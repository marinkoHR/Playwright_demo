package TOOLS;

import java.util.concurrent.TimeUnit;

public class Tools {

    public static void delay(int iSeconds) {
        try {
            TimeUnit.SECONDS.sleep(iSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
