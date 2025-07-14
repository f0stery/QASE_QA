package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProjectPage;

import static utils.TestDataGenerator.generateProjectCode;
import static utils.TestDataGenerator.generateProjectName;

public class ProjectTest extends BaseTest {

    private String projectName;
    private String projectCode;

    @BeforeMethod
    public void setUpProject() {
        projectName = generateProjectName("QASE");
        projectCode = generateProjectCode(projectName);
        projectPage = new ProjectPage(projectName, projectCode);
    }

    @Test(enabled = true)
    public void createSuiteAndCase() {

        project.createAndOpenProject(projectName, projectCode)
                .createSuite("Smoke", "Check authorization function", "nothing",
                        "Project root")
                .openNewTestCaseModal()
                .createTestCase("Auth", "Draft", "Critical", "High", "Smoke", "Unit",
                        "Yes", "Not set", "Positive", "Automated")
                .verifyCreateSuite("Smoke");

//        homePage.openPage().isPageOpened().deleteProject(projectName);
    }

    @Test(enabled = true)
    public void editSuite() {
        project.createAndOpenProject(projectName, projectCode)
                .createSuite("Smoke", "Check authorization function", "nothing",
                        "Project root")
                .openNewTestCaseModal()
                .createTestCase("Auth", "Draft", "Critical", "High", "Smoke", "Unit",
                        "Yes", "Not set", "Positive", "Automated")
                .verifyCreateSuite("Smoke")
                .openPage()
                .isPageOpened()
                .clickOnSuiteFunction("Smoke", "Edit suite");

        projectPage.editSuite("Smoke77", "Check new auth", "77", "Project root");
        homePage.openPage().isPageOpened().deleteProject(projectName);
    }
}
