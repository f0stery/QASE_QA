package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.HomePage;
import pages.ProjectPage;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    ProjectPage projectPage;

    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeSuite
    public void globalSetup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

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

        authorization();
    }

    public HomePage authorization() {
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .isPageOpened();
        return new HomePage();
    }

    public ProjectPage createAndOpenProject(String name, String code) {
        projectPage = homePage.createPublicProject(name, code)
                .verifyProjectCreated(code, name)
                .openPage()
                .isPageOpened();
        return projectPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
