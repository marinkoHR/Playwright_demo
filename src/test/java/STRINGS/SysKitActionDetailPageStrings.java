package STRINGS;

public class SysKitActionDetailPageStrings {

    public static String getJobDetailExpectedMessageForFailedDeletion(String sUserFullName) {
        return "User " + sUserFullName + " failed to be deleted.";
    }

    public static String sExpectedTitleForDeleteUser = "Delete User";
    public static String sExpectedShortErrorStatus = "Completed with errors";
    public static String sExpectedStatusDetailsForInsufficientPrivileges = "Error: Insufficient privileges to complete the operation.";
}
