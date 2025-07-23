package models.api.defects.get_specific_defect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSpecificDefectRs {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}
