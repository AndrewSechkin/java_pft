package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupCreationTests {
  private WebDriver dr;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Project\\chromedriver.exe");
    dr = new ChromeDriver();
    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    dr.get("http://localhost/addressbook/group.php?selected%5B%5D=1&selected%5B%5D=2&selected%5B%5D=3&delete=Delete+group%28s%29");
    login(dr, "admin", "secret");
  }

  private static void login(WebDriver dr, String username, String password) {
    dr.findElement(By.name("user")).clear();
    dr.findElement(By.name("user")).sendKeys(username);
    dr.findElement(By.name("pass")).click();
    dr.findElement(By.name("pass")).clear();
    dr.findElement(By.name("pass")).sendKeys(password);
    dr.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCretion();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    dr.findElement(By.linkText("Logout")).click();
  }

  private void submitGroupCretion() {
    dr.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
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

  private void initGroupCreation() {
    dr.findElement(By.name("new")).click();
  }

  private void gotoGroupPage() {
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
}
