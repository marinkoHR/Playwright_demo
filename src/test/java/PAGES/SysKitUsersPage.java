package PAGES;

import MAPPINGS.SysKitUsersPageMappings;
import TOOLS.ErrorHandler;
import TOOLS.Tools;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.List;

public class SysKitUsersPage extends SysKitUsersPageMappings {

    public static class TourPopUp extends SysKitUsersPageMappings.TourPopUp {

        public static boolean getIfTourPopUpIsDisplayed(Page page) {
            final boolean[] bIsDisplayed = {false};
            ErrorHandler.errorWrapper("Get if Tour pop-up on the SysKit Users page is displayed", false, success -> {
                page.waitForLoadState(LoadState.NETWORKIDLE);
                bIsDisplayed[0] = page.isVisible(mSkipTourButton);
                return success;
            });
            return bIsDisplayed[0];
        }

        public static void closeTourPopUp(Page page) {
            ErrorHandler.errorWrapper("Close Tour pop-up on the SysKit Users page", success -> {
                page.click(mSkipTourButton);
                return success;
            });
        }
    }

    public static class DeleteUserPopUp extends SysKitUsersPageMappings.DeleteUserPopUp {

        public static void verifyPopUpContent(Page page, String sUserFullName) {
            ErrorHandler.errorWrapper("Verify content of the 'Delete User' pop-up on the SysKit Users page",
                    false, success -> {
                List<String> lsExceptions = new ArrayList<>();
                if (!page.isVisible(mContent))
                    lsExceptions.add("'Delete User' pop-up isn't displayed on the SysKit Users page!");
                else {
                    String sActualTitle = page.querySelector(mTitle).textContent();
                    if (!sActualTitle.equals(sExpectedTitle))
                        lsExceptions.add("Title of the 'Delete User' pop-up isn't as expected!\nExpected: " + sExpectedTitle + ", Actual: " + sActualTitle);
                    String sExpectedContent = sExpectedParagraphPart1 + sUserFullName + sExpectedParagraphPart2;
                    String sActualContent = page.querySelector(mContent).innerText();
                    if (!sActualContent.equals(sExpectedContent))
                        lsExceptions.add("Content of the 'Delete User' pop-up isn't as expected!\nExpected:\n" + sExpectedContent + "\nActual:\n" + sActualContent);
                }
                if (lsExceptions.size() > 0)
                    success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
                return success;
            });
        }

        public static void clickButtonDeleteUser(Page page) {
            ErrorHandler.errorWrapper("Click button 'Delete User' on the 'Delete User' pop-up on the SysKit Users " +
                    "page", success -> {
                page.click(mButtonDeleteUser);
                return success;
            });
        }
    }

    public static class Toastify extends SysKitUsersPageMappings.Toastify {

        public static void verifyToastifyInProgressContent(Page page) {
            ErrorHandler.errorWrapper("Verify content of the Toastify (in progress) on the SysKit Users page",
                    false, success -> {
                List<String> lsExceptions = new ArrayList<>();
                page.waitForSelector(mToastifyTitle, new Page.WaitForSelectorOptions().setTimeout(10000));
                if (!page.isVisible(mToastifyTitle))
                    lsExceptions.add("Toastify (in progress) isn't displayed on the SysKit Users page!");
                else {
                    String sActualTitle = page.querySelector(mToastifyTitle).textContent();
                    if (!sActualTitle.equals(sExpectedTitleDeleteUser))
                        lsExceptions.add("Title of the Toastify (in progress) isn't as expected!\nExpected: " + sExpectedTitleDeleteUser + ", Actual: " + sActualTitle);
                    String sActualContent = page.querySelector(mToastifyMessage).textContent();
                    if (!sActualContent.equals(sExpectedMessageDeleteUserProgress))
                        lsExceptions.add("Content of the Toastify (in progress) isn't as expected!\nExpected:\n" + sExpectedMessageDeleteUserProgress + "\nActual:\n" + sActualContent);
                }
                if (lsExceptions.size() > 0)
                    success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
                return success;
            });
        }

        public static void waitUntilError(Page page) {
            ErrorHandler.errorWrapper("Wait until the Toastify (completed) display the error message on the SysKit " +
                    "Users page", true, success -> {
                List<String> lsExceptions = new ArrayList<>();
                String sContent;
                int iRepeat = 0;
                do {
                    Tools.delay(1);
                    sContent = page.waitForSelector(mToastifyMessage).textContent();
                    iRepeat++;
                } while (!sContent.equals(sExpectedMessageDeleteUserErrors) && iRepeat < 15);
                boolean bSuccess = sContent.equals(sExpectedMessageDeleteUserErrors);
                if (!bSuccess)
                    lsExceptions.add("Toastify (completed) with error message isn't displayed on the SysKit Users " +
                            "page!");
                if (lsExceptions.size() > 0)
                    success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
                return success;
            });
        }

