package TESTS.AdditionalTask;

import PAGES.*;
import STRINGS.Credentials;
import STRINGS.Urls;
import TOOLS.ErrorHandler;
import TOOLS.Initialize;
import TOOLS.Tools;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;

public class SysKit_E2E_AddEditAndDisableAlert {

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
    public void SysKit_E2E_AddEditAndDisableAlert_Test() {

        page.navigate(Urls.sSysKitTest);

        //region Sign in to SysKit and navigate to Alerts page
        SysKitHomePage.clickSignInButton(page);
        SysKitSignInPage.logInToSysKit(page, Credentials.sUserNameAdele, Credentials.sPasswordAdele);
        SysKitDashboardPage.clickAlerts(page);
        //endregion

        //region Enable Alerts for 'Changed Private Group to Public'
        int iExpectedNumberOfRows = SysKitAlertsPage.getNumberOfTableRows(page);
        iExpectedNumberOfRows++;
        SysKitAlertsPage.clickAddAlert(page);
        SysKitAlertsPage.ConfigureAlerts.clickPrivacyChange(page);
        SysKitAlertsPage.ConfigureAlerts.clickChangedPrivateGroupToPrivate(page);
        SysKitAlertsPage.ConfigureAlerts.changeToggleState(page, SysKitAlertsPage.ConfigureAlerts.Toggle.ON);
        //endregion

        //region Modify new Alert and save
        SysKitAlertsPage.ConfigureAlerts.ChangedPrivateGroupToPublic.Notifications.clickSysKitPointAdministratorsCheckbox(page, false);
        SysKitAlertsPage.ConfigureAlerts.ChangedPrivateGroupToPublic.Notifications.clickCustomRecipientsCheckbox(page
                , true);
        SysKitAlertsPage.ConfigureAlerts.ChangedPrivateGroupToPublic.Notifications.insertCustomRecipient(page,
                Credentials.sUserNameAdele);
        SysKitAlertsPage.ConfigureAlerts.clickSave(page);
        Calendar calModifiedOn = Calendar.getInstance();
        //endregion

        //region Verify Alert in the table
        SysKitAlertsPage.verifyNumberOfTableRows(page, iExpectedNumberOfRows);
        SysKitAlertsPage.verifyValuesInTheTableRow(page, 0, "Custom Recipients", Credentials.sUserNameAdele,
                Credentials.sFullNameAdele, calModifiedOn);
        //endregion

        //region Modify existing Alert and save
        SysKitAlertsPage.clickCheckboxInTheTable(page, 0);
        SysKitAlertsPage.clickEditAlert(page);
        SysKitAlertsPage.EditAlert.Notifications.clickSysKitPointAdministratorsCheckbox(page, true);
        SysKitAlertsPage.EditAlert.Notifications.removeAllCustomRecipientsFromInput(page);
        SysKitAlertsPage.EditAlert.Notifications.clickCustomRecipientsCheckbox(page, false);
        SysKitAlertsPage.EditAlert.clickSave(page);
        calModifiedOn = Calendar.getInstance();
        //endregion

        //region Verify Alert in the table
        Tools.waitForLoading(page);
        SysKitAlertsPage.verifyNumberOfTableRows(page, iExpectedNumberOfRows);
        SysKitAlertsPage.verifyValuesInTheTableRow(page, 0, "SysKit Point Administrators", "",
                Credentials.sFullNameAdele, calModifiedOn);
        //endregion

        //region Disable Alert and verify it is not displayed on the Alerts table
        SysKitAlertsPage.clickCheckboxInTheTable(page, 0);
        SysKitAlertsPage.clickDisableAlert(page);
        SysKitAlertsPage.DisableAlert.clickDisable(page);
        iExpectedNumberOfRows--;
        Tools.waitForLoading(page);
        SysKitAlertsPage.verifyNumberOfTableRows(page, iExpectedNumberOfRows);
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
