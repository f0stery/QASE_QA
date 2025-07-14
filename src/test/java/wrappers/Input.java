package wrappers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    private final SelenideElement TITLE_NAME = $(By.name("title"));

    public void write(String title) {
        TITLE_NAME.setValue(title);
    }
}
