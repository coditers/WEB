package com.estsoft.codit.db.vo;


public class ClientVo {
  int id;
  String name;
  String email;
  String password;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "ClientVo{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", email='" + email + '\''
        + ", password='" + password + '\'' + '}';

  }
}