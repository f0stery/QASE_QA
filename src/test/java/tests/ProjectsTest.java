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

    @Test (testName = "Проверка на создание и удаление Public проекта", groups = {"smoke"},
            description = "Удачное создание и удаление Public проекта", retryAnalyzer = Retry.class)
    public void checkCreatePublicProject() {

        final String projectName = generateProjectName("QASE");
        final String projectCode = generateProjectCode(projectName);
        log.info("Testing project: {} ({})", projectName, projectCode);

        homePage.createPublicProject(projectName, projectCode);
        homePage.openPage()
                .isPageOpened()
                .deleteProject(projectName)
                .isPageOpened();
    }

    @Test (testName = "Проверка создания проекта с одинаковым Project CODE",
            description = "Негативный тест на создание проекта с одинаковым Project CODE", retryAnalyzer = Retry.class)
    public void createSameNameProject() {

        final String projectName = generateProjectName("SAME");
        final String projectCode = generateProjectCode(projectName);

        homePage.createPublicProject(projectName, projectCode);
        homePage.openPage()
                        .isPageOpened();

        homePage.checkCreateSameNameProject(projectName, projectCode)
                .openPage()
                .isPageOpened()
                .deleteProject(projectName);
    }

    @Test (testName = "Проверка на создание и удаление Private проекта",
            description = "Удачное создание и удаление Private проекта",
            groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void checkCreatePrivateProject() {

        final String projectName = generateProjectName("TSM");
        final String projectCode = generateProjectCode(projectName);

        homePage.createPrivateProject(projectName, projectCode);
        homePage.openPage()
                .isPageOpened()
                .deleteProject(projectName)
                .isPageOpened();
    }

    @Test (groups = "smoke")
    public void testProjectLifecycle() {
        String name = generateProjectName("QASE");
        String code = generateProjectCode(name);

        homePage.createPublicProject(name, code)
                .verifyProjectCreated(code, name);
    }

    @Test(retryAnalyzer = Retry.class)
    public void checkSearchProject() {
        homePage.createPrivateWithGroupProject(DEFAULT_PROJECT, DEFAULT_CODE);
        homePage.openPage()
                .isPageOpened()
                .searchProjectByName("Z", DEFAULT_PROJECT)
                .deleteProject(DEFAULT_PROJECT)
                .isPageOpened();
    }
}
