package MAPPINGS;

import STRINGS.SysKitUsersPageStrings;

public class SysKitUsersPageMappings {

    public static class TourPopUp {

        public static String mSkipTourButton = "button.button.cancel.skip-button";
    }

    public static class DeleteUserPopUp extends SysKitUsersPageStrings.DeleteUserPopUp {

        public static String mTitle = "div.dx-toolbar.dx-widget.dx-visibility-change-handler.dx-collection.dx-popup-title.dx-has-close-button > div > div.dx-toolbar-before > div > div > div";
        public static String mContent = "div.delete-user-dialog > div:nth-child(2)";
        public static String mButtonDeleteUser = "div.generic-dialog__footer > button.button.primary";
    }

    public static class Toastify extends SysKitUsersPageStrings.Toastify {

        public static String mToastifyTitle = "div.Toastify__toast-body > div > div > div.title";
        public static String mToastifyMessage = "div.Toastify__toast-body > div > div > div.message";
        public static String mToastifyMessageLink = "div.Toastify__toast-body > div > div > div.message > a";
    }

    //region Table mappings
    public static String mTableSearchField = "input[aria-label='Search in the data grid']";
    public static String mTableRows = "table > tbody > tr.dx-row.dx-data-row.dx-column-lines";
    public static String mTableCheckbox = "span.dx-checkbox-icon";
    public static String mTableUsernameColumn = "td[aria-colindex='3']";
    public static String mTableDisplayNameColumn = "td[aria-colindex='2']";
    //endregion

    //region Manage section
    public static String mDeleteUser = "svg.icon.svg.svg-icon-userDelete";
    //endregion

    //region Other
    public static String mLoader = "div.dx-loadpanel-message";
    //endregion
}
