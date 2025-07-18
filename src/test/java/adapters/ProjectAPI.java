package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.api.create_defect.CreateDefectRq;
import models.api.create_defect.CreateDefectRs;
import models.api.create_project.CreateProjectRq;
import models.api.create_project.CreateProjectRs;
import tests.ui.PropertyReader;

import static io.restassured.RestAssured.given;

public class ProjectAPI {

    static String token = System.getProperty("token", PropertyReader.getProperty("token"));
    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    static RequestSpecification spec =
            given()
            .log().all()
            .contentType(ContentType.JSON)
            .header("Token", token);

    public static CreateProjectRs createProject(CreateProjectRq projectRq) {
        return
                spec
                .body(gson.toJson(projectRq))
                .when()
                .post("https://api.qase.io/v1/project")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreateProjectRs.class);
    }

    public static CreateDefectRs createDefect(CreateDefectRq defectRq, String projectCode) {
        return
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Token", "9065605510201f8c9712cd7435488d76e741c1cd39ae0839b72dbc9a55c44d0f")
                .body(defectRq)
                .when()
                .post("https://api.qase.io/v1/defect/" + projectCode)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreateDefectRs.class);
    }

    public static void deleteProject(String projectCode) {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Token", "9065605510201f8c9712cd7435488d76e741c1cd39ae0839b72dbc9a55c44d0f")
                .when()
                .delete("https://api.qase.io/v1/project/" + projectCode)
                .then()
                .log().all()
                .statusCode(200);
    }
}
