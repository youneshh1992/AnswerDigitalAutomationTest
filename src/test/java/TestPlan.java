import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main (String [] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    // Test Case 1 - Form Authentication

    @Test(testName = "Login - Correct Username & Incorrect Password")
    public static void loginWrongPassword(){
        driver.get(Utils.BASE_URL);
        PageElements pageElements = new PageElements(driver);
        pageElements.clickFormAuthenticationButton();
        pageElements.enterUsername();
        pageElements.enterWrongPassword();
        pageElements.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actualError = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedError = "Your password is invalid!" +
                "\n×";
        Assert.assertEquals(actualError, expectedError);

    }

    @Test(testName = "Login - Incorrect Username & Correct Password")
    public static void loginWrongUsername(){
        driver.get(Utils.BASE_URL);
        PageElements pageElements = new PageElements(driver);
        pageElements.clickFormAuthenticationButton();
        pageElements.enterWrongUsername();
        pageElements.enterPassword();
        pageElements.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actualError = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedError = "Your username is invalid!" +
                "\n×";
        Assert.assertEquals(actualError, expectedError);
    }

    @Test(testName = "Login - Correct Username & Correct Password")
    public static void loginCorrect(){
        driver.get(Utils.BASE_URL);
        PageElements pageElements = new PageElements(driver);
        pageElements.clickFormAuthenticationButton();
        pageElements.enterUsername();
        pageElements.enterPassword();
        pageElements.clickLoginButton();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actualError = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        String expectedError = "You logged into a secure area!" +
                "\n×";
        Assert.assertEquals(actualError, expectedError);
    }

    // Test Case 2 - Infinite Scroll

    @Test(testName = "Infinite Scroll - Scroll To Bottom of Page & Back Up To Top Of Page")
    public static void infiniteScroll() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        PageElements pageElements = new PageElements(driver);
        Thread.sleep(2000);
        pageElements.clickInfiniteScrollButton();
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 2000);");
        Thread.sleep(2000);
        jse.executeScript("window.scrollTo(0, 3000);");
        Thread.sleep(2000);
        jse.executeScript("window.scrollTo(0, -5000);");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String actualText = driver.findElement(By.xpath("//h3[contains(text(),'Infinite Scroll')]")).getText();
        String expectedText = "Infinite Scroll";
        Assert.assertEquals(actualText, expectedText);

    }

    // Test Case 3 - Key Presses

    @Test(testName = "Key Presses - Assert Text After Every Key Press")
    public static void keyPressAssert() throws InterruptedException{
        driver.get(Utils.BASE_URL);
        PageElements pageElements = new PageElements(driver);
        pageElements.clickKeyPressesButton();
        Thread.sleep(2000);

        pageElements.enterAltButton();
        String actualAltText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedAltText = "You entered: ALT";
        Assert.assertEquals(actualAltText, expectedAltText);
        Thread.sleep(2000);

        pageElements.enterBackSpaceButton();
        String actualBackSpaceText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedBackSpaceText = "You entered: BACK_SPACE";
        Assert.assertEquals(actualBackSpaceText, expectedBackSpaceText);
        Thread.sleep(2000);

        pageElements.enterTabButton();
        String actualTabText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedTabText = "You entered: TAB";
        Assert.assertEquals(actualTabText, expectedTabText);
        Thread.sleep(2000);

        pageElements.enterSpaceButton();
        String actualSpaceText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedSpaceText = "You entered: SPACE";
        Assert.assertEquals(actualSpaceText, expectedSpaceText);
        Thread.sleep(2000);
    }

    @AfterSuite
    public static void cleanup(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
