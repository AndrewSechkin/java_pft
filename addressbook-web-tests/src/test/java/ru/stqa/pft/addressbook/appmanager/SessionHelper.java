package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver dr) {
    super(dr);
  }

  public void login(WebDriver dr, String username, String password) {
    type(By.name("user"), username);
    type(By.name("pass"), password);
    click(By.cssSelector("input[type=\"submit\"]"));
  }
}
