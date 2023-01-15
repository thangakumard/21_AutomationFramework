package com.test.UIAutomationFramework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class CustomWebElement {
    private WebElement _element = null;

    /// <summary>
    /// Constructor for the element
    /// </summary>
    /// <param name="element">Reference to the control element</param>
    /// <param name="log">Reference to the test Logger</param>
    public CustomWebElement(WebElement element)
    {
        _element = element;
    }

    /// <summary>
    /// Internal accessor for the Selenium object
    /// </summary>
    protected WebElement getWebElement()
    {
        return _element;
    }

    /// <summary>
    /// Determines if the control is enabled or not
    /// </summary>
    public boolean IsEnabled()
    {
        return _element.isEnabled();
    }

    /// <summary>
    /// Determines if the control is displayed or not
    /// </summary>
    public boolean IsDisplayed()
    {
        return _element.isDisplayed();
    }

    /// <summary>
    /// Determines if the control is selected or not
    /// </summary>
    public boolean IsSelected()
    {
        return _element.isSelected();
    }

    /// <summary>
    /// Clears the element contents
    /// </summary>
    public void ClearField()
    {
        try
        {
            _element.clear();
        }
        catch (StaleElementReferenceException e)
        {
            throw e;
        }
    }

    /// <summary>
    /// Returns the value of an attribute
    /// </summary>
    /// <param name="attribute">Name of attribute</param>
    /// <returns>String value of the attribute</returns>
    public String getAttribute(String attribute)
    {
        String attribValue = null;
        try
        {
            attribValue = _element.getAttribute(attribute);
        }
        catch (StaleElementReferenceException e)
        {
            throw e;
        }
        return attribValue;
    }

    /// <summary>
    /// Gets the Tag name of the control
    /// </summary>
    public String getTagName()
    {
            return _element.getTagName();
    }

    /// <summary>
    /// Returns the value of a css property
    /// </summary>
    /// <param name="propertyName">Name of css property</param>
    /// <returns>String value of the css property</returns>
    public String GetCssValue(String propertyName)
    {
        String cssValue = null;
        try
        {
            cssValue = _element.getCssValue(propertyName);
        }
        catch (StaleElementReferenceException e)
        {
            throw e;
        }
        return cssValue;
    }

    /// <summary>
    /// Simulates typing text into the control
    /// </summary>
    /// <param name="text">Text String to write to the control</param>
    public void Write(String text)
    {
        Write(text, true);
    }

    /// <summary>
    /// Simulates typing text into the control
    /// </summary>
    /// <param name="text">Text String to write to the control</param>
    /// <param name="clearField"></param>
    public void Write(String text, boolean clearField)
    {
        if (clearField)
        {
            _element.clear();
        }
        _element.sendKeys(text);
    }

    /// <summary>
    /// Sends a mouse click to the control
    /// </summary>
    public void click() throws InterruptedException {
        boolean waschecked = _element.isSelected();
        boolean ischecked = waschecked;
        String tag = _element.getTagName();
        String id = _element.getAttribute("id");
        _element.click();

        Thread.currentThread().join(500);
        ischecked = _element.isSelected();
        if (ischecked == waschecked && _element.isEnabled())
        {
            _element.click();
        }
}

    /// <summary>
    /// Performs a right-click on the element.
    /// </summary>
    public void RightClick()
    {
        new Mouse().RightMouseClick(_element);
    }

    /// <summary>
    /// Sends a mouse click to the control
    /// </summary>
    /// <param name="IsRegressive">Set true to click on button mutiple times</param>
    public void Click(boolean IsRegressive) throws InterruptedException {
        if (IsRegressive)
        {
            click();
        }
        else
        {
            _element.click();
        }
    }

    /// <summary>
    /// Gets the 4 points of the rectangle represented by the control
    /// </summary>
    public Rectangle getBoundingRectangle()
    {
        return new Rectangle(_element.getLocation(), _element.getSize());
    }

    /// <summary>
    /// Gets the center point for the WebElement. To use in mouse positioning
    /// </summary>
    public Point getCenter()
    {
            Point point = new Point(0,0);
            point.x = this.getBoundingRectangle().width / 2;
            point.y = this.getBoundingRectangle().height / 2;
            return point;
    }

    /// <summary>
    /// Gets the inner text of the control
    /// </summary>
    public String getText()
    {
        return _element.getText();
    }

    /// <summary>
    /// Determines if descendent element exists on the page
    /// </summary>
    /// <param name="findType">Method to use for searching for the child element</param>
    /// <param name="valueToFind">Value of the search method</param>
    /// <returns>Boolean true if found</returns>
    public boolean IsFound(FindType findType, String valueToFind) throws InterruptedException {
        boolean found = false;
        // First wait for a second or so.
        Thread.currentThread().join(500);
        try
        {
            CustomWebElement element = new CustomWebElement(_element.findElement(new FindBy().GetBy(findType, valueToFind)));
            found = true;
        }
        catch (NoSuchElementException e)
        {
            throw e;
        }
        return found;
    }

    /// <summary>
    /// Finds descendent element
    /// </summary>
    /// <param name="findType">Method to use for searching for the child element</param>
    /// <param name="valueToFind">Value of the search method</param>
    /// <returns>WebElement child</returns>
    public CustomWebElement GetDescendentElement(FindType findType, String valueToFind) throws InterruptedException {
        return GetDescendentElement(findType, valueToFind, 750);
    }

    /// <summary>
    /// Finds descendent element
    /// </summary>
    /// <param name="findType">Method to use for searching for the child element</param>
    /// <param name="valueToFind">Value of the search method</param>
    /// <param name="waitTime">Duration of time to search for the element</param>
    /// <returns>WebElement child</returns>
    public CustomWebElement GetDescendentElement(FindType findType, String valueToFind, int waitTime) throws InterruptedException {
        CustomWebElement child = null;
//        DateTime timeout = DateTime.Now.AddMilliseconds(waitTime);
        int retryCount = 0;
        while ((child == null) && retryCount < 5)
        {
            try
            {
                child = new CustomWebElement(_element.findElement(new FindBy().GetBy(findType, valueToFind)));
                retryCount++;
            }
            catch (Exception e)
            {
                throw e;
            }
            Thread.currentThread().join(100);
        }
        return child;
    }

    /// <summary>
    /// Finds all descendent elements matching criteria
    /// </summary>
    /// <param name="findType">Method to use for searching for the child elements</param>
    /// <param name="valueToFind">Value of the search method</param>
    /// <returns>WebElementCollection containing all descendents matching criteria</returns>
    public CustomWebElements GetDescendentElements(FindType findType, String valueToFind)
    {
        return new CustomWebElements(_element.findElements(new FindBy().GetBy(findType, valueToFind)));
    }

    /// <summary>
    /// Selects an element's option (e.g. combobox) by value
    /// </summary>
    /// <param name="value">Value to select</param>
    public void SelectOptionByIndex(int value)
    {
        Select select = new Select(_element);
        select.selectByIndex(value);
//        if (new Config().getBrowser() == Browser.Firefox)
//        {
//            select.selectByValue(value);
//        }
//        else
//        {
//            _element.click();
//            select.selectByValue(value);
//        }
    }

    /// <summary>
    /// Selects an element's option (e.g. combobox) by text
    /// </summary>
    /// <param name="text">Text to select</param>
    public void selectByValue(String text)
    {
        Select select = new Select(_element);
        try
        {
            if (new Config().getBrowser() == Browser.Firefox)
            {
                select.selectByVisibleText(text);
            }
            else
            {
                _element.click();
                select.selectByValue(text);
            }
        }
        catch (StaleElementReferenceException e)
        {
            throw e;
        }
    }

    /// <summary>
    /// Selects an element's option (e.g. combobox) by index
    /// </summary>
    /// <param name="index">Index to select</param>
//    public void SelectOptionByIndex(int index)
//    {
//        SelectElement select = new SelectElement(_element);
//        if (new Config().Browser == Browser.Firefox)
//        {
//            select.SelectByIndex(index);
//        }
//        else
//        {
//            _element.click();
//            select.SelectByIndex(index);
//        }
//    }

    /// <summary>
    /// Selected element's option (e.g. combobox)
    /// </summary>
    /// <returns>IWebElement containing the selected option</returns>
    public WebElement getFirstSelectedOption()
    {
            Select select = new Select(_element);
            return select.getFirstSelectedOption();
    }

    /// <summary>
    /// Gets a collection of WebElements which make up the list of options for a given element (e.g. combobox or listbox)
    /// </summary>
    public List<WebElement> Options()
    {
            _element.click();
            Select select = new Select(_element);
            return select.getOptions();
    }
}
