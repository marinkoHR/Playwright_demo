package MAPPINGS;

import STRINGS.SysKitActionDetailPageStrings;

public class SysKitActionDetailsPageMappings extends SysKitActionDetailPageStrings {

    public static class Navigation {

        public static String mHomeButton = "div.navigation-home-button__content";
        public static String mUsersNavigationButton = "#navigation-bubble > div > a#Users";
    }

    public static String mActionDetailsTitle = "div.notification-info__action";

    public static String mPerformedBy = "div.grande-details-label__content.default-label:nth-child(1) > div.grande-details-label__description.default-label > div.grande-details-label__description__text.default-label";
    public static String mPerformedOn = "div.grande-details-label__content.default-label:nth-child(2) > div.grande-details-label__description.default-label > div.grande-details-label__description__text.default-label";
    public static String mShortStatus = "div.grande-details-label__content.default-label:nth-child(3) > div.grande-details-label__description.default-label > div.grande-details-label__description__text.default-label";

    public static String mJobDescription = "div.notification-actions > div > div.table-body > ul > div > div:nth-child(1)";
    public static String mStatusDetails = "div.notification-actions > div > div.table-body > ul > div > div.table-column.status";
}
