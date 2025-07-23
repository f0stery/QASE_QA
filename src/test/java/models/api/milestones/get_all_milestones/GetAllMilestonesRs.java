package models.api.milestones.get_all_milestones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllMilestonesRs {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("result")
    @Expose
    public Result result;
}
