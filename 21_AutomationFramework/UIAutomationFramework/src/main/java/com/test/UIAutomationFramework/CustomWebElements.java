package com.test.UIAutomationFramework;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CustomWebElements implements Enumeration<WebElement> {
    private List<WebElement> _elements = null;
    private int position = -1;

    /// <summary>
    /// Constructor for the CustomWebElements class
    /// </summary>
    /// <param name="elements">collection of elements</param>
    /// <param name="log">Reference to the test Logger</param>
    public CustomWebElements(List<WebElement> elements)
    {
        _elements = elements;
    }

    /// <summary>
    /// Count of the number of elements in the collection
    /// </summary>
    public int Size()
    {
        return _elements.size();
    }

    /// <summary>
    /// Forwards the call to the strong typed implementation.
    /// </summary>
//    IEnumerator IEnumerable.GetEnumerator()
//    {
//        return this.GetEnumerator();
//    }

    /// <summary>
    /// The strong typed enumerator
    /// </summary>
    /// <returns>Enumeration of collection</returns>
//    public Enumeration<WebElement> GetEnumerator()
//    {
//        for(WebElement element: _elements)
//        {
//             return new WebElement(element);
//        }
//    }

//    public boolean MoveNext()
//    {
//        position++;
//        return (position < _elements.size());
//    }

    /// <summary>
    /// Required but not used in this collection
    /// </summary>
    /// <param name="array">N/A</param>
    /// <param name="index">N/A</param>
//    void ICollection.CopyTo(Array array, int index)
//    {
//        throw new NotSupportedException();
//    }

    /// <summary>
    /// The access to this ICollection is not synchronized (thread-safe).
    /// This property returns always <b>false</b>.
    /// </summary>
//    bool ICollection.IsSynchronized
//    {
//        get { return false; }
//    }

    /// <summary>
    /// The access to this ICollection is not synchronized (thread-safe).
    /// </summary>
    /// <exception cref="NotSupportedException"></exception>
//    object ICollection.SyncRoot
//    {
//        get { throw new NotSupportedException(); }
//    }

    /// <summary>
    /// Find multiple elements in a collection
    /// </summary>
    /// <param name="id">Value of id to search for</param>
    /// <returns>Collection of elements matching search criteria</returns>
    public CustomWebElements FindAllById(String id)
    {
        List<WebElement> collection = GetElementsByAttribute("id", id);
        return new CustomWebElements(collection);
    }


    /// <summary>
    /// Find multiple elements in a collection with a given attribute
    /// </summary>
    /// <param name="attrib">Attribute to search for</param>
    /// <returns>Collection of elements matching search criteria</returns>
    public CustomWebElements FindAllWithAttribute(String attrib)
    {
        List<WebElement> elementList = new ArrayList<>();
        for (WebElement e: _elements)
        {
            String att = e.getAttribute(attrib);
//            if (!String.IsNullOrEmpty(att))
            if (att != null && att.length() >0)
            {
                elementList.add(e);
            }
        }
        return new CustomWebElements(elementList);
    }

    /// <summary>
    /// Find multiple elements in a collection by its text
    /// </summary>
    /// <param name="label">Text value to search for</param>
    /// <returns>Collection of elements matching the search criteria</returns>
    public CustomWebElements FindAllByText(String label)
    {
        List<WebElement> elementList = new ArrayList<>();
        for (WebElement e : _elements)
        {
            if (e.getText() == label)
            {
                elementList.add(e);
            }
        }
        return new CustomWebElements(elementList);
    }

    /// <summary>
    /// Find an element in a collection
    /// </summary>
    /// <param name="id">Value of id to search for</param>
    /// <returns>Element matching search criteria</returns>
    public WebElement FindById(String id)
    {
        WebElement element = null;
        List<WebElement> collection = GetElementsByAttribute("id", id);

        switch (collection.size())
        {
            case 0:
            {
                throw new NoSuchElementException("No element with id = " + id + " was found.");
            }
            case 1:
            {
                element = collection.get(0);
                break;
            }
            default:
            {
                throw new InvalidElementStateException("Multiple elements were found with id = " + id);
            }
        }
        return element;
    }

    /// <summary>
    /// Find multiple elements in a collection
    /// </summary>
    /// <param name="id">Value of alt to search for</param>
    /// <returns>Collection of elements matching search criteria</returns>
    public CustomWebElements FindAllByAlt(String alt)
    {
        List<WebElement> collection = GetElementsByAttribute("alt", alt);
        return new CustomWebElements(collection);
    }

    /// <summary>
    /// Find an element in a collection
    /// </summary>
    /// <param name="value">Value of alt to search for</param>
    /// <returns>Element matching search criteria</returns>
    public CustomWebElement FindByAlt(String value)
    {
        CustomWebElement element = null;
        List<WebElement> collection = GetElementsByAttribute("alt", value);

        switch (collection.size())
        {
            case 0:
            {
                throw new NoSuchElementException("No element with alt text = " + value + " was found.");
            }
            case 1:
            {
                element = new CustomWebElement(collection.get(0));
                break;
            }
            default:
            {
                throw new InvalidElementStateException("Multiple elements were found with alt text = " + value);
            }
        }
        return element;
    }

    /// <summary>
    /// Find multiple elements in a collection
    /// </summary>
    /// <param name="className">Class to search for</param>
    /// <returns>Collection of elements matching search criteria</returns>
    public CustomWebElements FindAllByClass(String className)
    {
        List<WebElement> collection = GetElementsByAttribute("class", className);
        return new CustomWebElements(collection);
    }

    /// <summary>
    /// Find an element in a collection
    /// </summary>
    /// <param name="className">Class to search for</param>
    /// <returns>Element matching search criteria</returns>
    public CustomWebElement FindByClass(String className)
    {
        CustomWebElement element = null;
        List<WebElement> collection = GetElementsByAttribute("class", className);

        switch (collection.size())
        {
            case 0:
            {
                throw new NoSuchElementException("No element with class = " + className + " was found.");
            }
            case 1:
            {
                element = new CustomWebElement(collection.get(0));
                break;
            }
            default:
            {
                throw new InvalidElementStateException("Multiple elements were found with class = " + className);
            }
        }
        return element;
    }

    /// <summary>
    /// Find multiple elements in a collection
    /// </summary>
    /// <param name="title">Value of title to search for</param>
    /// <returns>Collection of elements matching search criteria</returns>
    public CustomWebElements FindAllByTitle(String title)
    {
        List<WebElement> collection = GetElementsByAttribute("title", title);
        return new CustomWebElements(collection);
    }

    /// <summary>
    /// Find an element in a collection
    /// </summary>
    /// <param name="title">Value of title to search for</param>
    /// <returns>Element matching search criteria</returns>
    public WebElement FindByTitle(String title)
    {
        WebElement element = null;
        List<WebElement> collection = GetElementsByAttribute("title", title);

        switch (collection.size())
        {
            case 0:
            {
                throw new NoSuchElementException("No element with title = " + title + " was found.");
            }
            case 1:
            {
                element = collection.get(0);
                break;
            }
            default:
            {
                throw new InvalidElementStateException("Multiple elements were found with title = " + title);
            }
        }
        return element;
    }

    /// <summary>
    /// Find an element in a collection by its text
    /// </summary>
    /// <param name="label">Text value to search for</param>
    /// <returns>Element matching the search criteria</returns>
    public WebElement FindByText(String label)
    {
        WebElement element = null;
        List<WebElement> elementList = new ArrayList<>();
        for (WebElement e : _elements)
        {
            if (e.getText().equals(label))
            {
                elementList.add(e);
            }
        }
        switch (elementList.size())
        {
            case 0:
            {
                throw new NoSuchElementException("No element with text = " + label + " was found.");
            }
            case 1:
            {
                element = elementList.get(0);
                break;
            }
            default:
            {
                throw new InvalidElementStateException("Multiple elements were found with title = " + label);
            }
        }
        return element;
    }


    private List<WebElement> GetElementsByAttribute(String attrib, String value)
    {
        List<WebElement> elementList = new ArrayList<>();
        String attValue = "";
        for (WebElement e : _elements)
        {
            attValue = e.getAttribute(attrib);
            if (value == attValue)
            {
                elementList.add(e);
            }
        }
        return elementList;
    }

    private List<WebElement> GetElementsByCssProperty(String cssProperty, String value)
    {
        List<WebElement> elementList = new ArrayList<>();
        for (WebElement e : _elements)
        {
            if (e.getCssValue(cssProperty) == value)
            {
                elementList.add(e);
            }
        }
        return elementList;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public WebElement nextElement() {
        return null;
    }
}
