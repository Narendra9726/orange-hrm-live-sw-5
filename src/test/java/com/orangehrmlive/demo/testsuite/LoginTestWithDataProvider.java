package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.pages.HomePage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.ViewSystemUserPage;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.testdata.TestDataForUsersTest;


public class LoginTestWithDataProvider extends BaseTest
{
    com.orangehrmlive.demo.pages.AddUserPage addUserPage;
    com.orangehrmlive.demo.pages.AdminPage adminPage;
    com.orangehrmlive.demo.pages.DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUserPage viewSystemUserPage;

    @Test(dataProvider = "data set" , dataProviderClass = TestDataForUsersTest.class)
    public void verifyErrorMessageWithInvalidCredentials(String userName , String password , String errorMessage)
    {
        //Enter username
        homePage.setEnterUserName(userName);
        //   Enter password
        homePage.setEnterPassword(password);
        //   Click on Login Button
        loginPage.setClickOnLogin();
        //   Verify "Dashboard" Message
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage = errorMessage;
        Assert.assertEquals(actualMessage,expectedMessage,"Welcome  Text is not displayed");
    }

}