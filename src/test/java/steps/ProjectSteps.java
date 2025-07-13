package steps;

import io.qameta.allure.Step;
import lombok.Getter;
import pages.HomePage;
import pages.ProjectPage;

public class ProjectSteps {

    HomePage homePage;
    @Getter
    ProjectPage projectPage;

    public ProjectSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @Step
    public ProjectPage createAndOpenProject(String name, String code) {
        projectPage = homePage.createPublicProject(name, code);
        return projectPage;
    }

    @Step
    public ProjectPage createAndOpenPrivateProject(String name, String code) {
        projectPage = homePage.createPrivateProject(name, code);
        return projectPage;
    }
}
