package models.api.milestones.GetSpecificMilestone;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSpecificMilestoneRq {

    @Expose
    String code;
    @Expose
    String id;
}
