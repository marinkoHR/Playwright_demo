package STRINGS;

public class SysKitUsersPageStrings {

    public static class DeleteUserPopUp {

        public static String sExpectedTitle = "Delete User";
        public static String sExpectedParagraphPart1 = "Are you sure to delete ";
        public static String sExpectedParagraphPart2 = " from the Azure Active Directory (AAD)?\n"
                + "You will be able to restore deleted users for up to 30 days. "
                + "This includes all their data except calendar items and aliases.\n"
                + "Any licenses assigned will remain while the user is in the recycle bin unless "
                + "you remove them manually. After users get permanently deleted, assigned licenses get freed up.";
    }

    public static class ToastNotification {

        public static String sExpectedTitleDeleteUser = "Delete User";
        public static String sExpectedMessageDeleteUserProgress = "Action started. You can check the progress in the notification panel.";
        public static String sExpectedMessageDeleteUserErrors = "Action completed with errors. Check details.";
    }
}
