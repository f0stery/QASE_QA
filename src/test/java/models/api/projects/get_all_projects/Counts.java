package models.api.projects.get_all_projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Counts {
    @SerializedName("cases")
    @Expose
    public Integer cases;
    @SerializedName("suites")
    @Expose
    public Integer suites;
    @SerializedName("milestones")
    @Expose
    public Integer milestones;
    @SerializedName("runs")
    @Expose
    public Runs runs;
    @SerializedName("defects")
    @Expose
    public Defects defects;
}
