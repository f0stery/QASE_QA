package tests;

import org.testng.annotations.Test;
import utils.Retry;


public class LoginTest extends BaseTest{

    @Test(testName = "Авторизация", enabled = true)
    public void checkLogin() {
        loginPage.openPage();
        loginPage.login("f0rsteryo@gmail.com", "mW@7!uU?vnVf6mD");
        projectsPage.waitTillOpened();
    }
}
