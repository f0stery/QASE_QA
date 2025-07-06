package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage{

    private final SelenideElement EMAIL_CSS = $("[name='email']");
    private final SelenideElement PASSWORD_CSS = $("[name='password']");

    @Override
    public LoginPage openPage() {
        log.info("Login page opening");
        open("/login");
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        PASSWORD_CSS.shouldBe(visible);
        log.info("Login page is opened");
        return this;
    }

    public HomePage login(String email, String password) {
        log.info("Enter value email: '{}'", email);
        EMAIL_CSS.setValue(email);
        log.info("Enter value password: '{}' and submit log in", password);
        PASSWORD_CSS.setValue(password).submit();
        return new HomePage();
    }
}
