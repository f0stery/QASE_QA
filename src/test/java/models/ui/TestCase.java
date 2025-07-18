package models.ui;

import lombok.Getter;

@Getter
public class TestCase {

    private String title;
    private String status;
    private String severity;
    private String priority;
    private String type;
    private String layer;
    private String flaky;
    private String milestone;
    private String behavior;
    private String automationStatus;

    public TestCase(String title, String status,
                    String severity, String priority,
                    String type, String layer,
                    String flaky, String milestone,
                    String behavior, String automationStatus) {
        this.title = title;
        this.status = status;
        this.severity = severity;
        this.priority = priority;
        this.type = type;
        this.layer = layer;
        this.flaky = flaky;
        this.milestone = milestone;
        this.behavior = behavior;
        this.automationStatus = automationStatus;
    }

}
