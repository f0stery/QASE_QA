package tests.api;

import io.qameta.allure.*;
import io.restassured.response.Response;
import models.api.GeneratedDefectData;
import models.api.defects.get_specific_defect.GetSpecificDefectRs;
import models.api.defects.get_specific_defect.Result;
import models.api.defects.get_all_defects.GetAllDefectRs;
import models.api.defects.update_defect.UpdateDefectRq;
import models.api.defects.update_defect.UpdateDefectRs;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static adapters.DefectAPI.*;
import static org.testng.Assert.*;
import static utils.TestDataGenerator.*;

@Epic("Defects API")
@Feature("Defect CRUD Operations")
public class DefectAPITest extends BaseAPITest {

    private final String PROJECT_NAME = "Qase";
    private final String PROJECT_CODE = "QA";

    @Test
    @Story("Create a defect")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a defect can be created and has a valid ID")
    public void checkCreateDefect() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);
        GeneratedDefectData defectRs = TestDataGenerator.generateDefect(currentProjectCode);

        softAssert.assertTrue(defectRs.response.status, "Status should be true");
        softAssert.assertTrue(defectRs.response.result.id > 0, "Defect ID should be greater than 0");
        softAssert.assertNotNull(defectRs.response.result.id, "Defect ID should not be null");
        softAssert.assertAll();
    }

    @Test
    @Story("Get a specific defect")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a specific defect can be retrieved by its ID")
    public void checkGetDefect() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);
        GeneratedDefectData defectFirstRs = TestDataGenerator.generateDefect(currentProjectCode);

        assertTrue(defectFirstRs.response.status);
        assertEquals(defectFirstRs.response.result.id, 1);

        GetSpecificDefectRs getDefectRs = getDefect(currentProjectCode, 1);

        Result result = getDefectRs.result;
        softAssert.assertEquals(result.id.intValue(), 1);
        softAssert.assertEquals(result.title, defectFirstRs.title);
        softAssert.assertEquals(result.actual_result, defectFirstRs.actualResult);
        softAssert.assertEquals(result.severity.toLowerCase(), getSeverityLabel(defectFirstRs.severity));
        softAssert.assertAll();
    }

    @Test
    @Story("Get all defects")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all defects for the project are returned correctly")
    public void checkGetAllDefects() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GeneratedDefectData defectRs1 = TestDataGenerator.generateDefect(currentProjectCode);
        GeneratedDefectData defectRs2 = TestDataGenerator.generateDefect(currentProjectCode);
        GeneratedDefectData defectRs3 = TestDataGenerator.generateDefect(currentProjectCode);

        GetAllDefectRs response = getAllDefects(currentProjectCode);

        softAssert.assertTrue(response.status, "Status must be true");
        softAssert.assertNotNull(response.result, "Result should not be null");
        softAssert.assertTrue(response.result.total >= 3, "There should be at least 3 defects");
        softAssert.assertFalse(response.result.entities.isEmpty(), "Entities list should not be empty");

        List<String> createdTitles = List.of(defectRs1.title, defectRs2.title, defectRs3.title);
        List<String> responseTitles = response.result.entities.stream()
                .map(entity -> entity.title)
                .collect(Collectors.toList());

        softAssert.assertTrue(responseTitles.containsAll(createdTitles), "All created defect titles should " +
                "be in the response");
        softAssert.assertAll();
    }

    @Test
    @Story("Update a defect")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a defect can be successfully updated")
    public void checkUpdateDefect() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GeneratedDefectData createdDefect = TestDataGenerator.generateDefect(currentProjectCode);
        int defectId = createdDefect.response.result.id;

        String updatedTitle = generateTitle();
        String updatedActualResult = generateActualResult();
        int updatedSeverity = 1;

        UpdateDefectRq updateDefectRq = UpdateDefectRq.builder()
                .title(updatedTitle)
                .actual_result(updatedActualResult)
                .severity(updatedSeverity)
                .build();

        UpdateDefectRs updateRs = updateDefect(updateDefectRq, currentProjectCode, defectId);
        softAssert.assertTrue(updateRs.status, "Status update must be true");

        GetSpecificDefectRs updatedDefectRs = getDefect(currentProjectCode, defectId);
        softAssert.assertTrue(updatedDefectRs.status, "Status get Defect must be true");

        Result result = updatedDefectRs.result;
        softAssert.assertEquals(result.title, updatedTitle, "Title not updated");
        softAssert.assertEquals(result.actual_result, updatedActualResult, "Actual result not updated");
        softAssert.assertEquals(result.severity.toLowerCase(), getSeverityLabel(updatedSeverity),
                "Severity not updated");
        softAssert.assertAll();
    }

    @Test
    @Story("Delete a defect")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a defect is deleted successfully and returns 404 when accessed")
    public void checkDeleteDefect() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GeneratedDefectData createdDefect = TestDataGenerator.generateDefect(currentProjectCode);
        int defectId = createdDefect.response.result.id;

        deleteDefect(currentProjectCode, defectId);

        Response response = getDefectRaw(currentProjectCode, defectId);

        softAssert.assertEquals(response.statusCode(), 404, "Expected 404 after deleting defect");
        softAssert.assertFalse(response.jsonPath().getBoolean("status"), "Status should be false after " +
                "deletion");
        softAssert.assertEquals(response.jsonPath().getString("errorMessage"), "Defect not found");
        softAssert.assertAll();
    }
}
