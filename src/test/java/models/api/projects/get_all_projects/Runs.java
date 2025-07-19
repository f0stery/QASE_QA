package models.api.projects.get_all_projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Runs {
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("active")
    @Expose
    public Integer active;
}
