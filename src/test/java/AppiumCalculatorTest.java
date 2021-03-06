import static org.junit.Assert.assertEquals;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumCalculatorTest {

  private AndroidDriver<MobileElement> driver;

  @Before
  public void setUp() throws MalformedURLException, InterruptedException {

    final URL resource = getClass().getClassLoader().getResource("CTAppium_1_2.apk");

    final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("platformName", "android");
    desiredCapabilities.setCapability("deviceName", "emulator-5554");
    desiredCapabilities.setCapability("automationName", "uiautomator2");
    desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
    desiredCapabilities.setCapability(MobileCapabilityType.APP, resource.getPath());

    final URL remoteUrl = new URL("http://localhost:4723/wd/hub");

    driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);

    driver.manage().timeouts().implicitlyWait(BigDecimal.TEN.longValue(), TimeUnit.SECONDS);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void shouldAddNewName() {
    final List<MobileElement> elements =
        driver.findElements(By.className("android.widget.TextView"));

    elements.get(1).click();

    final MobileElement nomeEditText = driver.findElement(MobileBy.AccessibilityId("nome"));

    final String expectedValue = "expectedValue";

    nomeEditText.sendKeys(expectedValue);

    assertEquals(expectedValue, nomeEditText.getText());
  }
}
