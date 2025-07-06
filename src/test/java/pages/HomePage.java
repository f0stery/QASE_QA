package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class HomePage extends BasePage{

    private final SelenideElement PROJECT_NAME = $(By.id("project-name"));
    private final SelenideElement CREATE_NEW_PROJECT_BUTTON = $(byText("Create new project"));
    private final SelenideElement PUBLIC_PROJECT = $(byText("Public"));
    private final SelenideElement NOT_ADD_MEMBERS = $(byText("Don't add members"));
    private final SelenideElement CREATE_PROJECT_BUTTON = $(byText("Create project"));
    private final SelenideElement PRIVATE_PROJECT = $(byText("Private"));
    private final SelenideElement APPROVE_DELETE_BUTTON = $x("//span[text()='Delete project']");
    private final String ACTION_BUTTON = "button[aria-label='Open action menu']";
    private final SelenideElement REMOVE_BUTTON = $("[data-testid='remove']");
    private final SelenideElement GROUP_ACCESS_BUTTON = $(byText("Group access"));
    private final SelenideElement DROP_DOWN_GROUP_ACCESS = $x("//*[text()='Member access']" +
            "/ancestor::div[2]//*[@data-icon='chevron-down']");


    @Override
    public HomePage openPage() {
        log.info("Project page opening");
        open("/projects");
        return this;
    }

    @Override
    public HomePage isPageOpened() {
        CREATE_NEW_PROJECT_BUTTON.shouldBe(visible);
        log.info("Project page is opened");
        return this;
    }

    public void createPublicProject(String project) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        log.info("Set Project name: '{}'", project);
        PROJECT_NAME.setValue(project);
        log.info("Set Public group for project");
        PUBLIC_PROJECT.click();
        log.info("Click create project button");
        CREATE_PROJECT_BUTTON.click();
        try {
            PROJECT_NAME.shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not created");
        }
    }

    public void createPrivateProject(String project) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        log.info("Set Project name: '{}'", project);
        PROJECT_NAME.setValue(project);
        log.info("Set Private group for project");
        PRIVATE_PROJECT.click();
        log.info("Set Member access: Don't add members");
        NOT_ADD_MEMBERS.click();
        log.info("Click create project button");
        CREATE_PROJECT_BUTTON.click();
        try {
            PROJECT_NAME.shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not created");
        }
    }

    public void createPrivateWithGroupProject(String project) {
        log.info("Click on create new project button");
        CREATE_NEW_PROJECT_BUTTON.click();
        log.info("Set Project name: '{}'", project);
        PROJECT_NAME.setValue(project);
        log.info("Set Private group for project");
        PRIVATE_PROJECT.click();
        log.info("Set Group access: Owner group");
        GROUP_ACCESS_BUTTON.click();
        DROP_DOWN_GROUP_ACCESS.click();
        $(byText("Owner group")).shouldBe(visible, enabled).click();
        CREATE_PROJECT_BUTTON.click();
        try {
            PROJECT_NAME.shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not created");
        }
    }

    public HomePage deleteProject(String project) {
        log.info("Opening action menu for project '{}'", project);
        $(byText(project))
                .ancestor("tr")
                .find(ACTION_BUTTON)
                .click();
        log.info("Click 'remove' button in action menu'{}'", project);
        REMOVE_BUTTON.click();
        log.info("Click 'Delete project' button for approving");
        APPROVE_DELETE_BUTTON.click();
        try {
            PROJECT_NAME.shouldNotBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not deleted");
        }
        return this;
    }
}
