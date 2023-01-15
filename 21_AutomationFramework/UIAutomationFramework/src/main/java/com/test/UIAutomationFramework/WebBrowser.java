package com.test.UIAutomationFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

public class WebBrowser {

    private WebBrowser() {
    }

    private static RemoteWebDriver _driver = null;
    private static WebBrowser _webBrowser = null;
    private static Browser _browser = null;

    private static String _primaryWindowHandle = "";

    public static void setBrowser(Browser browser) {
        _browser = browser;
    }

    public static Browser getBrowser() {
        return _browser;
    }

    public static WebBrowser getInstance() {
        if (_webBrowser == null) {
            LaunchBrowser();
        }
        return _webBrowser;
    }

    /// <summary>
    /// To get and set RemoteWebDriver object
    /// </summary>
    protected static WebDriver getDriver() {
        if (_driver != null && _driver.getWindowHandles().size() > 0) {
            Quit();
        }
        return _driver;
    }

    protected static void setDriver(RemoteWebDriver driver) {
        _driver = driver;
    }

    /// <summary>
    /// Navigates browser to specified url
    /// </summary>
    /// <param name="url">Address to navigate to</param>
    public void Navigate(String url) throws InterruptedException {
        getDriver().navigate().to(url);
        getDriver().manage().window().maximize();
        WaitForPageToLoad();
    }

    /// <summary>
    /// Navigates back one entry in the browser's history
    /// </summary>
    public void NavigateBack() {
        getDriver().navigate().back();
    }

    /// <summary>
    /// Navigates forward one entry in the browser's history
    /// </summary>
    public void NavigateForward() {
        getDriver().navigate().forward();
    }

    /// <summary>
    /// Refreshes the current browser
    /// </summary>
    public void Refresh() {
        getDriver().navigate().refresh();
    }

