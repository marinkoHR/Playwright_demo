package PAGES;

import MAPPINGS.SysKitHomePageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class SysKitHomePage extends SysKitHomePageMappings {

    public static void clickSignInButton(Page page) {
        ErrorHandler.errorWrapper("Click Sign In button on the SysKit home page", true, success -> {
            page.click(mSignInButton);
            return success;
        });
    }

    public static void verifySignInPageIsDisplayed(Page page) {
        ErrorHandler.errorWrapper("Verify SysKit home page is displayed", false, success -> {
            List<String> lsExceptions = new ArrayList<>();
            try {
                page.waitForSelector(mSignInButton);
            } catch (Exception e) {
                lsExceptions.add("SysKit home page isn't displayed (can't find Sign in button)!");
            }
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }
}
