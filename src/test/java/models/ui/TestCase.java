package models.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TestCase {

    @Builder.Default
    private String title = "test";
    @Builder.Default
    private String status = "Actual";
    @Builder.Default
    private String severity = "Normal";
    @Builder.Default
    private String priority = "Medium";
    @Builder.Default
    private String type = "Other";
    @Builder.Default
    private String layer = "Not set";
    @Builder.Default
    private String flaky = "No";
    @Builder.Default
    private String milestone = "Not set";
    @Builder.Default
    private String behavior = "Not set";
    @Builder.Default
    private String automationStatus = "Manual";
}
