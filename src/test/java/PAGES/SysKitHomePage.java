package PAGES;

import MAPPINGS.SysKitHomePageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;

public class SysKitHomePage extends SysKitHomePageMappings {

    public static void clickSignInButton(Page page) {
        ErrorHandler.errorWrapper("Click Sign In button on the SysKit home page", true, success -> {
            page.click(mSignInButton);
            return success;
        });
    }
}
