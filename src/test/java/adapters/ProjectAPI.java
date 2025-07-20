package adapters;

import models.api.projects.create_project.CreateProjectRq;
import models.api.projects.create_project.CreateProjectRs;
import models.api.projects.get_all_projects.GetProjectsRs;


public class ProjectAPI extends BaseAPI {

    public static CreateProjectRs createProject(CreateProjectRq projectRq) {
        return post(projectRq, projectAPIUrl, CreateProjectRs.class);
    }

    public static GetProjectsRs getAllProjects() {
        return
                spec
                .when()
                .get(baseAPIUrl + projectAPIUrl + GET_VALUES)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(GetProjectsRs.class);
    }

    public static void deleteProject(String projectCode) {
        spec
                .when()
                .delete(baseAPIUrl + projectAPIUrl + projectCode)
                .then()
                .log().all()
                .statusCode(200);
    }
}
