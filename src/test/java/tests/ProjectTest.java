package tests;

import org.testng.annotations.Test;
import utils.Retry;


public class ProjectTest extends BaseTest{

    @Test (testName = "Авторизация", enabled = false, groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void checkAuthorization() {
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
    }

    @Test (testName = "Проверка на создание и удаление Public проекта", groups = {"smoke"},
            description = "Удачное создание и удаление Public проекта", retryAnalyzer = Retry.class)
    public void checkCreatePublicProject() {
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
        homePage.createPublicProject("QASE");
        homePage.openPage()
                .isPageOpened()
                .deleteProject("QASE")
                .isPageOpened();
    }

    @Test (testName = "Проверка на создание и удаление Private проекта",
            description = "Удачное создание и удаление Private проекта",
            groups = {"smoke"},
            retryAnalyzer = Retry.class)
    public void checkCreatePrivateProject() {
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
        homePage.createPrivateProject("TSM");
        homePage.openPage()
                .isPageOpened()
                .deleteProject("TSM")
                .isPageOpened();
    }

    @Test
    public void checkCreatePrivateWithGroupProject() {
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
        homePage.createPrivateWithGroupProject("ZIT");
        homePage.openPage()
                .isPageOpened()
                .searchProjectByName("Z", "ZIT")
                .deleteProject("ZIT")
                .isPageOpened();
    }
}
