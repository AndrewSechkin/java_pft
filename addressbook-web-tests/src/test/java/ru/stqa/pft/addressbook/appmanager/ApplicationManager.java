package ru.stqa.pft.addressbook.appmanager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import java.util.concurrent.TimeUnit;
public class ApplicationManager {
  public WebDriver dr;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private final String browser;
  public ApplicationManager(String browser) {
    this.browser = browser;
  }
  public void init() {
    if (browser.equals(BrowserType.CHROME)) {
      System.setProperty("web-driver.chrome.driver", "C:\\Project/chromedriver");
      dr = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      System.setProperty("web-driver.gecko.driver", "C:\\Project/geckodriver");
      dr = new FirefoxDriver();
    }
    dr.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    dr.get("http://localhost/addressbook");
    groupHelper = new GroupHelper(dr);
    navigationHelper = new NavigationHelper(dr);
    contactHelper = new ContactHelper(dr);
    sessionHelper = new SessionHelper(dr);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    dr.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}