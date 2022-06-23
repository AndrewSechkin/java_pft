package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends  HelperBase {

  public NavigationHelper(WebDriver dr) {
    super(dr);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}