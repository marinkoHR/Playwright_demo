package TOOLS;

import org.testng.asserts.SoftAssert;

import java.util.function.Function;

public class ErrorHandler {

    static SoftAssert softAssert = new SoftAssert();

    public static void errorWrapper(String sMethodDescription, Function<Success, Success> doWork) {
        errorWrapper(sMethodDescription, true, doWork);
    }

    public static void errorWrapper(String sMethodDescription, boolean bFailTest, Function<Success, Success> doWork) {

        Success success = new Success();

        try {
            success = doWork.apply(success);
            if (success.exception != null)
                throw success.exception;
            System.out.println(Tools.sTerminalColorGreen + "PASSED: " + sMethodDescription + Tools.sTerminalColorReset);
        } catch (Exception e) {
            success.bSuccess = false;
            System.out.println(Tools.sTerminalColorRed + "FAILED: " + sMethodDescription);
            success.exception = e;
            System.out.println(
                    "\tDETAILS: " + success.exception.getMessage().replace("\n", "\n\t") + Tools.sTerminalColorReset);
            softAssert.assertTrue(false, success.exception.getMessage());
            if (bFailTest)
                testEnd();
        }
    }

    public static void testEnd() {
        softAssert.assertAll();
    }
}
