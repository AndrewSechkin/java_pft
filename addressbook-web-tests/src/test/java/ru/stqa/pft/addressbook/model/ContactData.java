package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String homePhone;
  private final String mobilePhone;
  private final String email1;
  private final String email2;
  private String group;

  public ContactData(String firstName, String lastName, String address, String homePhone, String mobilePhone,
                     String email1, String email2, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.email1 = email1;
    this.email2 = email2;
    this.group = group;
  }

  public  String getFirstName() { return firstName; }

  public  String getLastName() { return lastName; }

  public  String getAddress() { return address; }

  public  String getHomePhone() { return homePhone; }

  public  String getMobilePhone() { return mobilePhone; }

  public  String getEmail1() { return email1; }

  public  String getEmail2() { return email2; }

  public String getGroup() {
    return group;
  }
}