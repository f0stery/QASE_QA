package models.api.defects.create_defect;

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

    private CreateDefectRq generateDefect(String title, String actualResult, int severity, int milestoneId) {
        return CreateDefectRq.builder()
                .title(title)
                .actual_result(actualResult)
                .severity(severity)
                .milestoneId(milestoneId)
                .build();
    }
}
