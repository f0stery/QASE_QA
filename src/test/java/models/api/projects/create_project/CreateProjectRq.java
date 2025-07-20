package models.api.projects.create_project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectRq {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("code")
    @Expose
    String code;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("access")
    @Expose
    String access;
    @SerializedName("group")
    @Expose
    String group;
}
