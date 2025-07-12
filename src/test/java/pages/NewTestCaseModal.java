package pages;

import org.openqa.selenium.By;
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

    public ProjectPage createTestCase(String title) {
        new Input().write(title);
        clickSaveButton();
        return new ProjectPage(projectName, projectCode).isPageOpened();
    }

    public NewTestCaseModal clickSaveButton() {
        $(By.id("save-case")).click();
        return this;
    }
}
