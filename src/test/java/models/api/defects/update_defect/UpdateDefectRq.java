package models.api.defects.update_defect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDefectRq {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("actual_result")
    @Expose
    public String actual_result;
    @SerializedName("severity")
    @Expose
    public Integer severity;
    @SerializedName("milestoneId")
    @Expose
    public Integer milestoneId;
}
