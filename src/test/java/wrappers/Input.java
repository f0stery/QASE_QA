package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    public void write(String title) {
        $(By.name("title")).setValue(title);
    }
}
