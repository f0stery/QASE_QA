package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import utils.Retry;

import static utils.TestDataGenerator.generateProjectCode;
import static utils.TestDataGenerator.generateProjectName;

@Log4j2
public class ProjectsTest extends BaseTest{

    private static final String DEFAULT_PROJECT = "ZIT";
    private static final String DEFAULT_CODE = "ZIT31";

    @Test (testName = "Check create public project", groups = {"smoke"},
            description = "Create public project", retryAnalyzer = Retry.class)
    public void checkCreatePublicProject() {

        final String projectName = generateProjectName("QASE");
        final String projectCode = generateProjectCode(projectName);

        project.createAndOpenProject(projectName, projectCode)
                .verifyProjectCreated(projectCode, projectName);
        homePage.openPage()
                .isPageOpened()
                .deleteProject(projectName);
    }

    @Test (testName = "Check create project with same Project CODE",
            description = "Negative test create with same code project ", retryAnalyzer = Retry.class)
    public void createSameNameProject() {

        final String projectName = generateProjectName("SAME");
        final String projectCode = generateProjectCode(projectName);

        project.createAndOpenProject(projectName, projectCode);

        homePage.openPage()
                .isPageOpened()
                .checkCreateSameNameProject(projectName, projectCode)
                .openPage()
                .isPageOpened()
                .deleteProject(projectName);
    }

    @Test (testName = "Check create private project",
            description = "Create private project",
            groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void checkCreatePrivateProject() {

        final String projectName = generateProjectName("TSM");
        final String projectCode = generateProjectCode(projectName);

        project.createAndOpenPrivateProject(projectName, projectCode)
                        .verifyProjectCreated(projectCode, projectName);
        homePage.openPage()
                .isPageOpened()
                .deleteProject(projectName);
    }

    @Test(testName = "Check Search function",
            description = "Search Defoult project",
            retryAnalyzer = Retry.class, priority = 1)
    public void checkSearchProject() {

        homePage.createPrivateWithGroupProject(DEFAULT_PROJECT, DEFAULT_CODE);
        homePage.openPage()
                .isPageOpened()
                .searchProjectByName("Z", DEFAULT_PROJECT);
    }

    @Test(testName = "Check delete project",
            description = "Deleted project test",
            dependsOnMethods = "checkSearchProject",
            retryAnalyzer = Retry.class,
            priority = 2)
    public void checkRemoveProject() {
        homePage.openPage()
                .isPageOpened()
                .deleteProject(DEFAULT_PROJECT)
                .verifyProjectDeleted(DEFAULT_PROJECT);
    }
}
