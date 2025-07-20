package models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.api.defects.create_defect.CreateDefectRs;

@Data
@AllArgsConstructor
public class GeneratedDefectData {

    public CreateDefectRs response;
    public String title;
    public String actualResult;
    public int severity;
}
