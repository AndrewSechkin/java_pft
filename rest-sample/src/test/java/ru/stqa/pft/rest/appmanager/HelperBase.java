package ru.stqa.pft.rest.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {

  protected ApplicationManager app;
  protected WebDriver dr;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.dr = app.getDriver();
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

  protected void attach(By locator, File file) {
    if (file != null) {
      dr.findElement(locator).sendKeys(file.getAbsolutePath());
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
