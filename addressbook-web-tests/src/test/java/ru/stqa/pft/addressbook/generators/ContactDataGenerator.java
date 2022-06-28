package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public  int count;

  @Parameter(names = "-f", description = "Target file")
  public  String file;

  @Parameter(names = "-d", description = "Data format")
  public  String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File (file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }

  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getHomePhone(), contact.getHomePhone2(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getPhoto()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      File photo = new File("src/test/resources/stru.png");
      contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("LastName %s", i)).withAddress(String.format("Address %s", i)).withHomePhone(String.format("11111111 %s", i)).withHomePhone2(String.format("22222222 %s", i)).withMobilePhone(String.format("33333333 %s", i)).withWorkPhone(String.format("44444444 %s", i)).withEmail(String.format("test@test.com %s", i)).withEmail2(String.format("test2@test.com %s", i)).withEmail3(String.format("test3@test.com %s", i)).withPhoto(photo));
    }
    return contacts;
  }
}