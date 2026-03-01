package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
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

    public static void setup() {
        // Создаём Connection Manager для HttpClient 5.x
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);

        // Создаём HttpClient (объект возвращаемый lambda должен быть типа HttpClient)
        HttpClient client = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        // Настройка Rest-Assured
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .httpClientFactory(() -> client));
    }

    static RequestSpecification getSpec() {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Token", token);
    }

    public static <T> T post(Object request, String url, Class<T> responseType) {
        return getSpec()
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
        return getSpec()
                .when()
                .get(baseAPIUrl + url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(responseType);
    }

    public static <T> T patch(Object request, String url, Class<T> responseType) {
        return getSpec()
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
        getSpec()
                .when()
                .delete(baseAPIUrl + url)
                .then()
                .log().all()
                .statusCode(200);
    }
}