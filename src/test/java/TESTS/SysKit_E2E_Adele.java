package TESTS;

import PAGES.*;
import STRINGS.Credentials;
import STRINGS.SysKitActionDetailPageStrings;
import STRINGS.Urls;
import TOOLS.ErrorHandler;
import TOOLS.Initialize;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;

public class SysKit_E2E_Adele {

    Playwright playwright;
    Page page;
    Initialize initialize;

    @BeforeTest
    public void setUp() {
        initialize = new Initialize(Initialize.BrowserToOpen.CHROME);
        playwright = initialize.getPlaywright();
        page = initialize.getPage();
    }

    @Test
    public void SysKit_E2E_Adele_Test() {

        page.navigate(Urls.sSysKitTest);

        SysKitHomePage.clickSignInButton(page);
        SysKitSignInPage.logInToSysKit(page, Credentials.sUserNameAdele, Credentials.sPasswordAdele);
        SysKitDashboardPage.clickUsers(page);

        if (SysKitUsersPage.TourPopUp.getIfTourPopUpIsDisplayed(page)) SysKitUsersPage.TourPopUp.closeTourPopUp(page);
        String sUsername = SysKitUsersPage.getUsernameFromTheTable(page, 1);
        SysKitUsersPage.search(page, sUsername);
        int iExpectedNumberOfRows = SysKitUsersPage.getNumberOfTableRows(page);
        if (iExpectedNumberOfRows == 0 || iExpectedNumberOfRows > 3)
            Assert.fail("Expected number of table rows on the SysKit Users page is either too low or too high! " +
                    "Actual number of rows = " + iExpectedNumberOfRows);
        String sFullName = SysKitUsersPage.getDisplayNameFromTheTable(page, 0);
        SysKitUsersPage.clickCheckboxInTheTable(page, 0);
        SysKitUsersPage.clickDeleteUser(page);

        SysKitUsersPage.DeleteUserPopUp.verifyPopUpContent(page, sFullName);
        SysKitUsersPage.DeleteUserPopUp.clickButtonDeleteUser(page);
        Calendar calTimestamp = Calendar.getInstance();

        SysKitUsersPage.Toastify.verifyToastifyInProgressContent(page);
        SysKitUsersPage.Toastify.waitUntilError(page);
        SysKitUsersPage.Toastify.clickLinkToCheckDetails(page);

        SysKitActionDetailsPage.verifyActionTitle(page, SysKitActionDetailPageStrings.sExpectedTitleForDeleteUser);
        SysKitActionDetailsPage.verifyPerformedBy(page, Credentials.sFullNameAdele);
        SysKitActionDetailsPage.verifyPerformedOn(page, calTimestamp);
        SysKitActionDetailsPage.verifyShortStatus(page, SysKitActionDetailPageStrings.sExpectedShortErrorStatus);
        SysKitActionDetailsPage.verifyJobDetails(page,
                SysKitActionDetailPageStrings.getJobDetailExpectedMessageForFailedDeletion(sFullName));
        SysKitActionDetailsPage.verifyStatusDetails(page,
                SysKitActionDetailPageStrings.sExpectedStatusDetailsForInsufficientPrivileges);

        SysKitActionDetailsPage.Navigation.clickUsersButton(page);

        SysKitUsersPage.search(page, sUsername);
        SysKitUsersPage.verifyNumberOfTableRows(page, iExpectedNumberOfRows);
    }

    @AfterTest
    public void testEnd() {
        ErrorHandler.testEnd();
        playwright.close();
    }
}
