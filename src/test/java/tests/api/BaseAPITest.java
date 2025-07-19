package tests.api;

import adapters.ProjectAPI;
import models.api.projects.create_project.CreateProjectRq;
import models.api.projects.create_project.CreateProjectRs;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class BaseAPITest {

    protected SoftAssert softAssert;
    protected String currentProjectCode;
    protected String currentProjectName;

    protected void initSoftAssert() {
        softAssert = new SoftAssert();
    }

    protected void createNewProject(String name, String code) {
        initSoftAssert();
        currentProjectName = name;
        currentProjectCode = code;

        CreateProjectRq projectRq = CreateProjectRq.builder()
                .title(name)
                .code(code)
                .description("auto created")
                .access("all")
                .group("testers")
                .build();

        CreateProjectRs projectRs = ProjectAPI.createProject(projectRq);

        assertTrue(projectRs.status, "Project must be create");
        assertEquals(projectRs.result.code, code, "Check Project Code");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteProject() {
        if (currentProjectCode != null) {
            ProjectAPI.deleteProject(currentProjectCode);
        }
    }
}

