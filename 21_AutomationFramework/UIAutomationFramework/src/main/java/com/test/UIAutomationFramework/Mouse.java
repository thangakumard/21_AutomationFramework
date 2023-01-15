package com.test.UIAutomationFramework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;

public class Mouse {
//    private IMouse _mouse = null;

//    public Mouse()
//    {
//        _mouse = WebBrowser.getDriver().;
//    }

    /// <summary>
    /// Moves the mouse pointer from one element to another - typically within a dscm main menu navbar/submenu item
    /// and clicks the destination element
    /// </summary>
    /// <param name="mainCategory">Element to originate the mouse movement (main nav menu item)</param>
    /// <param name="flyout">Element to move the mouse to (flyout menu item</param>
    public void MoveMouseFromToAndClick(WebElement mainCategory, WebElement flyout)
    {
        if (new Config().getBrowser().equals(Browser.Firefox))
        {
            Actions action = new Actions(WebBrowser.getDriver());
            action.moveToElement(mainCategory).moveToElement(flyout)
                    .click().build().perform();
        }
        else
        {
//            Locatable firstitem = (Locatable)mainCategory;
//            _mouse.mouseMove(firstitem.Coordinates);
//            System.Threading.Thread.Sleep(3000);
//            ILocatable seconditem = (ILocatable)flyout.seleniumControl;
//            if (flyout.IsDisplayed)
//            {
//                _mouse.MouseMove(seconditem.Coordinates);
//                _mouse.Click(seconditem.Coordinates);
//                System.Threading.Thread.CurrentThread.Join(500);
//            }
        }
    }

//    public void MoveMouseAndClick(WebElement element)
//    {
//        MoveMouseAndClick(element.seleniumControl);
//    }

    protected void MoveMouseAndClick(WebElement element) throws InterruptedException {
        new Actions(WebBrowser.getDriver())
                .moveToElement(element)
                .perform();
        Thread.sleep(500);
    }

    protected void RightMouseClick(WebElement element)
    {
        Actions actions = new Actions(WebBrowser.getDriver());
        Action rightclick = actions.contextClick(element).build();
        rightclick.perform();
    }
}
