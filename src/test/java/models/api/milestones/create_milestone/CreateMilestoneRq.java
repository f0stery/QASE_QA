package models.api.milestones.create_milestone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMilestoneRq {

    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("due_date")
    @Expose
    long due_date;
}
