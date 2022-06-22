package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected static WebDriver dr;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;


  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\Project\\chromedriver.exe");
    dr = new ChromeDriver();
    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dr.get("http://localhost/addressbook/group.php?selected%5B%5D=1&selected%5B%5D=2&selected%5B%5D=3&delete=Delete+group%28s%29");
    groupHelper = new GroupHelper(dr);
    navigationHelper = new NavigationHelper(dr);
    sessionHelper = new SessionHelper(dr);
    sessionHelper.login(dr, "admin", "secret");
  }

  public void stop() {
    dr.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
