package adapters;

import models.api.defects.getSpecificDefect.GetSpecificDefectRs;
import models.api.defects.get_all_defects.GetAllDefectRs;
import models.api.defects.update_defect.UpdateDefectRq;
import models.api.defects.update_defect.UpdateDefectRs;
import models.api.milestones.GetSpecificMilestone.GetSpecifiMilestoneRs;
import models.api.milestones.create_milestone.CreateMilestoneRq;
import models.api.milestones.create_milestone.CreateMilestoneRs;

public class MilestonesAPI extends BaseAPI {

    public static CreateMilestoneRs createMilestone(CreateMilestoneRq milestoneRq, String projectCode) {
        return post(milestoneRq, milestonesAPIUrl + projectCode, CreateMilestoneRs.class);
    }
    public static GetSpecifiMilestoneRs getMilestone(String projectCode, int milestoneId) {
        return get(milestonesAPIUrl + projectCode + "/" + milestoneId, GetSpecifiMilestoneRs.class);
    }

    public static GetAllDefectRs getAllDefects(String projectCode) {
        return get(defectAPIUrl + projectCode + "/" + GET_VALUES, GetAllDefectRs.class);
    }

    public static UpdateDefectRs updateDefect(UpdateDefectRq defectRq, String projectCode, int defectId) {
        return patch(defectRq, defectAPIUrl + projectCode + "/" + defectId, UpdateDefectRs.class);
    }

    public static void deleteDefect(String projectCode, int defectId) {
        delete(defectAPIUrl + projectCode + "/" + defectId);
    }
}
