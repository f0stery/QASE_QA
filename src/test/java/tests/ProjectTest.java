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

    @Test (enabled = true)
    public void checkCreateTestCase() {
        project
                .createAndOpenProject(projectName, projectCode)
                .openNewTestCaseModal()
                .createTestCase(testCase);
        SoftAssert.assertTrue(projectPage.verifyTestCaseExists(testCase.getTitle()), "Test case" +
                testCase.getTitle() + "not create");
        homePage.openPage().isPageOpened().deleteProject(projectName);
    }

    @Test(enabled = true)
    public void checkCreateSuite() {
        project
                .createAndOpenProject(projectName, projectCode);
        projectPage
                .openPage()
                .isPageOpened()
                .createSuite("Smoke", "Check authorization function", "nothing",
                        "Project root");
        SoftAssert.assertTrue(projectPage.verifyCreateSuite("Smoke"),
                "Suite is not create");
        homePage.openPage().isPageOpened().deleteProject(projectName);
    }

    @Test(enabled = true)
    public void checkEditSuite() {
        project
                .createAndOpenProject(projectName, projectCode)
                .createSuite("Smoke", "Check authorization function", "nothing",
                        "Project root");
        SoftAssert.assertTrue(projectPage.verifyCreateSuite("Smoke"),
                "Suite not create");
        projectPage
                .openNewTestCaseModal()
                .createTestCase(testCase)
                .isPageOpened()
                .clickOnSuiteFunction("Smoke", "Edit suite");
        projectPage
                .editSuite("Smoke77", "Check new auth", "77", "Project root")
                .openPage()
                .isPageOpened();
        SoftAssert.assertTrue(projectPage.verifyCreateSuite("Smoke77"),
                        "Suite not create");
        homePage.openPage().isPageOpened().deleteProject(projectName);
    }
}