        public static void clickLinkToCheckDetails(Page page) {
            ErrorHandler.errorWrapper("Click 'Check details' link on the toastify on the SysKit Users page",
                    success -> {
                page.click(mToastifyMessageLink);
                return success;
            });
        }
    }

    public static void search(Page page, String sValue) {
        ErrorHandler.errorWrapper("Search for '" + sValue + "' on the SysKit Users page", false, success -> {
            page.fill(mTableSearchField, sValue);
            try {
                page.waitForSelector(mLoader, new Page.WaitForSelectorOptions().setTimeout(5000));
            } catch (Exception e) {}
            page.waitForSelector(mLoader, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
            return success;
        });
    }

    public static String getUsernameFromTheTable(Page page, int iRow) {
        final String[] sUsername = {""};
        ErrorHandler.errorWrapper("Get username from the table row " + (iRow + 1) + " on the SysKit Users page",
                false, success -> {
            page.waitForSelector(mTableRows);
            List<ElementHandle> lsTableRows = page.querySelectorAll(mTableRows);
            if (iRow > lsTableRows.size())
                success.exception = new Exception("\nRow " + (iRow + 1) + " doesn't exist in the table on the SysKit "
                        + "Users page! Total number of rows in the table is " + lsTableRows.size() + ".\n");
            else sUsername[0] = lsTableRows.get(iRow).querySelector(mTableUsernameColumn).innerText();
            return success;
        });
        return sUsername[0];
    }

    public static String getDisplayNameFromTheTable(Page page, int iRow) {
        final String[] sDisplayName = {""};
        ErrorHandler.errorWrapper("Get Display name from the table row " + (iRow + 1) + " on the SysKit Users page",
                false, success -> {
            page.waitForSelector(mTableRows);
            List<ElementHandle> lsTableRows = page.querySelectorAll(mTableRows);
            if (iRow > lsTableRows.size())
                success.exception = new Exception("\nRow " + (iRow + 1) + " doesn't exist in the table on the SysKit "
                        + "Users page! Total number of rows in the table is " + lsTableRows.size() + ".\n");
            else sDisplayName[0] = lsTableRows.get(iRow).querySelector(mTableDisplayNameColumn).innerText();
            return success;
        });
        return sDisplayName[0];
    }

    public static int getNumberOfTableRows(Page page) {
        final int[] iNumberOfRows = {0};
        ErrorHandler.errorWrapper("Get number of rows in the table on the SysKit Users page", false, success -> {
            page.waitForSelector(mTableRows);
            iNumberOfRows[0] = page.querySelectorAll(mTableRows).size();
            return success;
        });
        return iNumberOfRows[0];
    }

    public static void verifyNumberOfTableRows(Page page, int iExpectedNumberOfRows) {
        ErrorHandler.errorWrapper("Verify number of rows in the table on the SysKit Users page is '" + iExpectedNumberOfRows + "'", false, success -> {
            List<String> lsExceptions = new ArrayList<>();
            page.waitForSelector(mTableRows);
            int iActualNumberOfRows = page.querySelectorAll(mTableRows).size();
            if (iActualNumberOfRows != iExpectedNumberOfRows)
                lsExceptions.add("Number of rows in the table on the SysKit Users page isn't as expected!" +
                        "\nExpected: " + iExpectedNumberOfRows + ", Actual: " + iActualNumberOfRows);
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void clickCheckboxInTheTable(Page page, int iRow) {
        ErrorHandler.errorWrapper("Click checkbox in the row " + (iRow + 1) + " in the table on the SysKit Users " +
                "page", false, success -> {
            page.waitForSelector(mTableRows);
            List<ElementHandle> lsTableRows = page.querySelectorAll(mTableRows);
            if (iRow > lsTableRows.size())
                success.exception = new Exception("\nRow " + (iRow + 1) + " doesn't exist in the table on the SysKit "
                        + "Users page! Total number of rows in the table is " + lsTableRows.size() + ".\n");
            else lsTableRows.get(iRow).querySelector(mTableCheckbox).click();
            return success;
        });
    }

    public static void clickDeleteUser(Page page) {
        ErrorHandler.errorWrapper("Click 'Delete User' link on the Manage section of the SysKit Users page",
                success -> {
            page.click(mDeleteUser);
            return success;
        });
    }
}
