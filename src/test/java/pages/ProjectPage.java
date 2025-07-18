package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectPage extends BasePage {

    private final String projectName,
            projectCode,
            SUITE_FUNCTION_BUTTON = "//h3[contains(., '%s')]/following::button[@aria-label='%s']",
            REPOSITORY_SUITE_BUTTON = "repository",
            NAME_IN_SUITE_MENU = "[title='%s']",
            PROJECT_SETTING_MENU = "[aria-label='%s']",
            PROJECT_SETTING_MENU_LABEL = "Access control",
            PROJECT_ACCESS_TYPE = "//div[contains(., '%s')]/ancestor::label[@data-selected='true']";

    private final SelenideElement
            CREATE_NEW_TEST_BUTTON = $(byText("New test")),
            TITLE_REPOSITORY = $(byText("repository")),
            SETTINGS_BUTTON = $(byText("Settings")),
            ADD_SUITE_BUTTON = $(byText("Add suite")),
            SUITE_NAME_FIELD = $(By.id("title")),
            SUITE_DESCRIPTION_FIELD = $x("//div[@aria-label='Description']" +
                    "//div[@contenteditable='true']"),
            SUITE_PRECONDITION_FIELD = $x("//div[@aria-label='Preconditions']" +
                    "//div[@contenteditable='true']"),
            PARENT_SUITE_LIST = $(byText("Project root")),
            SUBMIT_CREATE_SUITE_BUTTON = $(byText("Create")),
            SAVE_SUITE_BUTTON = $(byText("Save"));

    public ProjectPage(String projectName, String projectCode) {
        this.projectName = projectName;
        this.projectCode = projectCode;
    }

    @Override
    @Step("Open project page '{projectCode}'")
    public ProjectPage openPage() {
        log.info("Opening project page");
        open("/project/" + projectCode);
        return this;
    }

    @Override
    @Step("Check if project page '{projectCode}' is opened")
    public ProjectPage isPageOpened() {
        $(byText(projectCode)).shouldBe(visible);
        TITLE_REPOSITORY.shouldBe(visible);
        log.info("Project page is opened");
        return this;
    }

    @Step("Verify test case '{caseTitle}' exists")
    public boolean verifyTestCaseExists(String caseTitle) {
        $(byText(caseTitle)).shouldBe(visible);
        log.info("Test case '{}' is created", caseTitle);
        return true;
    }

    @Step("Check if project is public")
    public ProjectPage verifyProjectCreated(String projectName, String projectCode) {
        $(byText(projectCode)).shouldBe(visible);
        $(byText(REPOSITORY_SUITE_BUTTON)).shouldBe(visible);
        log.info("'{}' repository is created, with Project Name: '{}'", projectCode, projectName);
        return this;
    }

    @Step("Navigate to 'Repository' section")
    public NewTestCaseModal openNewTestCaseModal() {
        log.info("Opening menu 'New test'");
        CREATE_NEW_TEST_BUTTON.shouldBe(visible, enabled).click();
        return new NewTestCaseModal(projectName, projectCode).isPageOpened();
    }

    @Step("Verify that suite '{suiteName}' is created")
    public boolean verifyCreateSuite(String suiteName) {
        $(String.format(NAME_IN_SUITE_MENU, suiteName)).shouldBe(visible, enabled);
        log.info("Suite '{}' is created", suiteName);
        return true;
    }

    @Step("Click on suite function '{suiteFunction}' for suite '{suiteName}'")
    public void clickOnSuiteFunction(String suiteName, String suiteFunction){
        log.info("Click on '{}' in suite", suiteFunction);
        $x(String.format(SUITE_FUNCTION_BUTTON, suiteName, suiteFunction))
                .shouldBe(visible, enabled)
                .click();
    }

    @Step("Create suite: name='{suiteName}', description='{description}', " +
            "precondition='{precondition}', parent='{parentSuite}'")
    public ProjectPage createSuite(String suiteName, String description,
                                   String precondition, String parentSuite) {
        log.info("Create suite with params: '{}', '{}', '{}', '{}'", suiteName, description,
                precondition, parentSuite);
        ADD_SUITE_BUTTON.shouldBe(visible, enabled).click();
        SUITE_NAME_FIELD.setValue(suiteName);
        SUITE_DESCRIPTION_FIELD.setValue(description);
        SUITE_PRECONDITION_FIELD.setValue(precondition);
        PARENT_SUITE_LIST.click();
        $(byText(parentSuite)).click();
        SUBMIT_CREATE_SUITE_BUTTON.click();
        return new ProjectPage(projectName, projectCode).isPageOpened();
    }

    @Step("Edit suite: name='{suiteName}', description='{description}', " +
            "precondition='{precondition}', parent='{parentSuite}'")
    public ProjectPage editSuite(String suiteName, String description, String precondition, String parentSuite) {
        log.info("Edit suite with params: '{}', '{}', '{}', '{}'", suiteName, description, precondition, parentSuite);
        SUITE_NAME_FIELD.setValue(suiteName);
        SUITE_DESCRIPTION_FIELD.setValue(description);
        SUITE_PRECONDITION_FIELD.setValue(precondition);
        PARENT_SUITE_LIST.click();
        $(byText(parentSuite)).click();
        SAVE_SUITE_BUTTON.click();
        return this;
    }

    @Step("Verify project access type")
    public boolean checkTypeProject(String type) {
        SETTINGS_BUTTON.shouldBe(visible, enabled).click();
        $(String.format(PROJECT_SETTING_MENU, PROJECT_SETTING_MENU_LABEL)).shouldBe(visible, enabled).click();
        log.info("Checking type project '{}'", projectName);
        try {
            return $x(String.format(PROJECT_ACCESS_TYPE, type))
                    .shouldBe(visible)
                    .exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
