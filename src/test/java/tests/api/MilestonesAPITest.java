package tests.api;


import models.api.GenerateMilestonesData;
import models.api.milestones.GetSpecificMilestone.GetSpecifiMilestoneRs;
import models.api.milestones.GetSpecificMilestone.Result;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

import static adapters.MilestonesAPI.getMilestone;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MilestonesAPITest extends BaseAPITest {

    private final String PROJECT_NAME = "SauseDemo";
    private final String PROJECT_CODE = "SA";

    @Test
    public void checkCreateMilestone() {
        createNewProject(PROJECT_NAME, PROJECT_CODE);

        GenerateMilestonesData milestonesRs = TestDataGenerator.generateMilestones(PROJECT_CODE);

        softAssert.assertTrue(milestonesRs.response.status, "Milestone creation should return true status");
        softAssert.assertNotNull(milestonesRs.response.result.id, "Milestone ID should not be null");
        softAssert.assertAll();
    }

    @Test
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
}
