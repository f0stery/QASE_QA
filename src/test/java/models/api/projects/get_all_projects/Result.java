package models.api.projects.get_all_projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("filtered")
    @Expose
    public Integer filtered;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("entities")
    @Expose
    public List<Entity> entities;
}
