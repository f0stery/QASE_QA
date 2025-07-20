package models.api.projects.get_all_projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Defects {
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("open")
    @Expose
    public Integer open;
}
