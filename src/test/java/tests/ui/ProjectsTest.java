package tests.ui;

import adapters.ProjectAPI;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.TestDataGenerator.*;

@Log4j2
@Epic("Project Management")
public class ProjectsTest extends BaseTest {

    private static final String PREFIX = "QASE";
    final String projectName = generateProjectName(PREFIX);
    final String projectCode = generateProjectCode(projectName);
    final String prefixForSearch = generateCodeAfterPrefix(projectName, PREFIX);


    @Test(testName = "Check create public project", groups = {"smoke"}, description = "Create public project")
    @Feature("Create Project")
    @Story("User create a public project")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a public project and verifies it's marked as public")
    public void checkCreatePublicProject() {
        project.createAndOpenProject(projectName, projectCode);
        assertTrue(project.getProjectPage().checkTypeProject("Public"), "Project " + projectName +
                " is not Public");
    }

    @Test(testName = "Check create private project", description = "Create private project", groups = {"smoke"})
    @Feature("Create Project")
    @Story("User create a private project")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a private project and verifies it's marked as private")
    public void checkCreatePrivateProject() {
        project.createAndOpenPrivateProject(projectName, projectCode);
        assertTrue(project.getProjectPage().checkTypeProject("Private"), "Project " + projectName +
                " is not Private");
    }

    @Test(testName = "Check create project with same Project CODE",
            description = "Negative test create with same code project")
    @Feature("Create Project")
    @Story("User tries to create project with duplicate project code")
    @Severity(SeverityLevel.NORMAL)
    @Description("Tries to create a project with the same code and verifies an error message")
    public void createSameNameProject() {
        project
                .createAndOpenProject(projectName, projectCode)
                .verifyProjectCreated(projectCode, projectName);
        homePage.openPage()
                .isPageOpened()
                .checkCreateSameNameProject(projectName, projectCode);
    }

    @Test(testName = "Check Search function", description = "Search project")
    @Feature("Search")
    @Story("User search for a project")
    @Severity(SeverityLevel.MINOR)
    @Description("Creates a project and verifies that it can be found using search")
    public void checkSearchProject() {
        project.createAndOpenProject(projectName, projectCode)
                .verifyProjectCreated(projectCode, projectName);
        homePage.openPage()
                .isPageOpened()
                .searchProjectByName(prefixForSearch, projectName);
    }

    @Test(testName = "Check delete project", description = "Delete project")
    @Feature("Delete Project")
    @Story("User delete a project")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deletes a created project and verifies it no longer appears")
    public void checkRemoveProject() {
        project.createAndOpenProject(projectName, projectCode)
                        .verifyProjectCreated(projectCode, projectName);
        homePage.openPage()
                .isPageOpened()
                .deleteProject(projectName)
                .verifyProjectDeleted(projectName);
    }

    @AfterMethod
    public void deleteProject(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (!testName.equals("checkRemoveProject")) {
            ProjectAPI.deleteProject(projectCode);
        }
    }
}
