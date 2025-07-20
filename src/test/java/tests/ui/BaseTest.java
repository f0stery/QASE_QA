package tests.ui;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import models.ui.TestCase;
import models.ui.TestCaseFactory;
import org.testng.annotations.*;
import com.codeborne.selenide.Configuration;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.HomePage;
import pages.ProjectPage;
import steps.AuthSteps;
import steps.ProjectSteps;
import utils.TestListener;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    ProjectPage projectPage;
    TestCase testCase = TestCaseFactory.getTestCase();

    AuthSteps auth;
    ProjectSteps project;
    SoftAssert SoftAssert = new SoftAssert();

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeClass
    @Parameters("browser")
    public void initDriver(@Optional("chrome") String browser) {

        WebDriverRunner.closeWebDriver();

        Configuration.browser = browser;
        log.info("INITIALIZING BROWSER: {}", browser);

        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";

        log.info("CONFIGURED browser: {}", Configuration.browser);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        loginPage = new LoginPage();
        homePage = new HomePage();

        auth = new AuthSteps(loginPage);
        project = new ProjectSteps(homePage);

        auth.login(email, password);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
