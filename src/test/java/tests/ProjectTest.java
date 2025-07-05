package tests;

import org.testng.annotations.Test;
import utils.Retry;


public class ProjectTest extends BaseTest{

    @Test (retryAnalyzer = Retry.class)
    public void checkCreateProject() {
        loginPage.openPage();
        loginPage.login("f0rsteryo@gmail.com", "mW@7!uU?vnVf6mD");
        projectsPage.waitTillOpened();
    }

    @Test (retryAnalyzer = Retry.class)
    public void checkCreatePublicProject() {
        loginPage.openPage();
        loginPage.login("f0rsteryo@gmail.com", "mW@7!uU?vnVf6mD");
        projectsPage.waitTillOpened();
        projectsPage.createPublicProject("QASE");
        projectsPage.openPage();
        projectsPage.waitTillOpened();
        projectsPage.deleteProject("QASE");
    }

    @Test (retryAnalyzer = Retry.class)
    public void checkCreatePrivateProject() {
        loginPage.openPage();
        loginPage.login("f0rsteryo@gmail.com", "mW@7!uU?vnVf6mD");
        projectsPage.waitTillOpened();
        projectsPage.createPrivateProject("TSM");
        projectsPage.openPage();
        projectsPage.waitTillOpened();
        projectsPage.deleteProject("TSM");
    }
}
