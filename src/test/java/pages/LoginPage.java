package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage{

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

    public HomePage login(String email, String password) {
        log.info("Entering credentials and submitting login form");
        try {
            executeLogin(email, password);
            return new HomePage();
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            throw new RuntimeException("Login failed", e);
        }
    }
}
