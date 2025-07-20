package tests.api;

import io.qameta.allure.*;
import models.api.projects.get_all_projects.Entity;
import models.api.projects.get_all_projects.GetProjectsRs;
import org.testng.annotations.Test;

import java.util.List;

import static adapters.ProjectAPI.*;

@Epic("Projects API")
@Feature("Project management")
public class ProjectAPITest extends BaseAPITest{

    @Test
    @Story("Create a project")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a new project can be created")
    public void checkCreateProject() {

        String projectName = "QASE";
        String projectCode = "QASE2";

        createNewProject(projectName, projectCode);

        GetProjectsRs allProjects = getAllProjects();

        boolean isProjectFound = allProjects.result.entities.stream()
                .anyMatch(project ->
                        project.title.equals(projectName) &&
                                project.code.equals(projectCode)
                );

        softAssert.assertTrue(isProjectFound, "Created project must appear in the list of all projects.");
        softAssert.assertAll();
    }

    @Test
    @Story("Get all projects")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all existing projects are returned")
    public void checkGetAllProjects() {
        createNewProject("testing", "T2");

        GetProjectsRs response = getAllProjects();

        softAssert.assertTrue(response.status, "Status must be true");
        softAssert.assertNotNull(response.result, "Result should not be null");
        softAssert.assertNotNull(response.result.entities, "Entities list should not be null");
        softAssert.assertTrue(response.result.total >= 0, "Total must be >= 0");

        List<Entity> entities = response.result.entities;
        for (Entity project : entities) {
            softAssert.assertNotNull(project.title, "Project title should not be null");
            softAssert.assertNotNull(project.code, "Project code should not be null");
        }
        softAssert.assertAll();
    }
}
