package models.api.defects.get_all_defects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllDefectRs {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}
