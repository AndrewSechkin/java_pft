package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests {
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
  public void testContactCreationTests() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Andrew", "Sechkin", "Artemovich", "Andrew", "1234567890", "QA", "Test@test.ru"));
    submitContactCretion();
    returnToContactPage();
  }

  private void returnToContactPage() {
    dr.findElement(By.linkText("Logout")).click();
  }

  private void submitContactCretion() {
    dr.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    dr.findElement(By.name("firstname")).click();
    dr.findElement(By.name("firstname")).clear();
    dr.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    dr.findElement(By.name("middlename")).click();
    dr.findElement(By.name("middlename")).clear();
    dr.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    dr.findElement(By.name("lastname")).click();
    dr.findElement(By.name("lastname")).clear();
    dr.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    dr.findElement(By.name("nickname")).click();
    dr.findElement(By.name("nickname")).clear();
    dr.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
    dr.findElement(By.name("mobile")).click();
    dr.findElement(By.name("mobile")).clear();
    dr.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    dr.findElement(By.name("work")).click();
    dr.findElement(By.name("work")).clear();
    dr.findElement(By.name("work")).sendKeys(contactData.getQa());
    dr.findElement(By.name("email")).click();
    dr.findElement(By.name("email")).clear();
    dr.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void initContactCreation() {
    dr.findElement(By.linkText("add new")).click();
    dr.get("http://localhost/addressbook/edit.php");
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
