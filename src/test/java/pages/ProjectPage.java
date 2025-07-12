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
        $(byText(projectName)).shouldBe(visible);
        $(byText(projectCode)).shouldBe(visible);
        $(byText("repository")).shouldBe(visible);
        return this;
    }

    public void navigateToRepository() {
        $x("//span[text()='Repository']").shouldBe(visible, enabled).click();
        $(byText(projectCode))
                .shouldBe(visible);
        $(byText("repository")).shouldBe(visible);
    }
}
