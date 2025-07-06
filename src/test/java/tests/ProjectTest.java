package tests;

import org.testng.annotations.Test;
import utils.Retry;


public class ProjectTest extends BaseTest{

    @Test (testName = "Авторизация", enabled = false, groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void checkAuthorization() {
        loginPage.openPage();
        loginPage.login(email, password);
        projectsPage.waitTillOpened();
    }

    @Test (testName = "Проверка на создание и удаление Public проекта", groups = {"smoke"},
            description = "Удачное создание и удаление Public проекта", retryAnalyzer = Retry.class)
    public void checkCreatePublicProject() {
        loginPage.openPage();
        loginPage.login(email, password);
        projectsPage.waitTillOpened();
        projectsPage.createPublicProject("QASE");
        projectsPage.openPage();
        projectsPage.waitTillOpened();
        projectsPage.deleteProject("QASE");
    }

    @Test (testName = "Проверка на создание и удаление Private проекта",
            description = "Удачное создание и удаление Private проекта",
            groups = {"smoke"},
            retryAnalyzer = Retry.class)
    public void checkCreatePrivateProject() {
        loginPage.openPage();
        loginPage.login(email, password);
        projectsPage.waitTillOpened();
        projectsPage.createPrivateProject("TSM");
        projectsPage.openPage();
        projectsPage.waitTillOpened();
        projectsPage.deleteProject("TSM");
    }
}
