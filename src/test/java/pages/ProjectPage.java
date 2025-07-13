package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectPage extends BasePage {

    private final String projectName;
    private final String projectCode;
    private final SelenideElement CREATE_NEW_TEST_BUTTON = $(byText("New test")),
            CREATE_CASE_BUTTON = $("[data-testid='create-case-button']"),
            TITLE_REPOSITORY = $(byText("repository")),
    CREATE_SHARE_BUTTON = $(byText("Create shared step")),
    CREATE_PLAN_BUTTON = $(byText("Create plan")),
    START_NEW_TEST_RUN_BUTTON = $(byText("Start new test run"));



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

    public ProjectPage verifyTestCaseExists(String caseTitle) {
        $(byText(caseTitle)).shouldBe(visible);
        return this;
    }

    public ProjectPage verifyProjectCreated(String projectName, String projectCode) {
        $(byText(projectCode)).shouldBe(visible);
        $(byText("repository")).shouldBe(visible);
        log.info("{} repository is created, with Project Name: '{}'", projectCode, projectName);
        return new ProjectPage(projectName, projectCode);
    }

    public NewTestCaseModal openNewTestCaseModal() {
        CREATE_NEW_TEST_BUTTON.shouldBe(visible, enabled).click();
        CREATE_CASE_BUTTON.shouldBe(visible, enabled).click();
        return new NewTestCaseModal(projectName, projectCode).isPageOpened();
    }

    public SelenideElement menuButtonByLabel(String label) {
        return $x(String.format("//span[text()='%s']", label))
                .shouldBe(visible, enabled);
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
