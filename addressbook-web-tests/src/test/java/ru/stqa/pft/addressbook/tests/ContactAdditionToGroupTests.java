package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAdditionToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName("test_1"));//.withGroup("test1"));
      app.contact().create(new ContactData().withFirstName("test_2"));
      app.contact().create(new ContactData().withFirstName("test_3"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      app.group().create(new GroupData().withName("test2"));
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testContactAdditionToGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    app.goTo().contactPage();
    app.contact().selectContact(contacts, groups);
    app.contact().deleteContact(contacts, groups);
  }

}