package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation()  {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Andrew").withLastName("Sechkin").withAddress("Russia").withHomePhone("12345").withMobilePhone("67890").withWorkPhone("1234567890").withEmail("test@test.ru").withEmail2("test@test.com").withEmail3("test@test.su").withGroup("test1");
    app.contact().initContactCreation();
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation()  {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Andrew'").withLastName("Sechkin").withAddress("Russia").withHomePhone("12345").withMobilePhone("67890").withWorkPhone("1234567890").withEmail("test@test.ru").withEmail2("test@test.com").withEmail3("test@test.su").withGroup("test1");
    app.contact().initContactCreation();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}