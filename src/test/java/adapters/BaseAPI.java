package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import tests.ui.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    static String token = System.getProperty("token", PropertyReader.getProperty("token")),
            baseAPIUrl = "https://api.qase.io/v1/",
            projectAPIUrl = "project/",
            defectAPIUrl = "defect/",
            milestonesAPIUrl = "milestone/",
            GET_VALUES = "?limit=10&offset=0";

    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    static {
        RestAssured.config = RestAssured.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().gsonObjectMapperFactory((cls, charset) -> gson)
        );
    }

    static RequestSpecification spec =
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header("Token", token);

    public static <T> T post(Object request, String url, Class<T> responseType) {
        return
                spec
                        .body(gson.toJson(request))
                        .when()
                        .post(baseAPIUrl + url)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(responseType);
    }

    public static <T> T get(String url, Class<T> responseType) {
        return spec
                .when()
                .get(baseAPIUrl + url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(responseType);
    }

    public static <T> T patch(Object request, String url, Class<T> responseType) {
        return spec
                .body(gson.toJson(request))
                .when()
                .patch(baseAPIUrl + url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(responseType);
    }

    public static void delete(String url) {
        spec
                .when()
                .delete(baseAPIUrl + url)
                .then()
                .log().all()
                .statusCode(200);
    }
}
