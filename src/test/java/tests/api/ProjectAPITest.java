package tests.api;

import models.api.create_project.CreateProjectRq;
import models.api.create_project.CreateProjectRs;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProject;

public class ProjectAPITest {

    protected static final String PROJECT_NAME = "QASE_QA";
    protected static final String PROJECT_CODE = "QASE";

    @Test
    public void checkCreateProject() {
        CreateProjectRq projectRq = CreateProjectRq.builder()
                .title(PROJECT_NAME)
                .code(PROJECT_CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();

        CreateProjectRs projectRs = createProject(projectRq);
        Assert.assertEquals(projectRs.status, true);
        Assert.assertEquals(projectRs.result.code, PROJECT_CODE);

        deleteProject(projectRs.result.code);
    }
}
