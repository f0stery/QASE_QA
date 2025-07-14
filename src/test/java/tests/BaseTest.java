package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import com.codeborne.selenide.Configuration;
import pages.LoginPage;
import pages.HomePage;
import pages.ProjectPage;
import steps.AuthSteps;
import steps.ProjectSteps;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    ProjectPage projectPage;

    AuthSteps auth;
    ProjectSteps project;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeClass
    @Parameters("browser")
    public void initDriver(@Optional("chrome") String browser) {

        System.out.println("TEST PARAMETER browser: " + browser);

        Configuration.browser = browser;
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";
        Configuration.remote = System.getProperty("remote.url");

        System.out.println("CONFIGURED browser: " + Configuration.browser);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        auth = new AuthSteps(loginPage, homePage);
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
