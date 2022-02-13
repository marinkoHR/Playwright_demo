package MAPPINGS;

public class SysKitAlertsPageMappings {

    public static class ConfigureAlerts {

        //region Side navigation
        public static String mPrivacyChangesSideNavigation = "div.alert-navigation__view > div.dx-scrollable-wrapper > div > div.dx-scrollable-content > div.dx-scrollview-content > div > details:nth-child(5)";
        public static String mChangedPrivateGroupToPublic = mPrivacyChangesSideNavigation + " > div > div:nth-child(1)";
        //endregion

        public static String mToggle = "label.toggle-switch";

        public static String mSaveButton = "button.button.primary";

        public static class ChangedPrivateGroupToPublic {

            public static class Notifications {

                public static String mSysKitPointAdministratorsCheckbox = "div.notification-recipients__options > "
                        + "div:nth-child(1)";
                public static String mCustomRecipientsCheckbox = "div.notification-recipients__options > div:nth-child(2)";
                public static String mCustomRecipientsInput = "div.notification-recipients__specific-people > div > "
                        + "div > div > div > div.dx-texteditor-input-container > input";
                public static String mDropdownOption = "div[role='option']";
                public static String mRemoveCustomRecipient = "div.dx-tag-remove-button";
            }
        }
    }

    public static class EditAlert {

        public static class Notifications extends SysKitAlertsPageMappings.ConfigureAlerts.ChangedPrivateGroupToPublic.Notifications {}

        public static String mSaveButton = "button.button.primary";
    }

    public static class DisableAlert {

        public static String mDisableButton = "button.button.primary";
    }

    //region Table
    public static String mTableRows = "table > tbody > tr.dx-row.dx-data-row.dx-column-lines";
    public static String mTableCheckbox = "span.dx-checkbox-icon";
    public static String mTableRecipientsColumn = "td[aria-colindex='5']";
    public static String mTableCustomRecipientsColumn = "td[aria-colindex='6']";
    public static String mTableLastModifiedByColumn = "td[aria-colindex='7']";
    public static String mTableLastModifiedOnColumn = "td[aria-colindex='8']";
    //endregion

    //region Manage section
    public static String mAddAlert = "div#report-action__add-alert";
    public static String mEditAlert = "div#report-action__edit-alert";
    public static String mDisableAlert = "div#report-action__disable-alert";
    //endregion
}
