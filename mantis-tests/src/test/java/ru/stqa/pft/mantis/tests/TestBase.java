package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

  // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.ftp.restore("config_inc.php.bak", "config_inc.php");
    app.stop();

  }
  boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.baseUrl") + "api/soap/mantisconnect.php"));
    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    ObjectRef status = issue.getStatus();
    String statusName = status.getName();
    System.out.println(statusName);
    if (statusName.equals("resolved")) {
      return false;
    } else {
      return true;
    }

  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}