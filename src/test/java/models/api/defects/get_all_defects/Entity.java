package models.api.defects.get_all_defects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("actual_result")
    @Expose
    public String actualResult;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("milestone_id")
    @Expose
    public Object milestoneId;
    @SerializedName("severity")
    @Expose
    public String severity;
    @SerializedName("member_id")
    @Expose
    public Integer memberId;
    @SerializedName("author_id")
    @Expose
    public Integer authorId;
    @SerializedName("attachments")
    @Expose
    public List<Object> attachments;
    @SerializedName("custom_fields")
    @Expose
    public List<Object> customFields;
    @SerializedName("external_data")
    @Expose
    public String externalData;
    @SerializedName("runs")
    @Expose
    public List<Object> runs;
    @SerializedName("results")
    @Expose
    public List<Object> results;
    @SerializedName("resolved_at")
    @Expose
    public Object resolvedAt;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("updated")
    @Expose
    public String updated;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("tags")
    @Expose
    public List<Object> tags;
}
