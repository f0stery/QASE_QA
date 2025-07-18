package tests.ui;

import adapters.ProjectAPI;
import io.qameta.allure.*;
import models.api.create_project.CreateProjectRq;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProjectPage;

import static utils.TestDataGenerator.generateProjectCode;
import static utils.TestDataGenerator.generateProjectName;

@Epic("Test Case & Suite Management")
public class ProjectTest extends BaseTest {

    private final String projectName = generateProjectName("QASE");
    private final String projectCode = generateProjectCode(projectName);


    @BeforeMethod
    public void createProject() {
        CreateProjectRq projectRq = CreateProjectRq.builder()
                .title(projectName)
                .code(projectCode)
                .description("test cases, suites")
                .access("all")
                .group("test")
                .build();
        ProjectAPI.createProject(projectRq);
        projectPage = new ProjectPage(projectName, projectCode);
    }

    @Test(testName = "Check create test case", description = "Create test case")
    @Feature("Test Case")
    @Story("User create a test case")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a test case and verifies it exists on the project page")
    public void checkCreateTestCase() {
                projectPage.openPage().isPageOpened()
                        .openNewTestCaseModal()
                        .createTestCase(testCase);
        SoftAssert.assertTrue(projectPage.verifyTestCaseExists(testCase.getTitle()), "Test case" +
                testCase.getTitle() + "not create");
    }

    @Test(testName = "Check create suite", description = "Create a test suite")
    @Feature("Test Suite")
    @Story("User create a suite")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creates a suite named 'Smoke' and verifies it is created")
    public void checkCreateSuite() {
        projectPage
                .openPage()
                .isPageOpened()
                .createSuite("Smoke", "Check authorization function", "nothing",
                        "Project root");
        SoftAssert.assertTrue(projectPage.verifyCreateSuite("Smoke"),
                "Suite is not create");
    }

    @Test(testName = "Check edit suite", description = "Edit a test suite")
    @Feature("Test Suite")
    @Story("User edit an existing suite")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creates and edits a suite, then verifies the updated suite appears")
    public void checkEditSuite() {
        projectPage.openPage()
                .isPageOpened()
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
    }

    @AfterMethod
    public void deleteProject(ITestResult result) {
        ProjectAPI.deleteProject(projectCode);
    }
}
