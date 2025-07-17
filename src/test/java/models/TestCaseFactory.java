package models;

import com.github.javafaker.Faker;

public class TestCaseFactory {

    public static TestCase getTestCase() {
        Faker faker = new Faker();

        String titleTestcase = faker.options().option(
                "Login", "Checkout",
                "Search suite", "Edit suite",
                "Added suite","Logout");
        String status = faker.options().option(
                "Actual", "Draft",
                "Deprecated");
        String severity = faker.options().option(
                "Normal", "Blocker",
                "Critical", "Major",
                "Minor", "Trivial");
        String priority = faker.options().option(
                "High", "Medium", "Low");
        String type = faker.options().option(
                "Functional", "Smoke",
                "Regression", "Security",
                "Usability", "Performance",
                "Acceptance", "Compatibility",
                "Integration", "Exploratory");
        String layer = faker.options().option(
                "E2E", "API", "Unit");
        String flaky = faker.options().option(
                "Yes", "No");
        String milestone = faker.options().option(
                "Not set");
        String behavior = faker.options().option(
                "Positive", "Negative", "Destructive");
        String automationStatus = faker.options().option(
                "Manual", "Automated");

        return new TestCase(titleTestcase, status, severity, priority, type, layer, flaky,
                milestone, behavior, automationStatus);
    }
}
