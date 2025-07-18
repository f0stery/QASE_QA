package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class HomePage extends BasePage{

    private final SelenideElement PROJECT_NAME = $(By.id("project-name")),
            PROJECT_CODE = $(By.id("project-code")),
            CREATE_NEW_PROJECT_BUTTON = $(byText("Create new project")),
            PUBLIC_PROJECT = $(byText("Public")),
            NOT_ADD_MEMBERS = $(byText("Don't add members")),
            CREATE_PROJECT_BUTTON = $(byText("Create project")),
            PRIVATE_PROJECT = $(byText("Private")),
            APPROVE_DELETE_BUTTON = $x("//span[text()='Delete project']"),
            REMOVE_BUTTON = $("[data-testid='remove']"),
            GROUP_ACCESS_BUTTON = $(byText("Group access")),
            DROP_DOWN_GROUP_ACCESS = $x("//*[text()='Member access']" +
            "/ancestor::div[2]//*[@data-icon='chevron-down']"),
            SEARCH_FIELD = $x("//input[@aria-label='Search for projects']"),
            OWNER_GROUP_OPTION = $(byText("Owner group"));
    private final String ACTION_BUTTON = "button[aria-label='Open action menu']",
            CODE_ALREADY_USE_ERROR = "The selected project code is already in use.";

    @Step("Opening the home page")
    @Override
    public HomePage openPage() {
        log.info("Project page opening");
        open("/projects");
        return this;
    }

    @Step("Verifying the home page is opened")
    @Override
    public HomePage isPageOpened() {
        CREATE_NEW_PROJECT_BUTTON.shouldBe(visible);
        log.info("Home page with projects is opened");
        return this;
    }

    @Step("Filling project data: name = '{name}', code = '{code}'")
    private HomePage fillProjectData(String name, String code) {
        log.info("Setting project name: '{}' and code: '{}'", name, code);
        PROJECT_NAME.setValue(name);
        PROJECT_CODE.setValue(code);
        return this;
    }

    @Step("Creating a **public project**: name = '{projectName}', code = '{projectCode}'")
    public ProjectPage createPublicProject(String projectName, String projectCode) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        fillProjectData(projectName, projectCode);
        log.info("Set Public group for project");
        PUBLIC_PROJECT.click();
        log.info("Click create project button");
        CREATE_PROJECT_BUTTON.shouldBe(visible, enabled).click();
        return new ProjectPage(projectName, projectCode);
    }

    @Step("Creating a **private project**: name = '{projectName}', code = '{projectCode}'")
    public ProjectPage createPrivateProject(String projectName, String projectCode) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        fillProjectData(projectName, projectCode);
        log.info("Set Private group for project");
        PRIVATE_PROJECT.click();
        log.info("Set Member access: Don't add members");
        NOT_ADD_MEMBERS.click();
        log.info("Click create project button");
        CREATE_PROJECT_BUTTON.shouldBe(enabled).click();
        log.info("Check create {} repository", projectCode);
        $(byText(projectName))
                .shouldBe(visible);
        log.info("{} repository is created, with Project Name: '{}'", projectCode, projectName);
        return new ProjectPage(projectName, projectCode);
    }

    @Step("Searching for project by partial name '{symbolsFromProjectName}', full name = '{projectName}'")
    public HomePage searchProjectByName(String symbolsFromProjectName, String projectName) {
        log.info("Search project with symbol: '{}'", symbolsFromProjectName);
        SEARCH_FIELD.sendKeys(symbolsFromProjectName);
        $(byText(projectName)).shouldBe(visible);
        log.info("Project " + projectName + " is found");
        return this;
    }

    @Step("Creating a **private project with group access**: name = '{projectName}', code = '{projectCode}'")
    public ProjectPage createPrivateWithGroupProject(String projectName, String projectCode) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        fillProjectData(projectName, projectCode);
        log.info("Set Private group for project");
        PRIVATE_PROJECT.click();
        log.info("Set Group access: Owner group");
        GROUP_ACCESS_BUTTON.click();
        DROP_DOWN_GROUP_ACCESS.click();
        OWNER_GROUP_OPTION.shouldBe(visible, enabled).click();
        CREATE_PROJECT_BUTTON.shouldBe(enabled).click();
        $(byText(projectName))
                .shouldBe(visible);
        log.info("{} repository is created, with Project Name: '{}'", projectCode, projectName);
        return new ProjectPage(projectName, projectCode);
    }

    @Step("Trying to create a project with a **duplicate name**: name = '{projectName}', code = '{projectCode}'")
    public HomePage checkCreateSameNameProject(String projectName, String projectCode) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        fillProjectData(projectName, projectCode);
        log.info("Set Public group for project");
        PUBLIC_PROJECT.click();
        log.info("Click create project button");
        CREATE_PROJECT_BUTTON.shouldBe(enabled).click();
        verifyProjectCodeNotAvailable();
        return this;
    }

    @Step("Verifying that the project code is **already in use**")
    public HomePage verifyProjectCodeNotAvailable() {
        $(byText(CODE_ALREADY_USE_ERROR))
                .shouldBe(visible)
                .shouldHave(text("already in use"));
        log.info("Project with same code not created");
        return this;
    }

    @Step("Verifying that project '{projectName}' is deleted")
    public HomePage verifyProjectDeleted(String projectName) {
        $(byText(projectName)).shouldNotBe(visible);
        return this;
    }

    @Step("Opening actions menu for project '{projectName}'")
    public HomePage openProjectActionsMenu(String projectName) {
        log.info("Opening action menu for project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(ACTION_BUTTON)
                .click();
        return this;
    }

    @Step("Confirming project deletion")
    public HomePage confirmDeletion() {
        log.info("Click 'Delete project' button for approving");
        APPROVE_DELETE_BUTTON
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Clicking 'Remove' in actions menu for project '{projectName}'")
    public HomePage clickRemoveInActionsMenu(String projectName) {
        log.info("Click 'remove' button in action menu'{}'", projectName);
        REMOVE_BUTTON
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Deleting project '{projectName}'")
    public HomePage deleteProject(String projectName) {
        log.info("Deleting project {}", projectName);
        openProjectActionsMenu(projectName);
        clickRemoveInActionsMenu(projectName);
        confirmDeletion();
        log.info("Project {} is deleted", projectName);
        return verifyProjectDeleted(projectName);
    }
}
