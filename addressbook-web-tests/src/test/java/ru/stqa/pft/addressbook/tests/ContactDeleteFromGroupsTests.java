package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/stru.png");
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstName("Andrew").withLastName("Sechkin").withAddress("Russia").withHomePhone("12345").withHomePhone2("67890").withMobilePhone("1234567890").withWorkPhone("1234567890").withEmail("test@test.ru").withEmail2("test@test.com").withEmail3("test@test.su").withPhoto(photo));

      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
      }
    }
  }

  @Test
  public void testContactDeletionFromGroups() throws InterruptedException {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    int groupsSize = groups.size();
    ContactData contact = app.contact().findContactThatCanBeDeletedFromSomeGroup(contacts, groupsSize);
    GroupData group = app.contact().selectGroupForDeletion(groups);
    Groups before = contact.getGroups();
    int groupsCount = contact.getGroups().size();
    if (groupsCount != 0) {
      app.contact().selectContactById(contact.getId());
      app.contact().deleteFromGroup();
      int contactId = contact.getId();
      ContactData contactDeleted = app.db().contact(contactId);
      Groups after = contactDeleted.getGroups();
      System.out.println(contact.getGroups().size());
      assertThat(after, equalTo(
              before.without(group)));

    } else {
      System.out.println("There are no contacts in selected group");
    }
  }
}