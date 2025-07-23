package tests.api;


import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.api.GenerateMilestonesData;
import models.api.milestones.get_all_milestones.GetAllMilestonesRs;
import models.api.milestones.get_specific_milestone.GetSpecifiMilestoneRs;
import models.api.milestones.get_specific_milestone.Result;
import models.api.milestones.update_milestone.UpdateMilestoneRq;
import models.api.milestones.update_milestone.UpdateMilestoneRs;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static adapters.DefectAPI.getDefectRaw;
import static adapters.MilestonesAPI.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MilestonesAPITest extends BaseAPITest {

    private final String PROJECT_NAME = "SauseDemo";
    private final String PROJECT_CODE = "SA";
    private final String updateTitle = "test";
    private final String updateDescription = "test";
    private final String updateStatus = "completed";

    @Test
    @Story("Create a milestone")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a milestone can be created and has a valid ID")
    public void checkCreateMilestone() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData milestonesRs = TestDataGenerator.generateMilestones(PROJECT_CODE);

        softAssert.assertTrue(milestonesRs.response.status, "Milestone creation should return true status");
        softAssert.assertNotNull(milestonesRs.response.result.id, "Milestone ID should not be null");
        softAssert.assertAll();
    }

    @Test
    @Story("Get a specific milestone")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a specific milestone can be retrieved by its ID")
    public void checkGetSpecificMilestone() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData milestonesRs = TestDataGenerator.generateMilestones(PROJECT_CODE);

        assertTrue(milestonesRs.response.status);
        assertEquals(milestonesRs.response.result.id, 1);

        GetSpecifiMilestoneRs getMilestoneRs = getMilestone(currentProjectCode, 1);

        Result result = getMilestoneRs.result;

        softAssert.assertEquals(result.id.intValue(), 1);
        softAssert.assertEquals(result.title, milestonesRs.title);
        softAssert.assertEquals(result.description, milestonesRs.description);
        softAssert.assertEquals(result.status.toLowerCase(), "active");
        softAssert.assertAll();
    }

    @Test
    @Story("Update a milestone")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a milestone can be successfully updated")
    public void checkUpdateMilestone() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData createdMilestone = TestDataGenerator.generateMilestones(PROJECT_CODE);
        int milestoneId = createdMilestone.response.result.id;

        UpdateMilestoneRq updateMilestoneRq = UpdateMilestoneRq.builder()
                .title(updateTitle)
                .description(updateDescription)
                .status(updateStatus)
                .build();

        UpdateMilestoneRs updateRs = updateMilestones(updateMilestoneRq, currentProjectCode, milestoneId);
        softAssert.assertTrue(updateRs.status, "Status update must be true");

        GetSpecifiMilestoneRs updatedMilestoneRs = getMilestone(currentProjectCode, milestoneId);

        softAssert.assertTrue(updatedMilestoneRs.status, "Status get Milestone must be true");

        Result result = updatedMilestoneRs.result;
        softAssert.assertEquals(result.title, updateTitle, "Title not updated");
        softAssert.assertEquals(result.description, updateDescription, "Description not updated");
        softAssert.assertEquals(result.status.toLowerCase(), updateStatus,
                "Status not updated");
        softAssert.assertAll();
    }

    @Test
    @Story("Get all milestones")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all milestones for the project are returned correctly")
    public void checkGetAllMilestones() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData createdMilestone1 = TestDataGenerator.generateMilestones(PROJECT_CODE);
        GenerateMilestonesData createdMilestone2 = TestDataGenerator.generateMilestones(PROJECT_CODE);
        GenerateMilestonesData createdMilestone3 = TestDataGenerator.generateMilestones(PROJECT_CODE);

        GetAllMilestonesRs response = getAllMilestones(currentProjectCode);

        softAssert.assertTrue(response.status, "Status must be true");
        softAssert.assertNotNull(response.result, "Result should not be null");
        softAssert.assertTrue(response.result.total >= 3, "There should be at least 3 milestones");
        softAssert.assertFalse(response.result.entities.isEmpty(), "Entities list should not be empty");

        List<String> createdTitles = List.of(createdMilestone1.title, createdMilestone2.title, createdMilestone3.title);
        List<String> responseTitles = response.result.entities.stream()
                .map(entity -> entity.title)
                .toList();

        softAssert.assertTrue(responseTitles.containsAll(createdTitles), "All created milestones " +
                "titles should be in the response");
        softAssert.assertAll();
    }

    @Test
    @Story("Delete a milestone")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a milestone is deleted successfully and returns 404 when accessed")
    public void checkDeleteMilestone() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData createdMilestone = TestDataGenerator.generateMilestones(PROJECT_CODE);

        int milestoneId = createdMilestone.response.result.id;

        deleteMilestone(currentProjectCode, milestoneId);

        Response response = getMilestoneRaw(currentProjectCode, milestoneId);

        softAssert.assertEquals(response.statusCode(), 404, "Expected 404 after deleting milestone");
        softAssert.assertFalse(response.jsonPath().getBoolean("status"), "Status should be false after " +
                "deletion");
        softAssert.assertEquals(response.jsonPath().getString("errorMessage"), "Milestone not found");
        softAssert.assertAll();
    }
}
