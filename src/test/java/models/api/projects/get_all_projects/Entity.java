package models.api.projects.get_all_projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("counts")
    @Expose
    public Counts counts;
}
