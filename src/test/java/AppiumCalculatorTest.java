import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumCalculatorTest {

  private AndroidDriver<MobileElement> driver;

  @Before
  public void setUp() throws MalformedURLException {

    final URL resource = getClass().getClassLoader().getResource("CTAppium_1_2.apk");

    final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", "android");
    desiredCapabilities.setCapability("deviceName", "emulator-5554");
    desiredCapabilities.setCapability("automationName", "uiautomator2");
    desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
    desiredCapabilities.setCapability(MobileCapabilityType.APP, resource.getPath());

    final URL remoteUrl = new URL("http://localhost:4723/wd/hub");

    driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
  }

  @After
  public void tearDown() {
    driver.quit();
  }
}
