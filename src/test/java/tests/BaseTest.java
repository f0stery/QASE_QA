package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ProjectsPage;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
@Log4j2
public class BaseTest {

    LoginPage loginPage;
    ProjectsPage projectsPage;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "Chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.clickViaJs = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @AfterMethod
    public void tearDown() {
        getWebDriver().quit();
    }
}
