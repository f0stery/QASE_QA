package models.api;

import models.api.milestones.create_milestone.CreateMilestoneRs;

import java.time.Instant;

public class GenerateMilestonesData {

    public CreateMilestoneRs response;
    public String title;
    public String description;
    public String status;
    public long due_date;

    public GenerateMilestonesData(CreateMilestoneRs response, String title,
                                  String description, String status, long due_date) {

        this.response = response;
        this.title = title;
        this.description = description;
        this.status = status;
        this.due_date = due_date;
    }
}
