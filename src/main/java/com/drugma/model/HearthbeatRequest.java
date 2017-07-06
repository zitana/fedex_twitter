package com.drugma.model;

public class HearthbeatRequest {
  private String appid;
  private String status;

  public HearthbeatRequest(String appid, String status) {
    this.appid = appid;
    this.status = status;
  }

  public HearthbeatRequest() {
  }

  public String getAppid() {
    return appid;
  }

  public String getStatus() {
    return status;
  }
}
