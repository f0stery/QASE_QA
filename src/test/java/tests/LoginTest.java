package tests;

import org.testng.annotations.Test;
import utils.Retry;


public class LoginTest extends BaseTest{

    @Test(testName = "Авторизация", enabled = true)
    public void checkLogin() {
        loginPage.openPage();
        loginPage.login(email, password);
        projectsPage.waitTillOpened();
    }
}
