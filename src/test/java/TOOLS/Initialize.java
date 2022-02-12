package TOOLS;

import com.microsoft.playwright.*;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Initialize {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public enum BrowserToOpen {
        CHROME, FIREFOX
    }

    public Initialize(BrowserToOpen browserToOpen) {

        Logger.getLogger("org.testng").setLevel(Level.OFF);
        playwright = Playwright.create();
        switch (browserToOpen) {
            case CHROME:
                browser =
                        playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized")));
                browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
                break;
            case FIREFOX:
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                browserContext = browser.newContext();
                break;
            default:
                Assert.fail("Set up a new browser in Initialize.java");
        }
        page = browserContext.newPage();
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public Page getPage() {
        return page;
    }
}
