package com.test.UIAutomationFramework;

import java.util.HashMap;

public class Config {
    private static String _userName = "";
    private static String _userEmail = "";
    private static String _userPassword = "";
    private static String _baseUrl = "";
    private static String _environment = "";
    private static String _site = "";
    private static Browser _browser = com.test.UIAutomationFramework.Browser.Undefined;
    private static HashMap<String, String> _configMap = new HashMap<String, String>();
    private static String _htmlFormPostUrl = "";
    private static String _devMachineName = "";
    private static String _contextId = "";


    public Config()
    {
        if (_configMap.size() == 0)
        {
            LoadConfig();
        }
    }

    /// <summary>
    /// Gets the Base Site based on value in Config file
    /// </summary>
    public String getSite()
    {
        if (_site == null || _site.length() == 0)
        {
            _site = GetValue("Site");
        }
        return _site;
    }

    public void setSite(String site)
    {
        _site = site;
    }
    /// <summary>
    /// Gets the UserName based on value in Config file
    /// </summary>
    public String getUserName()
    {
        if (_userName == null || _userName.length() == 0)
        {
            _userName = GetValue("UserName");
        }
        return _userName;
    }

    /// <summary>
    /// Gets the UserEmail based on value in Config file
    /// </summary>
    public String getUserEmail()
    {
        if (_userEmail == null || _userEmail.length() == 0)
        {
            _userEmail = GetValue("UserEmail");
        }
        return _userEmail;
    }

    /// <summary>
    /// Gets the UserPassword based on value in Config file
    /// </summary>
    public String getUserPassword()
    {
        if (_userPassword == null || _userPassword.length() == 0)
        {
            _userPassword = GetValue("UserPassword");
        }
        return _userPassword;
    }

    /// <summary>
    /// Gets the BaseUrl based on value in Config file
    /// </summary>
    public String getBaseUrl()
    {
            if (_baseUrl == null || _baseUrl.length() == 0)
            {
                _baseUrl = GetValue("BaseUrl");
            }
            return _baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        _baseUrl = baseUrl;
    }

    /// <summary>
    /// Gets the Browser enum based on value in Config file
    /// </summary>
    public Browser getBrowser()
    {
            if (_browser == Browser.Undefined)
            {
                switch (GetValue("Browser"))
                {
                    case "Android":
                    {
                        _browser = Browser.Android;
                        break;
                    }
                    case "Chrome":
                    {
                        _browser = Browser.Chrome;
                        break;
                    }
                    case "Firefox":
                    {
                        _browser = Browser.Firefox;
                        break;
                    }
                    case "IE":
                    {
                        _browser = Browser.IE;
                        break;
                    }
                    default:
                    {
                        _browser = Browser.Undefined;
                        break;
                    }
                }
            }
            return _browser;
    }

    public void setBrowser(Browser browser){
        _browser = browser;
    }

    /// <summary>
    /// Gets the TestEnvironment based on value in Config file
    /// </summary>
    public String getAutomationEnvironment()
    {
        if (_environment == null || _environment.length() == 0)
        {
            _environment = GetValue("AutomationEnvironment");
        }
        return _environment;
    }

    public void setAutomationEnvironment(String environment){
        _environment = environment;
    }

    /// <summary>
    /// Gets the HtmlFormPostUrl value from UIConfig.xml file
    /// </summary>
    public String getHtmlFormPostUrl()
    {
        if (_htmlFormPostUrl == null || _htmlFormPostUrl.length() == 0)
        {
            _htmlFormPostUrl = GetValue("HtmlFormPostUrl");
        }
        return _htmlFormPostUrl;
    }

    public void setHtmlFormPostUrl(String url){
        _htmlFormPostUrl = url;
    }


    private void LoadConfig()
    {
//        XmlDocument doc = new XmlDocument();
//        String filename = Environment.CurrentDirectory + "\\SpecflowConfig.xml";
//        System.Console.WriteLine(String.Format("The config file loaded will be: {0}", filename));
//        doc.Load(filename);
//        XmlNodeList nodeList = doc.SelectNodes("//add");
//
//        XmlAttributeCollection collection = null;
//        foreach (XmlNode node in nodeList)
//        {
//            collection = node.Attributes;
//            System.Console.WriteLine(String.Format("The config node key is '{0}', the value is '{1}'.", collection[0].Value, collection[1].Value));
//            _configMap.Add(collection[0].Value, collection[1].Value);
//        }
    }

    private String GetValue(String key)
    {
        return _configMap.getOrDefault(key,"");
    }



}

