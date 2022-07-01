package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.ContactCreationTests;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver dr) {
    super(dr);
  }

  public void returnToHomePage() {

    if (isElementPresent(By.linkText("home page"))) {
      click(By.linkText("home page"));
    }
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("phone2"), contactData.getHomePhone2());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    // attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(dr.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    System.out.println("1");
    click(By.xpath("//input[21]"));
    System.out.println("2");
  }

  public void selectContactById(int id) {
    dr.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModification(int id) {
    dr.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  // public Set<ContactData> all() {
  //  Set<ContactData> contacts = new HashSet<ContactData>();
  //  List<WebElement> rows = wd.findElements(By.name("entry"));
  //   for (WebElement row : rows) {
  //     List<WebElement> cells = row.findElements(By.tagName("td"));
  //     int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
  //     String lastname = cells.get(1).getText();
  //     String firstname = cells.get(2).getText();
  //     String address = cells.get(3).getText();
  //    String allEmails = cells.get(4).getText();
  //     String allPhones = cells.get(5).getText();
  //     contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));

//    }
  //   return contacts;
  // }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDeletion() {
    accept();
  }

  public void add(ContactData contact) throws InterruptedException {
    Groups groups = app.db().groups();
    if (!contact.getGroups().isEmpty()) {
      Groups groupsInContact = contact.getGroups();
      int groupsInContactSize = groupsInContact.size();
      while (groupsInContactSize > 0) {
        GroupData group = groupsInContact.iterator().next();
        delete(group);
        groupsInContactSize--;
      }
    } else {
      returnToHomePage();
      selectContactById(contact.getId());
      addToGroup(groups);
      returnToHomePage();
      Groups groups1 = app.db().groups();
      System.out.println(contact.getGroups());

    }
  }

  public ContactData findContactThatCanBeAddedToSomeGroup(Contacts contacts, int groupSize) {
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < groupSize) {
        return contact;
      }
    }
    return null;
  }

  public ContactData findContactThatCanBeDeletedFromSomeGroup(Contacts contacts, int groupSize) {
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() >= groupSize) {
        return contact;
      }
    }
    return contacts.iterator().next();
  }

  public void addToGroup(Groups groups) throws InterruptedException {

    //  Groups groups = app.db().groups();
    new Select(dr.findElement(By.name("to_group"))).selectByValue(String.valueOf(groups.iterator().next().getId()));
    Thread.sleep(2000);
    click(By.name("add"));
    Thread.sleep(2000);
    System.out.println();

  }

  public void deleteFromGroup() throws InterruptedException {
    // new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groups.iterator().next().getId()));
    Thread.sleep(2000);
    click(By.name("remove"));  //wd.findElement(By.cssSelector("input[value='Delete']")).click();
    Thread.sleep(2000);
    //   System.out.println();
  }

  public GroupData selectGroupForDeletion(Groups groups)  throws InterruptedException {
    new Select(dr.findElement(By.name("group"))).selectByValue(String.valueOf(groups.iterator().next().getId()));
    Thread.sleep(2000);
    //      click(By.name("remove"));
    //       Thread.sleep(2000);
    return groups.iterator().next();
  }

//    selectContactById(contact.getId());
//    Groups beforeGroups = app.db().groups();
//    Groups groupsInContact = contact.getGroups();



  //   System.out.println(groupsInContact);
  //   if (groupsInContact.iterator().hasNext()) {
  //     System.out.println("inside 1 if");
  //    if (groupsInContact.iterator().next().getId() == beforeGroups.iterator().next().getId()) {
  //       System.out.println("inside 2nd if");
  //      returnToHomePage();
  //     } else {
  //      System.out.println("inside else");
  //     new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(beforeGroups.iterator().next().getName());
  //     click(By.name("add"));
  //      returnToHomePage();
  //     }
  //    System.out.println("outside 2nd if");
  //   }
  //   System.out.println("outside 1 if");
//  }


  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }


  public void selectGroupById(int id) {
    dr.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void delete(GroupData group) {
    click(By.linkText("groups"));
    selectGroupById(group.getId());
    deleteSelectedGroups();
    //   groupCache = null;
    returnToGroupPage();
  }


  public void returnToGroupPage() {
    if (isElementPresent(By.linkText("group page"))) {
      click(By.linkText("group page"));
    }
    click(By.linkText("groups"));
  }

  // ContactData contact = findContactThatCanBeAddedToSomeGroup(contacts, groupSize);

//  GroupData group = findGroupThatDoesNotIncludeContact(contact, groups);

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    acceptDeletion();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  private Contacts contactCache = null;

  public int count() {
    return dr.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = dr.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      //    String firstName = cells.get(2).getText();
      //   String lastName = cells.get(1).getText();
      //   int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //   contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      //  contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = dr.findElement(By.name("firstname")).getAttribute("value");
    String lastname = dr.findElement(By.name("lastname")).getAttribute("value");
    String home = dr.findElement(By.name("home")).getAttribute("value");
    String mobile = dr.findElement(By.name("mobile")).getAttribute("value");
    String work = dr.findElement(By.name("work")).getAttribute("value");
    String phone2 = dr.findElement(By.name("phone2")).getAttribute("value");
    String address = dr.findElement(By.name("address")).getAttribute("value");
    String email = dr.findElement(By.name("email")).getAttribute("value");
    String email2 = dr.findElement(By.name("email2")).getAttribute("value");
    String email3 = dr.findElement(By.name("email3")).getAttribute("value");

    dr.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3).withHomePhone2(phone2);
  }

  private  void initContactModificationById(int id) {
    WebElement checkbox = dr.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

}