package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class ContactDeletionTests {
  private WebDriver dr;
  private boolean acceptNextAlert;


  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Project\\chromedriver.exe");
    dr = new ChromeDriver();
    dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    dr.get("http://localhost/addressbook/");
    dr.findElement(By.name("user")).click();
    dr.findElement(By.name("user")).clear();
    dr.findElement(By.name("user")).sendKeys("admin");
    dr.findElement(By.name("pass")).click();
    dr.findElement(By.name("pass")).clear();
    dr.findElement(By.name("pass")).sendKeys("secret");
    dr.findElement(By.cssSelector("input[type=\"submit\"]")).click();

    dr.findElement(By.xpath("//form[2]/table/tbody/tr[2]/td[1]/input[@id='4']")).click();
    dr.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    dr.findElement(By.linkText("Logout")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    dr.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      dr.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      dr.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = dr.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
