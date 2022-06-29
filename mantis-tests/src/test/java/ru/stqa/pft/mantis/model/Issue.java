package ru.stqa.pft.mantis.model;

public class Issue {

  private int id;
  private String summary;
  private String description;
  private Project project;
  private String state;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;

  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public String getState() {
    return state;
  }

  public Issue withState(String state) {
    this.state = state;
    return this;
  }
}