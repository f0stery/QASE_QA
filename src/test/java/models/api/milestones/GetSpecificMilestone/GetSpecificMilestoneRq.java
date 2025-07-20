package models.api.milestones.GetSpecificMilestone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSpecificMilestoneRq {

    @SerializedName("code")
    @Expose
    String code;
    @SerializedName("id")
    @Expose
    int id;
}