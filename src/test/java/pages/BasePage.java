package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public abstract class BasePage {

    protected final SelenideElement
            EMAIL_CSS = $("[name='email']"),
            PASSWORD_CSS = $("[name='password']");

    public abstract BasePage openPage();

    public abstract BasePage isPageOpened();

    public void executeLogin(String email, String password) {
        EMAIL_CSS.setValue(email);
        PASSWORD_CSS.setValue(password).submit();
    }

    public void disableBeforeUnloadAlert() {
        executeJavaScript("window.onbeforeunload = null;");
    }
}
