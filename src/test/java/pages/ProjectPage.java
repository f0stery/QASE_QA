package pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage extends BasePage {

    private final String projectName;
    private final String projectCode;

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
        $(byText("repository")).shouldBe(visible);
        return this;
    }

    public ProjectPage verifyTestCaseExists(String caseTitle) {
        $(byText(caseTitle)).shouldBe(visible);
        return this;
    }

    public NewTestCaseModal openNewTestCaseModal() {
        $(byText("New test")).shouldBe(visible, enabled).click();
        $("[data-testid='create-case-button']").shouldBe(visible, enabled).click();
        return new NewTestCaseModal(projectName, projectCode).isPageOpened();
    }


    public void navigateToRepository() {
        $x("//span[text()='Repository']").shouldBe(visible, enabled).click();
        $(byText(projectCode))
                .shouldBe(visible);
        $(byText("repository")).shouldBe(visible);
    }

    public void navigateToSharedSteps() {
        $x("//span[text()='Shared Steps']").shouldBe(visible, enabled).click();
        $(byText("Create shared step")).shouldBe(visible);
    }

    public void navigateToTestPlans() {
        $x("//span[text()='Test Plans']").shouldBe(visible, enabled).click();
        $(byText("Create plan")).shouldBe(visible);
    }

    public void navigateToTestRuns() {
        $x("//span[text()='Test Runs']").shouldBe(visible, enabled).click();
        $(byText("Start new test run")).shouldBe(visible);
    }
}
