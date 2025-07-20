package models.api.defects.update_defect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @SerializedName("id")
    @Expose
    public Integer id;
}
