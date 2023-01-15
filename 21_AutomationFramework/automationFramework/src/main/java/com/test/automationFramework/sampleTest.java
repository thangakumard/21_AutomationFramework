package com.test.automationFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class sampleTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;

//        WebDriverManager.chromedriver().browserVersion("106.0.5249.119").setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("enable-automation");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--disable-browser-side-navigation");
//        options.addArguments("--disable-gpu");
//
//        driver = new ChromeDriver(options);

//        WebDriverManager.firefoxdriver().browserVersion("106.0.4").setup();
//        FirefoxOptions fireFoxOptions = new FirefoxOptions();
//        driver = new FirefoxDriver(fireFoxOptions);

//        WebDriverManager.safaridriver().browserVersion("15.6.1").setup();
        SafariOptions safariOptions = new SafariOptions();
        driver = new SafariDriver(safariOptions);

        driver.get("https://www.google.com/");
        driver.get("http:\\www.amazon.com");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@id='nav-tools']/a[1]/span[1]")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("ap_email")).sendKeys("adfsdfsdf");
        Thread.sleep(4000);
        driver.findElement(By.id("ap_password")).sendKeys("test@gmail.com");
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(4000);
        String s = driver.findElement(By.className("a-list-item")).getText();
        System.out.println(s);
    }
}
