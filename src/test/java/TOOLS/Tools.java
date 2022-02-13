package TOOLS;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.concurrent.TimeUnit;

public class Tools {

    public static String sTerminalColorRed = "\033[0;31m";
    public static String sTerminalColorGreen = "\033[0;32m";
    public static String sTerminalColorReset = "\033[0m";

    public static String mLoading = "div.loading-component";

    public static void delay(int iSeconds) {
        try {
            TimeUnit.SECONDS.sleep(iSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clickOnCoordinates(Page page, String sSelector) {
        BoundingBox box = page.querySelector(sSelector).boundingBox();
        page.mouse().click(box.x + box.width / 2, box.y + box.height / 2);
    }

    public static void waitForLoading(Page page) {
        try {
            delay(2);
            page.waitForSelector(mLoading, new Page.WaitForSelectorOptions().setTimeout(2000));
        } catch (Exception e) {
            page.waitForSelector(mLoading, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }
}
