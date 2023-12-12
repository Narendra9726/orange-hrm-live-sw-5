package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlistners.CustomListeners;
import com.orangehrmlive.demo.pages.HomePage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.ViewSystemUserPage;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LoginTest extends BaseTest
{
    //Declaration
    com.orangehrmlive.demo.pages.AddUserPage addUserPage;
    com.orangehrmlive.demo.pages.AdminPage adminPage;
    com.orangehrmlive.demo.pages.DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUserPage viewSystemUserPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt()
    {
        //Initialisation
        addUserPage= new com.orangehrmlive.demo.pages.AddUserPage();
        adminPage = new com.orangehrmlive.demo.pages.AdminPage();
        dashboardPage = new com.orangehrmlive.demo.pages.DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUserPage = new ViewSystemUserPage();
    }

    @Test(groups={"Sanity" , "Smoke" ,"Regression"})
    public void verifyUserShouldLoginSuccessfully(){
        //Enter username
        homePage.setEnterUserName("Admin");
        //   Enter password
        homePage.setEnterPassword("admin123");
        //   Click on Login Button
        loginPage.setClickOnLogin();
        //   Verify "Dashboard" Message
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage = "WelCome";
        Assert.assertEquals(actualMessage,expectedMessage,"WelCome Text is displayed");
    }

    @Test(groups = {"Smoke" , "Regression"})
    public void verifyThatTheLogoDisplayedOnLoginPage(){
        //Launch the application
        driver.getCurrentUrl();
        //   Verify Logo is Displayed
        homePage.setHrmLogo();
    }

    @Test(groups = {"Regression" })
    public void verifyUserShouldLogoutSuccessfully() throws InterruptedException{
        //Login To The application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();
        //   Click on User Profile logo
        adminPage.setClickOnUserProfileLogo();
        //   Mouse hover on "Logout" and click
        adminPage.setMouseHoverOnLogout();
        //   Verify the text "Login Panel" is displayed
        Thread.sleep(2000);
        String actualMessage = homePage.setVerifyLoginPanel();
        String expectedMessage = "Login";
        Assert.assertEquals(actualMessage,expectedMessage,"login message is not displayed");
    }

    @Test(groups = {"Regression" })
    public void verifyErrorMessageWithInvalidCredentials()
    {
        //Enter username
        homePage.setEnterUserName("naren123@yahoo.com");
        //   Enter password
        homePage.setEnterPassword("naren");
        //   Click on Login Button
        loginPage.setClickOnLogin();
        //   Verify "Dashboard" Message
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage ="Welcome  Text is not displayed";
        Assert.assertEquals(actualMessage,expectedMessage,"Welcome  Text is not displayed");
    }
}