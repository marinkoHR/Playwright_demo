package PAGES;

import MAPPINGS.SysKitAlertsPageMappings;
import TOOLS.ErrorHandler;
import TOOLS.Tools;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SysKitAlertsPage extends SysKitAlertsPageMappings {

    public static class ConfigureAlerts extends SysKitAlertsPageMappings.ConfigureAlerts {

        public enum Toggle {
            ON, OFF
        }

        public static class ChangedPrivateGroupToPublic extends SysKitAlertsPageMappings.ConfigureAlerts.ChangedPrivateGroupToPublic {

            public static class Notifications extends SysKitAlertsPageMappings.ConfigureAlerts.ChangedPrivateGroupToPublic.Notifications {

                public static void clickSysKitPointAdministratorsCheckbox(Page page, boolean bSetOn) {
                    ErrorHandler.errorWrapper("Set checkbox 'SysKit Point Administrators' to " + bSetOn + " on the "
                            + "'Changed Private Group to Public' window on the SysKit Alerts page", success -> {
                        if (bSetOn) {
                            if (!page.isChecked(mSysKitPointAdministratorsCheckbox))
                                page.click(mSysKitPointAdministratorsCheckbox);
                        } else if (page.isChecked(mSysKitPointAdministratorsCheckbox))
                            page.click(mSysKitPointAdministratorsCheckbox);
                        return success;
                    });
                }

                public static void clickCustomRecipientsCheckbox(Page page, boolean bSetOn) {
                    ErrorHandler.errorWrapper("Set checkbox 'Custom Recipients' to " + bSetOn + " on the 'Changed "
                            + "Private Group to Public' window on the SysKit Alerts page", success -> {
                        if (bSetOn) {
                            if (!page.isChecked(mCustomRecipientsCheckbox))
                                page.click(mCustomRecipientsCheckbox);
                        } else if (page.isChecked(mCustomRecipientsCheckbox))
                            page.click(mCustomRecipientsCheckbox);
                        return success;
                    });
                }

                public static void insertCustomRecipient(Page page, String sValue) {
                    ErrorHandler.errorWrapper("Insert value '" + sValue + "' for the Custom Recipient on the 'Changed "
                            + "Private Group to Public' window on the SysKit Alerts page", success -> {
                        List<String> lsExceptions = new ArrayList<>();
                        try {
                            page.fill(mCustomRecipientsInput, sValue);
                            String sContent;
                            int iRepeat = 0;
                            do {
                                Tools.delay(1);
                                sContent = page.waitForSelector(mDropdownOption).innerText().toLowerCase();
                                iRepeat++;
                            } while (!sContent.contains(sValue.toLowerCase()) && iRepeat < 10);
                            boolean bSuccess = sContent.contains(sValue.toLowerCase());
                            if (!bSuccess)
                                lsExceptions.add("Searched user isn't displayed as an option in the dropdown on the "
                                        + "'Changed Private Group to Public' window on the SysKit Alerts page!");
                            else
                                page.click(mDropdownOption);
                        } catch (Exception e) {
                            lsExceptions.add("Input field for the Custom Recipient isn't displayed on the 'Changed "
                                    + "Private Group to Public' window on the SysKit Alerts page!");
                        }
                        if (lsExceptions.size() > 0)
                            success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
                        return success;
                    });
                }
            }
        }

        //region Side navigation
        public static void clickPrivacyChange(Page page) {
            ErrorHandler.errorWrapper("Click button 'Privacy Change' on the Configure Alerts window on the SysKit "
                    + "Alerts page", success -> {
                page.click(mPrivacyChangesSideNavigation);
                return success;
            });
        }

        public static void clickChangedPrivateGroupToPrivate(Page page) {
            ErrorHandler.errorWrapper("Click button 'Changed Private Group to Private' on the Configure Alerts window "
                    + "on the SysKit Alerts page", success -> {
                page.waitForSelector(mChangedPrivateGroupToPublic);
                Tools.clickOnCoordinates(page, mChangedPrivateGroupToPublic);
                return success;
            });
        }
        //endregion

        public static void changeToggleState(Page page, Toggle status) {
            ErrorHandler.errorWrapper("Set toggle " + status + " on the Configure Alerts window on the SysKit Alerts "
                    + "page", success -> {
                if (status == Toggle.ON) {
                    if (!page.isChecked(mToggle))
                        page.click(mToggle);
                } else if (page.isChecked(mToggle))
                    page.click(mToggle);
                return success;
            });
        }

        public static void clickSave(Page page) {
            ErrorHandler.errorWrapper(
                    "Click button 'Save' on the Configure Alerts window on the SysKit Alerts page", success -> {
                        page.click(mSaveButton);
                        return success;
                    });
        }
    }

    public static class EditAlert extends SysKitAlertsPageMappings.EditAlert {

        public static class Notifications extends SysKitAlertsPageMappings.EditAlert.Notifications {

            public static void clickSysKitPointAdministratorsCheckbox(Page page, boolean bSetOn) {
                ErrorHandler.errorWrapper("Set checkbox 'SysKit Point Administrators' to " + bSetOn + " on the 'Edit "
                        + "Alert' window on the SysKit Alerts page", success -> {
                    if (bSetOn) {
                        if (!page.isChecked(mSysKitPointAdministratorsCheckbox))
                            page.click(mSysKitPointAdministratorsCheckbox);
                    } else if (page.isChecked(mSysKitPointAdministratorsCheckbox))
                        page.click(mSysKitPointAdministratorsCheckbox);
                    return success;
                });
            }

            public static void clickCustomRecipientsCheckbox(Page page, boolean bSetOn) {
                ErrorHandler.errorWrapper("Set checkbox 'Custom Recipients' to " + bSetOn + " on the 'Edit Alert' "
                        + "window on the SysKit Alerts page", success -> {
                    if (bSetOn) {
                        if (!page.isChecked(mCustomRecipientsCheckbox))
                            page.click(mCustomRecipientsCheckbox);
                    } else if (page.isChecked(mCustomRecipientsCheckbox))
                        page.click(mCustomRecipientsCheckbox);
                    return success;
                });
            }

            public static void removeAllCustomRecipientsFromInput(Page page) {
                ErrorHandler.errorWrapper("Remove all values from the 'Custom Recipients' input field on the "
                        + "'Edit Alert' window on the SysKit Alerts page", success -> {
                    try {
                        page.waitForSelector(mRemoveCustomRecipient,
                                new Page.WaitForSelectorOptions().setTimeout(5000));
                        List<ElementHandle> lsValues = page.querySelectorAll(mRemoveCustomRecipient);
                        for (ElementHandle ehValue : lsValues) {
                            ehValue.click();
                            Tools.delay(1);
                        }
                    } catch (Exception e) {
                    }
                    return success;
                });
            }
        }

        public static void clickSave(Page page) {
            ErrorHandler.errorWrapper(
                    "Click button 'Save' on the Edit Alert window on the SysKit Alerts page", success -> {
                        page.click(mSaveButton);
                        return success;
                    });
        }
    }

    public static class DisableAlert extends SysKitAlertsPageMappings.DisableAlert {

        public static void clickDisable(Page page) {
            ErrorHandler.errorWrapper(
                    "Click button 'Disable' on the 'Disable Alert' window on the SysKit Alerts page", success -> {
                        Tools.delay(2);
                        page.click(mDisableButton);
                        Tools.waitForLoading(page);
                        return success;
                    });
        }
    }

    //region Table
    public static int getNumberOfTableRows(Page page) {
        final int[] iNumberOfRows = {0};
        ErrorHandler.errorWrapper("Get number of rows in the table on the SysKit Alerts page", false, success -> {
            try {
                page.waitForSelector(mTableRows, new Page.WaitForSelectorOptions().setTimeout(5000));
                iNumberOfRows[0] = page.querySelectorAll(mTableRows).size();
            } catch (Exception e) {
                iNumberOfRows[0] = 0;
            }
            return success;
        });
        return iNumberOfRows[0];
    }

    public static void verifyNumberOfTableRows(Page page, int iExpectedNumberOfRows) {
        ErrorHandler.errorWrapper(
                "Verify number of rows in the table on the SysKit Alerts page is '" + iExpectedNumberOfRows
                        + "'", false, success -> {
                    List<String> lsExceptions = new ArrayList<>();
                    int iActualNumberOfRows;
                    try {
                        page.waitForSelector(mTableRows, new Page.WaitForSelectorOptions().setTimeout(2000));
                        iActualNumberOfRows = page.querySelectorAll(mTableRows).size();
                    } catch (Exception e) {
                        iActualNumberOfRows = 0;
                    }
                    if (iActualNumberOfRows != iExpectedNumberOfRows)
                        lsExceptions.add(
                                "Number of rows in the table on the SysKit Alerts page isn't as expected!\nExpected: "
                                        + iExpectedNumberOfRows + ", Actual: " + iActualNumberOfRows);
                    if (lsExceptions.size() > 0)
                        success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
                    return success;
                });
    }

    public static void verifyValuesInTheTableRow(Page page, int iRow, String sExpectedRecipients,
                                                 String sExpectedCustomRecipient, String sExpectedLastModifiedBy,
                                                 Calendar calExpectedLastModifiedOn) {
        ErrorHandler.errorWrapper("Verify table value in the row " + (iRow + 1)
                + " in the table on the SysKit Alerts page", false, success -> {

            List<String> lsExceptions = new ArrayList<>();
            List<ElementHandle> lsTableRows;
            String sFormat = "M/d/yyyy h:mm:ss aaa";
            String sExpectedCustomRecipientModified = sExpectedCustomRecipient.replace("@", "\u00AD@").replace(".",
                    "\u00AD.");

            Calendar calExpectedPlus = Calendar.getInstance();
            calExpectedPlus.setTime(calExpectedLastModifiedOn.getTime());
            calExpectedPlus.add(Calendar.MINUTE, 1);
            Calendar calExpectedMinus = Calendar.getInstance();
            calExpectedMinus.setTime(calExpectedLastModifiedOn.getTime());
            calExpectedMinus.add(Calendar.MINUTE, -1);

            try {
                page.waitForSelector(mTableRows);
                lsTableRows = page.querySelectorAll(mTableRows);
            } catch (Exception e) {
                lsTableRows = new ArrayList<>();
            }

            if (lsTableRows.size() < (iRow + 1))
                lsExceptions.add("Row " + (iRow + 1) + " doesn't exist in the table on the SysKit "
                        + "Alerts page! Total number of rows in the table is " + lsTableRows.size() + ".");
            else {
                String sActualRecipients = lsTableRows.get(iRow).querySelector(mTableRecipientsColumn).innerText();
                String sActualCustomRecipient =
                        lsTableRows.get(iRow).querySelector(mTableCustomRecipientsColumn).innerText();
                String sActualLastModifiedBy =
                        lsTableRows.get(iRow).querySelector(mTableLastModifiedByColumn).innerText();
                String sActualLastModifiedOn =
                        lsTableRows.get(iRow).querySelector(mTableLastModifiedOnColumn).innerText();
                Calendar calActualTime = Calendar.getInstance();
                try {
                    calActualTime.setTime(new SimpleDateFormat(sFormat).parse(sActualLastModifiedOn));
                } catch (ParseException e) {
                    Assert.fail(e.getMessage());
                }

                if (!sActualRecipients.equals(sExpectedRecipients))
                    lsExceptions.add("Value for 'Recipients' in the table row " + (iRow + 1)
                            + " isn't as expected on the SysKit Alerts page!\nExpected: " + sExpectedRecipients
                            + "\nActual: " + sActualRecipients);
                if (!sActualCustomRecipient.equalsIgnoreCase(sExpectedCustomRecipientModified))
                    lsExceptions.add("Value for 'Custom Recipients' in the table row " + (iRow + 1)
                            + " isn't as expected on the SysKit Alerts page!\nExpected: " + sExpectedCustomRecipientModified
                            + "\nActual: " + sActualCustomRecipient);
                if (!sActualLastModifiedBy.equals(sExpectedLastModifiedBy))
                    lsExceptions.add("Value for 'Last Modified By' in the table row " + (iRow + 1)
                            + " isn't as expected on the SysKit Alerts page!\nExpected: " + sExpectedLastModifiedBy
                            + "\nActual: " + sActualLastModifiedBy);
                if (!(calActualTime.after(calExpectedMinus) && calActualTime.before(calExpectedPlus)))
                    lsExceptions.add("Value for 'Last Modified On' in the table row " + (iRow + 1)
                            + " isn't as expected on the SysKit Alerts page!\nExpected: "
                            + calExpectedLastModifiedOn.getTime() + "\nActual: " + calActualTime.getTime());
            }
            if (lsExceptions.size() > 0)
                success.exception = new Exception("\n" + String.join("\n", lsExceptions) + "\n");
            return success;
        });
    }

    public static void clickCheckboxInTheTable(Page page, int iRow) {
        ErrorHandler.errorWrapper("Click checkbox in the row " + (iRow + 1) + " in the table on the SysKit Alerts "
                + "page", false, success -> {
            page.waitForSelector(mTableRows);
            List<ElementHandle> lsTableRows = page.querySelectorAll(mTableRows);
            if (iRow > lsTableRows.size())
                success.exception = new Exception("\nRow " + (iRow + 1) + " doesn't exist in the table on the SysKit "
                        + "Alerts page! Total number of rows in the table is " + lsTableRows.size() + ".\n");
            else
                lsTableRows.get(iRow).querySelector(mTableCheckbox).click();
            return success;
        });
    }
    //endregion

    //region Manage section
    public static void clickAddAlert(Page page) {
        ErrorHandler.errorWrapper("Click button 'Add Alert' on the SysKit Alerts page", success -> {
            page.click(mAddAlert);
            return success;
        });
    }

    public static void clickEditAlert(Page page) {
        ErrorHandler.errorWrapper("Click button 'Edit Alert' on the SysKit Alerts page", success -> {
            page.click(mEditAlert);
            return success;
        });
    }

    public static void clickDisableAlert(Page page) {
        ErrorHandler.errorWrapper("Click button 'Disable Alert' on the SysKit Alerts page", success -> {
            page.click(mDisableAlert);
            return success;
        });
    }
    //endregion
}
