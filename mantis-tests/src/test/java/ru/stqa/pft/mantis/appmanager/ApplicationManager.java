package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver dr;

  private String browser;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));



    if (browser.equals(BrowserType.FIREFOX)) {
      dr = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      dr = new ChromeDriver();
    }
    dr.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    dr.get(properties.getProperty("web.baseUrl"));

  }

  public void stop() {
    dr.quit();
  }

}