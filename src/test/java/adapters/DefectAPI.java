package adapters;

import io.restassured.response.Response;
import models.api.defects.create_defect.CreateDefectRq;
import models.api.defects.create_defect.CreateDefectRs;
import models.api.defects.get_specific_defect.GetSpecificDefectRs;
import models.api.defects.get_all_defects.GetAllDefectRs;
import models.api.defects.update_defect.UpdateDefectRq;
import models.api.defects.update_defect.UpdateDefectRs;

public class DefectAPI extends BaseAPI {

    public static CreateDefectRs createDefect(CreateDefectRq defectRq, String projectCode) {
        return post(defectRq, defectAPIUrl + projectCode, CreateDefectRs.class);
    }

    public static GetSpecificDefectRs getDefect(String projectCode, int defectId) {
        return get(defectAPIUrl + projectCode + "/" + defectId, GetSpecificDefectRs.class);
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

    public static Response getDefectRaw(String projectCode, int defectId) {
        return spec
                .when()
                .get(baseAPIUrl + defectAPIUrl + projectCode + "/" + defectId);
    }
}

