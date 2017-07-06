package com.drugma.model;

public class Status {
  private String status;
  private String message;

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public Status(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public Status() {
  }
}
