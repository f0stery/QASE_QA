package adapters;

import io.restassured.response.Response;
import models.api.defects.get_all_defects.GetAllDefectRs;
import models.api.milestones.get_all_milestones.GetAllMilestonesRs;
import models.api.milestones.get_specific_milestone.GetSpecifiMilestoneRs;
import models.api.milestones.create_milestone.CreateMilestoneRq;
import models.api.milestones.create_milestone.CreateMilestoneRs;
import models.api.milestones.update_milestone.UpdateMilestoneRq;
import models.api.milestones.update_milestone.UpdateMilestoneRs;

public class MilestonesAPI extends BaseAPI {

    public static CreateMilestoneRs createMilestone(CreateMilestoneRq milestoneRq, String projectCode) {
        return post(milestoneRq, milestonesAPIUrl + projectCode, CreateMilestoneRs.class);
    }
    public static GetSpecifiMilestoneRs getMilestone(String projectCode, int milestoneId) {
        return get(milestonesAPIUrl + projectCode + "/" + milestoneId, GetSpecifiMilestoneRs.class);
    }

    public static GetAllMilestonesRs getAllMilestones(String projectCode) {
        return get(milestonesAPIUrl + projectCode + "/" + GET_VALUES, GetAllMilestonesRs.class);
    }

    public static UpdateMilestoneRs updateMilestones(UpdateMilestoneRq milestoneRq, String projectCode, int milestoneId) {
        return patch(milestoneRq, milestonesAPIUrl + projectCode + "/" + milestoneId, UpdateMilestoneRs.class);
    }

    public static void deleteMilestone(String projectCode, int milestoneId) {
        delete(milestonesAPIUrl + projectCode + "/" + milestoneId);
    }

    public static Response getMilestoneRaw(String projectCode, int milestoneId) {
        return spec
                .when()
                .get(baseAPIUrl + milestonesAPIUrl + projectCode + "/" + milestoneId);
    }
}
