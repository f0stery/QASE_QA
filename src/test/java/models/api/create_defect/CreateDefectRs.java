package models.api.create_defect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateDefectRs {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}
