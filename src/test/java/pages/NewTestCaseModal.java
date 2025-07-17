package pages;

import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import wrappers.Picklist;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class NewTestCaseModal extends BasePage {

    private final String
            projectName,
            projectCode,
            CREATE_TEST_CASE = "Create test case";


    public NewTestCaseModal(String projectName, String projectCode) {
        this.projectName = projectName;
        this.projectCode = projectCode;
    }

    @Override
    public NewTestCaseModal openPage() {
        open("/case/" + projectCode + "/create");
        return this;
    }

    @Override
    public NewTestCaseModal isPageOpened() {
        $(byText(CREATE_TEST_CASE)).shouldBe(visible);
        return this;
    }

    public ProjectPage createTestCase(TestCase testCase) {
        log.info("Create test case with params: '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}'",
                testCase.getTitle(), testCase.getStatus(),
                testCase.getSeverity(), testCase.getPriority(),
                testCase.getType(), testCase.getLayer(),
                testCase.getFlaky(), testCase.getMilestone(),
                testCase.getBehavior(), testCase.getAutomationStatus());
        new Input().write(testCase.getTitle());
        new Picklist("Status").select(testCase.getStatus());
        new Picklist("Severity").select(testCase.getSeverity());
        new Picklist("Priority").select(testCase.getPriority());
        new Picklist("Type").select(testCase.getType());
        new Picklist("Layer").select(testCase.getLayer());
        new Picklist("Is flaky").select(testCase.getFlaky());
        new Picklist("Milestone").select(testCase.getMilestone());
        new Picklist("Behavior").select(testCase.getBehavior());
        new Picklist("Automation status").select(testCase.getAutomationStatus());

        clickSaveButton();
        return new ProjectPage(projectName, projectCode).isPageOpened();
    }

    public NewTestCaseModal clickSaveButton() {
        log.info("Click on Save button");
        $(By.id("save-case")).click();
        return this;
    }
}
