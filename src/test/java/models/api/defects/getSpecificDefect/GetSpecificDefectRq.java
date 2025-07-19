package models.api.defects.getSpecificDefect;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSpecificDefectRq {
    @Expose
    public String title;
    @Expose
    public String actual_result;
    @Expose
    public Integer severity;
    @Expose
    public Integer milestoneId;
}
