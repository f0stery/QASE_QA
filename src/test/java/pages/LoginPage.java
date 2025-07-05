package pages;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage {

    private final String EMAIL_CSS = "[name='email']";
    private final String PASSWORD_CSS = "[name='password']";

    public void openPage() {
        log.info("Login page opening");
        open("/login");
    }

    public void login(String email, String password) {
        log.info("Enter value email: '{}'", email);
        $(EMAIL_CSS).setValue(email);
        log.info("Enter value password: '{}' and submit log in", password);
        $(PASSWORD_CSS).setValue(password).submit();
    }
}
