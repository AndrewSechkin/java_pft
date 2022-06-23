package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver dr;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;

  public void init() {
    dr = new ChromeDriver();
    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dr.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(dr);
    navigationHelper = new NavigationHelper(dr);
    contactHelper = new ContactHelper(dr);
    sessionHelper = new SessionHelper(dr);
    sessionHelper.login("admin", "secret");
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

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}