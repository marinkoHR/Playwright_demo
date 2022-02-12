package PAGES;

import MAPPINGS.SysKitDashboardPageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;

public class SysKitDashboardPage extends SysKitDashboardPageMappings {

    public static void clickUsers(Page page) {
        ErrorHandler.errorWrapper("Click on button Users on the SysKit dashboard", true, success -> {
            page.click(mButtonUsers);
            return success;
        });
    }
}
