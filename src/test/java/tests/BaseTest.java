package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Listeners;
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

    @BeforeMethod
    public void initDriver() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";

        loginPage = new LoginPage();
        homePage = new HomePage();


        auth = new AuthSteps(loginPage, homePage);
        project = new ProjectSteps(homePage);

        auth.login(email, password);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