    /// <summary>
    /// Launches the Browser specified
    /// </summary>
    public static void LaunchBrowser() {
        switch (_browser) {
            case Chrome: {
                CustomChromeDriver chromeDriver = new CustomChromeDriver();
                chromeDriver.StartBrowser();
                setDriver(chromeDriver);
                break;
            }
            case Firefox: {
                CustomFirefoxDriver firefoxDriver = new CustomFirefoxDriver();
                firefoxDriver.StartBrowser();
                setDriver(firefoxDriver);
                break;
            }
            case IE: {
                CustomIEDriver ieDriver = new CustomIEDriver();
                ieDriver.StartBrowser();
                setDriver(ieDriver);
                break;
            }
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        _primaryWindowHandle = getDriver().getWindowHandle();
    }

    /// <summary>
    /// Returns the current url
    /// </summary>
    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    /// <summary>
    /// Take a screenshot of the displayed desktop window
    /// </summary>
    public String TakeScreenshot() {
        String filename = "Screenshot " + LocalDateTime.now() + ".gif";
        return TakeScreenshot(filename);
    }

    /// <summary>
    /// Take a screenshot of the displayed desktop window
    /// </summary>
    /// <param name="filename"></param>
    public String TakeScreenshot(String filename) {
        String folder = System.getProperty("user.dir") + "\\screenshots";
        new File(folder).mkdirs();
        filename = folder + "\\" + filename;
        if (getDriver() != null) {
            switch (_browser) {
                case Chrome: {
                    CustomChromeDriver _driver = new CustomChromeDriver();
                    _driver.StartBrowser();
                    setDriver(_driver);
                    break;
                }
                case Firefox: {
                    CustomFirefoxDriver _driver = new CustomFirefoxDriver();
                    _driver.StartBrowser();
                    setDriver(_driver);
                    break;
                }
                case IE: {
                    CustomIEDriver _driver = new CustomIEDriver();
                    _driver.StartBrowser();
                    setDriver(_driver);
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
        }
        return filename;
    }

    /// <summary>
    /// Finds the first descendent element based on criteria
    /// </summary>
    /// <param name="findType">What criteria to search from</param>
    /// <param name="value">Value of the criteria</param>
    /// <returns>WebElement representing the searched-for object</returns>
    public CustomWebElement GetElement(By findType) {
        CustomWebElement element = null;
        int retryCount = 0;
        while ((element == null) && retryCount < 5) {
            try {
                if (WebBrowser.getDriver() != null) {
                    element = new CustomWebElement(getDriver().findElement(findType));
                }
            } catch (Exception e) {
                throw e;
            }

        }
        return element;
    }

    /// <summary>
    /// Finds the first descendent element based on criteria
    /// </summary>
    /// <param name="findType">What criteria to search from</param>
    /// <param name="value">Value of the criteria</param>
    /// <returns>WebElementCollection representing the searched-for objects</returns>
    public CustomWebElements GetElements(By findBy) {
        CustomWebElements elements = null;
        int retryCount = 0;
        while ((elements == null) && (retryCount < 5)) {
            try {
                if (WebBrowser.getDriver() != null) {
                    elements = new CustomWebElements(getDriver().findElements(findBy));
                }
            } catch (NoSuchElementException e) {
                throw e;
            }
        }
        return elements;
    }

    /// <summary>
    /// Closes current browser window
    /// </summary>
    public void CloseBrowser() {
        getDriver().close();
        this.SwitchToWindow(_primaryWindowHandle);
    }

    /// <summary>
    /// Closes all windows associated with this driver and then quits/disposes the driver itself
    /// </summary>
    public static void Quit() {
        if (_driver != null) {
            switch (_browser) {
                case Chrome: {
                    KillProcess("ChromeDriver");
                    break;
                }
                case IE: {
                    KillProcess("IEDriverServer");
                    break;
                }
                case Firefox: {
                    KillProcess("firefox");
                    break;
                }
            }
            _driver.quit();
            // For firefox dispose method is throwing error
//            if (_browser != Browser.Firefox)
//            {
//                _driver.Dispose();
//            }
            _driver = null;
            _browser = Browser.Undefined;
            _primaryWindowHandle = null;
        }
    }

    /// <summary>
    /// Gets a collection of the window handles associated with this driver
    /// </summary>
    public Set<String> getWindowHandles() {
        return getDriver().getWindowHandles();
    }

    /// <summary>
    /// Switches focus for subsequent UI automation to a new window
    /// </summary>
    /// <param name="windowName">Name of the window to switch to</param>
    public void SwitchToWindow(String windowName) {
        getDriver().switchTo().window(windowName);
    }

    /// <summary>
    /// Switches focus for required frame
    /// </summary>
    /// <param name="frameName">Name of the frame to switch to</param>
    public void SwitchToFrame(String frameName) {
        getDriver().switchTo().frame(frameName);
    }

    /// <summary>
    /// Switches focus for base browser page
    /// </summary>
    public void SwitchToBasePage() {
        getDriver().switchTo().defaultContent();
    }

    /// <summary>
    /// Gets the window handle of the current window
    /// </summary>
    public String CurrentHandle() {
        return getDriver().getWindowHandle();
    }

    /// <summary>
    /// To send key to the active element
    /// </summary>
    /// <param name="keysToSend">Keys to be sent to the active element</param>
    public void SendKeyToActiveElement(String keysToSend) {
        getDriver().switchTo().activeElement().sendKeys(keysToSend);
    }

    /// <summary>
    /// Waits up to 10 seconds for a page to finish loading.
    /// </summary>
    public void WaitForPageToLoad() throws InterruptedException {
        Thread.currentThread().join(500L);
    }

    public String PageSource() {
        return getDriver().getPageSource();
    }

//        [DllImport("Kernel32.dll", EntryPoint = "ProcessIdToSessionId", SetLastError = true)]
//            [return: MarshalAs(UnmanagedType.Bool)]
//    private static extern boolean ProcessIdToSessionId(uint dwProcessId, ref uint pSessionId);

private static void KillProcess(String name) {
        ////TODO
}
//    private static void KillProcess(String name)
//    {
//        uint pid = System.Convert.ToUInt32(Process.GetCurrentProcess().Id);
//        uint currentSession = 0;
//        if (!ProcessIdToSessionId(pid, ref currentSession))
//        {
//            int lastErr = Marshal.GetLastWin32Error();
//            throw new ApplicationException("Failed to convert pid to session id for current process, error: 0x" + lastErr.ToString("x"));
//        }
//
//        Process[] processes = Process.GetProcessesByName(name);
//        foreach (Process process in processes)
//        {
//            uint targetPid = System.Convert.ToUInt32(process.Id);
//            uint targetSession = 0;
//            if (!ProcessIdToSessionId(targetPid, ref targetSession))
//            {
//                String lastErr = Marshal.GetLastWin32Error().ToString("x");
//                break;
//            }
//
//            if (targetSession.Equals(currentSession))
//            {
//                try
//                {
//                    if (!process.HasExited)
//                    {
//                        process.Kill();
//                    }
//                }
//                catch (Win32Exception error)
//                {
//                    throw error;
//                }
//                if (!process.WaitForExit(10000))
//                {
//                    String id = process.Id.ToString("x");
//                    throw new TimeoutException("Timeout waiting for process 0x" + id + " to exit.");
//                }
//            }
//        }
//    }

    /// <summary>
    /// Deletes all cookies from browser cache
    /// </summary>
    public void DeleteCookies()
    {
        getDriver().manage().deleteAllCookies();
    }

    /// <summary>
    /// Wait for element to display.
    /// </summary>
    /// <param name="elementType">Type of element</param>
    /// <param name="element">Attribute value of element</param>
    /// <returns>true if element displayed</returns>
    public boolean IsElementDisplayed(FindType elementType, String element)
    {
        boolean isDisplayed = false;
        WebElement dynamicElement = null;
        dynamicElement = ReturnElement(elementType, element);
        isDisplayed = dynamicElement == null ? false : dynamicElement.isDisplayed();
        return isDisplayed;
    }

    /// <summary>
    /// Wait for element to display for the given time.
    /// </summary>
    /// <param name="elementType">Type of element</param>
    /// <param name="element">Attribute value of element</param>
    /// <param name="waitTime">Wait time</param>
    /// <returns>true if element displayed</returns>
    public boolean WaitForElementToLoad(FindType findBy, String element, long waitTime)
    {
        boolean isDisplayed = false;
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTime));
        WebElement dynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(getFindBy(findBy,element)));
        isDisplayed = dynamicElement == null ? false : dynamicElement.isDisplayed();
        return isDisplayed;
    }

