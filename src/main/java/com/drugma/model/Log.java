package com.drugma.model;

public class Log {
  private String appid;
  private String message;
  private int status;

  public Log(String appid, String message, int status) {
    this.appid = appid;
    this.message = message;
    this.status = status;
  }

  public Log() {
  }

  public String getAppid() {
    return appid;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }
}
