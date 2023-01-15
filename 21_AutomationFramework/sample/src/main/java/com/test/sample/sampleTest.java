package com.test.sample;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*********
 * For safari driver run the below command from the terminal
 * sudo safaridriver --enable
 */

public class sampleTest {

        public static final String browser = "firefox";

        public static void main(String[] args) throws InterruptedException {
            WebDriver driver = null;

            switch (browser){
                case "chrome":{
                    driver = new ChromeDriver();
                    break;
                }
                case "firefox":{
                    driver = new FirefoxDriver();
                    break;
                }
                case "edge":{
                    driver = new EdgeDriver();
                    break;
                }
            }
            driver.get("https://www.youtube.com/");
        }
}
