package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    public WebDriver driver;

    public WebDriver initialiseDriver() throws IOException {

        String browserName = PropertyFileReaderConfig.fileRead("basicData").getProperty("browser");


        if(browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","Drivers/chromedriver");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver","Drivers/geckodriver");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String URL = PropertyFileReaderConfig.fileRead("basicData").getProperty("URL");
        driver.get(URL);
        return driver;

    }

    public void closeDriver() throws IOException {

        driver.close();

    }

    public String getScreenShots(String testName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File ss = ts.getScreenshotAs(OutputType.FILE);
        String destLocation=System.getProperty("user.dir")+"/reports/"+testName+".png";
        try {
            org.apache.commons.io.FileUtils.copyFile(ss,new File(destLocation));
        } catch (IOException e) {

        }

        return destLocation;

    }
}
