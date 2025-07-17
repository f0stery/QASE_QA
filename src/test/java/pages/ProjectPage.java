package pages;

import com.codeborne.selenide.SelenideElement;
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
            MENU_BUTTONS_IN_PROJECT = "//span[text()='%s']",
            NAME_IN_SUITE_MENU = "[title='%s']",
            PROJECT_SETTING_MENU = "[aria-label='%s']",
            PROJECT_SETTING_MENU_LABEL = "Access control";

    private final SelenideElement
            CREATE_NEW_TEST_BUTTON = $(byText("New test")),
            TITLE_REPOSITORY = $(byText("repository")),
            CREATE_SHARE_BUTTON = $(byText("Create shared step")),
            CREATE_PLAN_BUTTON = $(byText("Create plan")),
            START_NEW_TEST_RUN_BUTTON = $(byText("Start new test run")),
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
    public ProjectPage openPage() {
        open("/project/" + projectCode);
        return this;
    }

    @Override
    public ProjectPage isPageOpened() {
        $(byText(projectCode)).shouldBe(visible);
        TITLE_REPOSITORY.shouldBe(visible);
        return this;
    }

    public boolean verifyTestCaseExists(String caseTitle) {
        $(byText(caseTitle)).shouldBe(visible);
        log.info("Test case '{}' is created", caseTitle);
        return true;
    }

    public ProjectPage verifyProjectCreated(String projectName, String projectCode) {
        $(byText(projectCode)).shouldBe(visible);
        $(byText(REPOSITORY_SUITE_BUTTON)).shouldBe(visible);
        log.info("'{}' repository is created, with Project Name: '{}'", projectCode, projectName);
        return this;
    }

    public NewTestCaseModal openNewTestCaseModal() {
        log.info("Opening menu 'New test'");
        CREATE_NEW_TEST_BUTTON.shouldBe(visible, enabled).click();
        return new NewTestCaseModal(projectName, projectCode).isPageOpened();
    }

    public boolean verifyCreateSuite(String suiteName) {
        $(String.format(NAME_IN_SUITE_MENU, suiteName)).shouldBe(visible, enabled);
        log.info("Suite '{}' is created", suiteName);
        return true;
    }

    public void clickOnSuiteFunction(String suiteName, String suiteFunction){
        log.info("Click on '{}' in suite", suiteFunction);
        $x(String.format(SUITE_FUNCTION_BUTTON, suiteName, suiteFunction))
                .shouldBe(visible, enabled)
                .click();
    }

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

    public SelenideElement menuButtonByLabel(String label) {
        log.info("Select '{}' in project menu", label);
        return $x(String.format(MENU_BUTTONS_IN_PROJECT, label))
                .shouldBe(visible, enabled);
    }

    public boolean isPublicProject() {
        SETTINGS_BUTTON.shouldBe(visible, enabled).click();
        $(String.format(PROJECT_SETTING_MENU, PROJECT_SETTING_MENU_LABEL)).shouldBe(visible, enabled).click();
        try {
            return $x("//div[contains(text(), 'Public')]/ancestor::label[@data-selected='true']")
                    .shouldBe(visible)
                    .exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPrivateProject() {
        SETTINGS_BUTTON.shouldBe(visible, enabled).click();
        $(String.format(PROJECT_SETTING_MENU, PROJECT_SETTING_MENU_LABEL)).shouldBe(visible, enabled).click();
        return $x("//div[contains(., 'Private')]/ancestor::" +
                "label[@data-react-aria-pressable]")
                .has(attribute("data-selected", "true"));
    }

    public void navigateToRepository() {
        menuButtonByLabel("Repository").click();
        $(byText(projectCode))
                .shouldBe(visible);
        TITLE_REPOSITORY.shouldBe(visible);
    }

    public void navigateToSharedSteps() {
        menuButtonByLabel("Shared Steps").click();
        CREATE_SHARE_BUTTON.shouldBe(visible);
    }

    public void navigateToTestPlans() {
        menuButtonByLabel("Test Plans").click();
        CREATE_PLAN_BUTTON.shouldBe(visible);
    }

    public void navigateToTestRuns() {
        menuButtonByLabel("Test Runs").click();
        START_NEW_TEST_RUN_BUTTON.shouldBe(visible);
    }
}
