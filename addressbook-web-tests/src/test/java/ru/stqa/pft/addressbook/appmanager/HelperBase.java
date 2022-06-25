package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected WebDriver dr;

  public HelperBase(WebDriver dr) {
    this.dr = dr;
  }

  protected void click(By locator) {
    dr.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = dr.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        dr.findElement(locator).clear();
        dr.findElement(locator).sendKeys(text);
      }
    }
  }
  protected void accept() {
    dr.switchTo().alert().accept();
  }

  public boolean isAlertPresent() {
    try {
      dr.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      dr.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}