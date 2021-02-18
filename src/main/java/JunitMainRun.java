import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.jupiter.api.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.logging.Level;

public class JunitMainRun {
    // Junit report Location
    private String reportDirectory = "/Users/ignacio/workspace/angular-mobile-automation-appium/src/main/java/reports";
    private String reportFormat = "xml";
    private String testName = "LogInHaUser";
    //hauser to test
    private String user = "hauser";
    private String pass = "12345";

    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    //deviceCapabiltiy
    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "RF8NA12GQHK");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    //test
    @Test
    public void LogInHaUser() {
            driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='mail outline Log In with Email']")));
            driver.findElement(By.xpath("//*[@text='mail outline Log In with Email']")).click();
            driver.findElement(By.xpath("//*[@class='android.widget.EditText' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Company']]]")).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='H']")));
            driver.findElement(By.xpath("//*[@text='H']")).click();
            driver.findElement(By.xpath("//*[@text='a']")).click();
            driver.findElement(By.xpath("//*[@text='airplane outline HAWAIIAN AIRLINES (HA)']")).click();
            //driver.findElement(By.xpath("//*[@text='airplane outline HAWAIIAN AIRLINES (HA)']")).click();
            driver.findElement(By.xpath("//*[@class='android.widget.EditText' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Email or Employee Number']]]")).sendKeys(user);
            driver.findElement(By.xpath("//*[@class='android.widget.EditText' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@text='Password']]]")).sendKeys(pass);
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LOG IN']")));
            driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        Thread.sleep(5000);

        Location loc = new Location();
        loc.getLocation();

        System.out.println(loc.reportLocation);
        XmlFilter test = new XmlFilter();
        test.init(loc.reportLocation);

        System.out.println(Arrays.toString(test.arrayTestsFail));
        MessageToSend slackTest = new MessageToSend();
        slackTest.slack(test.arrayTestsFail,test.testPass,test.testFail,testName);

    }


}