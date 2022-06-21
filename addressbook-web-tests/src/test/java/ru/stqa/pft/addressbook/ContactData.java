package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String mobile;
  private final String qa;
  private final String email;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String mobile, String qa, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.mobile = mobile;
    this.qa = qa;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getQa() {
    return qa;
  }

  public String getEmail() {
    return email;
  }
}
