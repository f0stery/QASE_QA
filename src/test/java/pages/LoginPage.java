package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage{

    private final SelenideElement EMAIL_CSS = $("[name='email']"),
            PASSWORD_CSS = $("[name='password']");

    @Step("Opening the login page")
    @Override
    public LoginPage openPage() {
        log.info("Login page opening");
        open("/login");
        return this;
    }

    @Step("Verifying the login page is opened")
    @Override
    public LoginPage isPageOpened() {
        PASSWORD_CSS.shouldBe(visible);
        log.info("Login page is opened");
        return this;
    }

    @Step("Login in as user: {email}")
    public HomePage login(String email, String password) {
        log.info("Enter value email: '{}'", email);
        EMAIL_CSS.setValue(email);
        log.info("Enter value password: '{}' and submit log in", password);
        PASSWORD_CSS.setValue(password).submit();
        return new HomePage();
    }
}
