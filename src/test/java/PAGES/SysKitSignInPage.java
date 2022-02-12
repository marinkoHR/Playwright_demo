package PAGES;

import MAPPINGS.SysKitSignInPageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class SysKitSignInPage extends SysKitSignInPageMappings {

    public static void logInToSysKit(Page page, String sUsername, String sPassword) {
        ErrorHandler.errorWrapper("Log in to SysKit", true, success -> {
            page.fill(mEmailField, sUsername);
            page.click(mButtonNext);
            page.fill(mPassword, sPassword);
            page.click(mButtonSignIn);
            page.waitForLoadState(LoadState.NETWORKIDLE);
            if (page.isVisible(mDoNotShowAgainCheckbox)) page.click(mButtonNoForStaySignedIn);
            return success;
        });
    }
}
