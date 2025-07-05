package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectsPage {

    private final By PROJECT_NAME = By.id("project-name");
    private final By CREATE_NEW_PROJECT_BUTTON = byText("Create new project");
    private final By PUBLIC_PROJECT = byText("Public");
    private final By NOT_ADD_MEMBERS = byText("Don't add members");
    private final By CREATE_PROJECT_BUTTON = byText("Create project");
    private final By PRIVATE_PROJECT = byText("Private");

    public void openPage() {
        open("/projects");
        log.info("Project page opening");
    }

    public boolean waitTillOpened() {
        try {
            $(CREATE_NEW_PROJECT_BUTTON).shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project page isn't opened");
        }
        return false;
    }

    public void createPublicProject(String project) {
        log.info("Click on create new project button");
        $(CREATE_NEW_PROJECT_BUTTON).click();
        log.info("Set Project name: '{}'", project);
        $(PROJECT_NAME).setValue(project);
        log.info("Set Public group for project");
        $(PUBLIC_PROJECT).click();
        log.info("Click create project button");
        $(CREATE_PROJECT_BUTTON).click();
        try {
            $(PROJECT_NAME).shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not created");
        }

    }

    public void createPrivateProject(String project) {
        log.info("Click on create new project button");
        $(CREATE_NEW_PROJECT_BUTTON).click();
        log.info("Set Project name: '{}'", project);
        $(PROJECT_NAME).setValue(project);
        log.info("Set Private group for project");
        $(PRIVATE_PROJECT).click();
        log.info("Set Member access: Don't add members");
        $(NOT_ADD_MEMBERS).click();
        log.info("Click create project button");
        $(CREATE_PROJECT_BUTTON).click();
        try {
            $(PROJECT_NAME).shouldBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not created");
        }

    }

    public void deleteProject(String project) {
        log.info("Open action menu");
        $x(String.format("//a[text()='%s']/ancestor::tr//button[@aria-label='Open action menu']", project)).click();
        log.info("Find action menu for project '{}'", project);
        $(byText(project))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        log.info("Click 'remove' button in action menu'{}'", project);
        $("[data-testid=remove]").click();
        log.info("Click 'Delete project' button for approving");
        $x("//span[text()='Delete project']").click();
        try {
            $(PROJECT_NAME).shouldNotBe(visible);
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Project is not deleted");
        }
    }
}
