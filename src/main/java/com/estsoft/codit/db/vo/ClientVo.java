package com.estsoft.codit.db.vo;


public class ClientVo {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String passwd;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return passwd;
  }

  public void setPassword(String password) {
    this.passwd = password;
  }

  @Override
  public String toString() {
    return "ClientVo{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", passwd='" + passwd + '\'' +
        '}';
  }
}
