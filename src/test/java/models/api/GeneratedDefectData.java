package models.api;

import models.api.defects.create_defect.CreateDefectRs;

public class GeneratedDefectData {

    public CreateDefectRs response;
    public String title;
    public String actualResult;
    public int severity;

    public GeneratedDefectData(CreateDefectRs response, String title, String actualResult, int severity) {

        this.response = response;
        this.title = title;
        this.actualResult = actualResult;
        this.severity = severity;
    }
}

