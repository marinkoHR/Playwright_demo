package PAGES;

import MAPPINGS.MicrosoftSignOutPageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;

public class MicrosoftSignOutPage extends MicrosoftSignOutPageMappings {

    public static void clickAccountSignOut(Page page) {
        ErrorHandler.errorWrapper("Click Account sign out button on the Microsoft sign out page",
                success -> {
            page.click(mSignOutAccountButton);
            return success;
        });
    }
}
