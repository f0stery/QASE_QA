package tests;

import org.testng.annotations.Test;

import static utils.TestDataGenerator.generateProjectCode;
import static utils.TestDataGenerator.generateProjectName;

public class ProjectTest extends BaseTest {

    @Test
    public void testExample() {

        String projectName = generateProjectName("QASE");
        String projectCode = generateProjectCode(projectName);

        createAndOpenProject(projectName, projectCode)
                .openNewTestCaseModal()
                .createTestCase("Auth");
    }
}
