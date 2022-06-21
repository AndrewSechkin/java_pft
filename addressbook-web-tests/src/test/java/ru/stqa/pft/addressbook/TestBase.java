package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
  private WebDriver dr;

  private static void login(WebDriver dr, String username, String password) {
    dr.findElement(By.name("user")).clear();
    dr.findElement(By.name("user")).sendKeys(username);
    dr.findElement(By.name("pass")).click();
    dr.findElement(By.name("pass")).clear();
    dr.findElement(By.name("pass")).sendKeys(password);
    dr.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  }

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Project\\chromedriver.exe");
    dr = new ChromeDriver();
    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dr.get("http://localhost/addressbook/group.php?selected%5B%5D=1&selected%5B%5D=2&selected%5B%5D=3&delete=Delete+group%28s%29");
    TestBase.login(dr, "admin", "secret");
  }

  protected void returnToGroupPage() {
    dr.findElement(By.linkText("Logout")).click();
  }

  protected void submitGroupCretion() {
    dr.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupData groupData) {
    dr.findElement(By.name("group_name")).click();
    dr.findElement(By.name("group_name")).clear();
    dr.findElement(By.name("group_name")).sendKeys(groupData.getName());
    dr.findElement(By.name("group_header")).click();
    dr.findElement(By.name("group_header")).clear();
    dr.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    dr.findElement(By.name("group_footer")).click();
    dr.findElement(By.name("group_footer")).clear();
    dr.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  protected void initGroupCreation() {
    dr.findElement(By.name("new")).click();
  }

  protected void gotoGroupPage() {
    dr.findElement(By.linkText("group page")).click();
    dr.get("http://localhost/addressbook/group.php");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    dr.quit();
  }

  private boolean isAlertPresent() {
    try {
      dr.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected void deleteSelectGroups() {
    dr.findElement(By.name("delete")).click();
  }

  protected void selectGroup() {
    dr.findElement(By.name("selected[]")).click();
  }
}
