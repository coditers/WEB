package com.estsoft.codit.core.vo;

public class Test_caseVo {
  int id;
  String input;
  int problem_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public int getProblem_id() {
    return problem_id;
  }

  public void setProblem_id(int problem_id) {
    this.problem_id = problem_id;
  }

  @Override
  public String toString() {
    return "Test_caseVo{"
        + "id=" + id
        + ", input='" + input + '\''
        + ", problem_id=" + problem_id + '}';
  }
}
