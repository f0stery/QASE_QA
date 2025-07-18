package models.api.create_defect;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDefectRq {

    @Expose
    String title;
    @Expose
    String actual_result;
    @Expose
    int severity;
    @Expose
    int milestoneId;
}
