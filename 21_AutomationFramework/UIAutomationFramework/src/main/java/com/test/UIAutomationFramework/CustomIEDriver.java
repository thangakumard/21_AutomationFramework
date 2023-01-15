package com.test.UIAutomationFramework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class CustomIEDriver extends InternetExplorerDriver {
    protected CustomIEDriver(){
        super();
    }
    /// <summary>
    /// Will start the browser
    /// </summary>
    protected void StartBrowser()
    {
        long browserCount = super.getWindowHandles().stream().count();
        if (browserCount <= 1)
        {
            try
            {
                super.startSession(null);
            }
            catch(Exception ex)
            {
                throw ex;
            }
        }
        else
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            super.startSession(capabilities);
        }
        super.manage().window().maximize();
    }

    /// <summary>
    /// Will stop the current browser
    /// </summary>
    protected void CloseBrowser()
    {
        super.close();
    }

    /// <summary>
    /// Takes a screenshot of the current
    /// </summary>
    /// <returns></returns>
    protected void TakeScreenshot(String filePath) throws IOException {
        File sourceFile = super.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileUtils.copyFile(sourceFile,destFile);
    }
}
