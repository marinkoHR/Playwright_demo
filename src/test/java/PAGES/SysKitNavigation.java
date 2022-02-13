package PAGES;

import MAPPINGS.SysKitNavigationMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;

public class SysKitNavigation extends SysKitNavigationMappings {

    public static class Profile extends SysKitNavigationMappings.Profile {

        public static void clickSignOut(Page page) {
            ErrorHandler.errorWrapper("Click 'Sign out' button on the Profile dropdown on the navigation", success -> {
                if (page.isVisible(mSignOut))
                    page.click(mSignOut);
                else {
                    clickProfile(page);
                    page.click(mSignOut);
                }
                return success;
            });
        }
    }

    public static void clickUsersButton(Page page) {
        ErrorHandler.errorWrapper("Navigate to Users page from navigation", success -> {
            page.hover(mHomeButton);
            page.click(mUsersNavigationButton);
            return success;
        });
    }

    public static void clickProfile(Page page) {
        ErrorHandler.errorWrapper("Click Profile button on the navigation", success -> {
            page.click(mProfileImage);
            return success;
        });
    }
}
