package utils;

import adapters.DefectAPI;
import adapters.MilestonesAPI;
import com.github.javafaker.Faker;
import models.api.GenerateMilestonesData;
import models.api.GeneratedDefectData;
import models.api.defects.create_defect.CreateDefectRq;
import models.api.defects.create_defect.CreateDefectRs;
import models.api.milestones.create_milestone.CreateMilestoneRq;
import models.api.milestones.create_milestone.CreateMilestoneRs;

import java.time.Instant;
import java.util.Random;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    public static String generateProjectName(String prefix) {
        return prefix + "_" + new Random().nextInt(1000);
    }

    public static String generateProjectCode(String name) {
        return name.replace("_", "")
                .toUpperCase();
    }

    public static String generateCodeAfterPrefix(String name, String prefix) {
        return name.replace(prefix, "").replace("_", "");
    }

    public static String generateTitle() {
        return faker.lorem().sentence();
    }

    public static String generateActualResult() {
        return faker.lorem().paragraph();
    }

    public static String generateDescription() {
        return faker.company().catchPhrase();
    }

    public static int generateSeverity() {
        return new Random().nextInt(6) + 1;
    }

    public static String getSeverityLabel(int severityCode) {
        return switch (severityCode) {
            case 1 -> "blocker";
            case 2 -> "critical";
            case 3 -> "major";
            case 4 -> "normal";
            case 5 -> "minor";
            case 6 -> "trivial";
            default -> "not set";
        };
    }

    public static int generateMilestoneId() {
        return new Random().nextInt(100);
    }

    public static GeneratedDefectData generateDefect(String projectCode) {
        String title = generateTitle();
        String actualResult = generateActualResult();
        int severity = generateSeverity();

        CreateDefectRq defectRq = CreateDefectRq.builder()
                .title(title)
                .actual_result(actualResult)
                .severity(severity)
                .milestoneId(generateMilestoneId())
                .build();

        CreateDefectRs response = DefectAPI.createDefect(defectRq, projectCode);

        return new GeneratedDefectData(response, title, actualResult, severity);
    }

    public static GenerateMilestonesData generateMilestones(String projectCode) {
        String title = generateTitle();
        String description = generateDescription();
        String status = "active";
        long due_data = Instant.now().getEpochSecond();

        CreateMilestoneRq milestoneRq = CreateMilestoneRq.builder()
                .title(title)
                .description(description)
                .status(status)
                .due_date((int) due_data)
                .build();

        CreateMilestoneRs response = MilestonesAPI.createMilestone(milestoneRq, projectCode);

        return new GenerateMilestonesData(response, title, description, status, due_data);
    }
}
