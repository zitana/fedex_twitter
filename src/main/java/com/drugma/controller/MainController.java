package com.drugma.controller;

import com.drugma.model.Status;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Status receive(@RequestParam(name = "url") String url) {
    return new Status("ok", url);
  }

}
