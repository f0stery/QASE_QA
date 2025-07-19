package models.api.milestones.create_milestone;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMilestoneRq {
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String status;
    @Expose
    int due_date;
}
