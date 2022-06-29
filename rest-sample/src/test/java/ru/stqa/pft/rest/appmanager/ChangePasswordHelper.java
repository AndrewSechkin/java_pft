package ru.stqa.pft.rest.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String email) {
    dr.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), email);
    click(By.cssSelector("input[type='submit']"));

  }

  public void start(String username, String email) {
    dr.get(app.getProperty("web.baseUrl") + "/signup_page.php");

    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));

  }

  public void moveToManageUsers(String user) throws InterruptedException {
    //  click(By.cssSelector("a[href*='\"/mantisbt-2.25.5/manage_overview_page.php\"']"));
    click(By.id("menu-toggler"));
    click(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a"));
    click(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a"));
    click(By.linkText(user));
    Thread.sleep(2000);
    // click(By.cssSelector("input[value='Сбросить пароль']")); //*[@id="manage-user-reset-form"]/fieldset/span/input
    click(By.xpath("//*[@id=\"manage-user-reset-form\"]/fieldset/span/input"));
    Thread.sleep(5000);
    //  click(By.cssSelector("a[href*='\"/mantisbt-2.25.5/manage_user_page.php\"']"));
  }

  public void finishConfirmation(String confirmationLink, String user, String password) {
    dr.get(confirmationLink);
    type(By.name("realname"), user);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}