    private By getFindBy(FindType elementType, String element) {
        switch (elementType)
        {
            case Id:
                return By.id(element);
            case CssSelector:
                return By.cssSelector(element);
            case ClassName:
                return By.className(element);
            case LinkText:
                return By.linkText(element);
            case Name:
                return By.name(element);
            case PartialLinkText:
                return By.partialLinkText(element);
            case TagName:
                return By.tagName(element);
            case XPath:
            default:
                return By.xpath(element);

        }
    }


    /// <summary>
    /// Get the element object
    /// </summary>
    /// <param name="elementType">Type of element</param>
    /// <param name="element">Attribute value of element</param>
    /// <returns></returns>
    public WebElement ReturnElement(FindType elementType, String element)
    {
        WebElement dynamicElement = null;
        try
        {
            switch (elementType)
            {
                case Id:
                    dynamicElement = _driver.findElement(By.id(element));
                    break;
                case CssSelector:
                    dynamicElement = _driver.findElement(By.cssSelector(element));
                    break;
                case ClassName:
                    dynamicElement = _driver.findElement(By.className(element));
                    break;
                case LinkText:
                    dynamicElement = _driver.findElement(By.linkText(element));
                    break;
                case Name:
                    dynamicElement = _driver.findElement(By.name(element));
                    break;
                case PartialLinkText:
                    dynamicElement = _driver.findElement(By.partialLinkText(element));
                    break;
                case TagName:
                    dynamicElement = _driver.findElement(By.tagName(element));
                    break;
                case XPath:
                    dynamicElement = _driver.findElement(By.xpath(element));
                    break;
                default:
                    dynamicElement = null;
                    break;
            }
            return dynamicElement;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /// <summary>
    /// Get alert box text
    /// </summary>
    /// <returns>Alert message</returns>
    public String GetAlertBoxText()
    {
        try
        {
            Alert alert = _driver.switchTo().alert();
            if (alert != null)
            {
                return alert.getText();
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }

    }

    /// <summary>
    /// Opens a new browser window
    /// </summary>
    public void OpenNewWindow()
    {
        JavascriptExecutor jscript = (JavascriptExecutor) getDriver();
        jscript.executeScript("window.open()");
        getDriver().switchTo().window(getWindowHandles().stream().findFirst().toString());
    }

}
