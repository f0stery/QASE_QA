package wrappers;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Picklist {

    String label;
    String selectPattern = "//label[contains(., '%s')]/following::div[@role='combobox'][1]";

    public Picklist(String label) {
        this.label = label;
    }

    public void select(String option) {
        $x(String.format(selectPattern, label))
                .shouldBe(visible, enabled).click();
        $(byText(option)).click();
    }
}
