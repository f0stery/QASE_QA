package models.api.defects.create_defect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDefectRq {

    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("actual_result")
    @Expose
    String actual_result;
    @SerializedName("severity")
    @Expose
    int severity;
    @SerializedName("milestoneId")
    @Expose
    int milestoneId;
}
