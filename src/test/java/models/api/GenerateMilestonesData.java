package models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.api.milestones.create_milestone.CreateMilestoneRs;

@Data
@AllArgsConstructor
public class GenerateMilestonesData {

    public CreateMilestoneRs response;
    public String title;
    public String description;
    public String status;
    public long due_date;

}
