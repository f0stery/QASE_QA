package pages;

import org.openqa.selenium.By;
import wrappers.Picklist;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class NewTestCaseModal extends BasePage {

    private final String projectName;
    private final String projectCode;

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
        $(byText("Create test case")).shouldBe(visible);
        return this;
    }

    public ProjectPage createTestCase(String title, String status, String severity, String priority,
                                      String type, String layer, String flaky, String milestone,
                                      String behavior, String automationStatus) {
        new Input().write(title);
        new Picklist("Status").select(status);
        new Picklist("Severity").select(severity);
        new Picklist("Priority").select(priority);
        new Picklist("Type").select(type);
        new Picklist("Layer").select(layer);
        new Picklist("Is flaky").select(flaky);
        new Picklist("Milestone").select(milestone);
        new Picklist("Behavior").select(behavior);
        new Picklist("Automation status").select(automationStatus);

        clickSaveButton();
        return new ProjectPage(projectName, projectCode).isPageOpened();
    }

    public NewTestCaseModal clickSaveButton() {
        $(By.id("save-case")).click();
        return this;
    }
}
