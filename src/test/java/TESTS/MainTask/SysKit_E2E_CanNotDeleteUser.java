package TESTS.MainTask;

import MAPPINGS.SysKitNavigationMappings;
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

public class SysKit_E2E_CanNotDeleteUser {

    Playwright playwright;
    Page page;
    Initialize initialize;

    @BeforeTest
    public void setUp() {
        initialize = new Initialize(Initialize.BrowserToOpen.CHROME, false);
        playwright = initialize.getPlaywright();
        page = initialize.getPage();
    }

    @Test
    public void SysKit_E2E_CanNotDeleteUser_Test() {

        page.navigate(Urls.sSysKitTest);

        //region Sign in to SysKit and navigate to Users page
        SysKitHomePage.clickSignInButton(page);
        SysKitSignInPage.logInToSysKit(page, Credentials.sUserNameAdele, Credentials.sPasswordAdele);
        SysKitDashboardPage.clickUsers(page);
        //endregion

        //region Search for a user in the table and get expected number of table rows
        if (SysKitUsersPage.TourPopUp.getIfTourPopUpIsDisplayed(page))
            SysKitUsersPage.TourPopUp.closeTourPopUp(page);
        String sUsername = SysKitUsersPage.getUsernameFromTheTable(page, 1);
        SysKitUsersPage.search(page, sUsername);
        int iExpectedNumberOfRows = SysKitUsersPage.getNumberOfTableRows(page);
        if (iExpectedNumberOfRows == 0 || iExpectedNumberOfRows > 3)
            Assert.fail("Expected number of table rows on the SysKit Users page is either too low or too high! "
                    + "Actual number of rows = " + iExpectedNumberOfRows);
        String sFullName = SysKitUsersPage.getDisplayNameFromTheTable(page, 0);
        //endregion

        //region Select first user in the table and delete it
        SysKitUsersPage.clickCheckboxInTheTable(page, 0);
        SysKitUsersPage.clickDeleteUser(page);
        SysKitUsersPage.DeleteUserPopUp.verifyPopUpContent(page, sFullName);
        SysKitUsersPage.DeleteUserPopUp.clickButtonDeleteUser(page);
        Calendar calTimestamp = Calendar.getInstance();
        //endregion

        //region Verify toast notification shows error and click on the link for details
        SysKitUsersPage.ToastNotification.verifyToastNotificationInProgressContent(page);
        SysKitUsersPage.ToastNotification.waitUntilError(page);
        SysKitUsersPage.ToastNotification.clickLinkToCheckDetails(page);
        //endregion

        //region Verify details for the failed Delete action on the Action Details page
        SysKitActionDetailsPage.verifyActionTitle(page, SysKitActionDetailPageStrings.sExpectedTitleForDeleteUser);
        SysKitActionDetailsPage.verifyPerformedBy(page, Credentials.sFullNameAdele);
        SysKitActionDetailsPage.verifyPerformedOn(page, calTimestamp);
        SysKitActionDetailsPage.verifyShortStatus(page, SysKitActionDetailPageStrings.sExpectedShortErrorStatus);
        SysKitActionDetailsPage.verifyJobDetails(page,
                SysKitActionDetailPageStrings.getJobDetailExpectedMessageForFailedDeletion(sFullName));
        SysKitActionDetailsPage.verifyStatusDetails(page,
                SysKitActionDetailPageStrings.sExpectedStatusDetailsForInsufficientPrivileges);
        //endregion

        //region Navigate to the Users page and verify selected user wasn't deleted
        SysKitNavigation.clickUsersButton(page);
        SysKitUsersPage.search(page, sUsername);
        SysKitUsersPage.verifyNumberOfTableRows(page, iExpectedNumberOfRows);
        SysKitUsersPage.verifyDisplayNameInTheTable(page, 0, sFullName);
        //endregion

        //region Sign out
        SysKitNavigation.clickProfile(page);
        SysKitNavigation.Profile.clickSignOut(page);
        MicrosoftSignOutPage.clickAccountSignOut(page);
        SysKitHomePage.verifySignInPageIsDisplayed(page);
        //endregion

        ErrorHandler.testEnd();
    }

    @AfterTest
    public void testEnd() {
        playwright.close();
    }
}
