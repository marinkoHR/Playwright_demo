package PAGES;

import MAPPINGS.SysKitActionDetailsPageMappings;
import TOOLS.ErrorHandler;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SysKitActionDetailsPage extends SysKitActionDetailsPageMappings {

    public static void verifyActionTitle(Page page, String sExpectedTitle) {
        ErrorHandler.errorWrapper("Verify action title on the SysKit Action Details page", false, success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sActualValue = page.textContent(mActionDetailsTitle);
            if (!sActualValue.equals(sExpectedTitle))
                lsExceptions.add(
                        "Action title isn't as expected on the SysKit Action Details page!\nExpected: " + sExpectedTitle
                                + ", Actual: " + sActualValue);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void verifyPerformedBy(Page page, String sUserFullName) {
        ErrorHandler.errorWrapper("Verify value for 'Performed By' on the SysKit Action Details page", false,
                success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sActualValue = page.textContent(mPerformedBy);
            if (!sActualValue.equals(sUserFullName))
                lsExceptions.add(
                        "Value for 'Performed By' isn't as expected on the SysKit Action Details page!\nExpected: "
                                + sUserFullName + ", Actual: " + sActualValue);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void verifyPerformedOn(Page page, Calendar calExpectedTime) {
        ErrorHandler.errorWrapper("Verify value for 'Performed On' on the SysKit Action Details page", false,
                success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sFormat = "M/d/yyyy h:mm:ss aaa";

            Calendar calExpectedPlus = Calendar.getInstance();
            calExpectedPlus.setTime(calExpectedTime.getTime());
            calExpectedPlus.add(Calendar.MINUTE, 1);
            Calendar calExpectedMinus = Calendar.getInstance();
            calExpectedMinus.setTime(calExpectedTime.getTime());
            calExpectedMinus.add(Calendar.MINUTE, -1);

            String sActualValue = page.textContent(mPerformedOn);
            Calendar calActualTime = Calendar.getInstance();
            try {
                calActualTime.setTime(new SimpleDateFormat(sFormat).parse(sActualValue));
            } catch (ParseException e) {
                Assert.fail(e.getMessage());
            }

            if (!(calActualTime.after(calExpectedMinus) && calActualTime.before(calExpectedPlus)))
                lsExceptions.add(
                        "Value for 'Performed On' isn't as expected on the SysKit Action Details page!\nExpected: "
                                + calExpectedTime.getTime() + " (+/- 1 minute), Actual: " + calActualTime.getTime());
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void verifyShortStatus(Page page, String sExpectedStatus) {
        ErrorHandler.errorWrapper("Verify value for 'Status' (short) on the SysKit Action Details page", false,
                success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sActualValue = page.textContent(mShortStatus);
            if (!sActualValue.equals(sExpectedStatus))
                lsExceptions.add(
                        "Value for 'Status' (short) isn't as expected on the SysKit Action Details page!\nExpected: "
                                + sExpectedStatus + "\nActual: " + sActualValue);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void verifyJobDetails(Page page, String sExpectedValue) {
        ErrorHandler.errorWrapper("Verify value for 'Job Details' on the SysKit Action Details page", false,
                success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sActualValue = page.textContent(mJobDescription);
            if (!sActualValue.equals(sExpectedValue))
                lsExceptions.add(
                        "Value for 'Job Details' isn't as expected on the SysKit Action Details page!\nExpected: "
                                + sExpectedValue + "\nActual: " + sActualValue);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void verifyStatusDetails(Page page, String sExpectedValue) {
        ErrorHandler.errorWrapper("Verify value for 'Status' (long) on the SysKit Action Details page", false,
                success -> {
            List<String> lsExceptions = new ArrayList<>();
            String sActualValue = page.textContent(mStatusDetails);
            if (!sActualValue.equals(sExpectedValue))
                lsExceptions.add(
                        "Value for 'Status' (long) isn't as expected on the SysKit Action Details page!\nExpected: "
                                + sExpectedValue + "\nActual: " + sActualValue);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }
}
