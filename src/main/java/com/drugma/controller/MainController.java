package com.drugma.controller;

import com.drugma.model.HearthbeatRequest;
import com.drugma.model.Status;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationContext;

@RestController
public class MainController {

  @RequestMapping(value = "/{tweet}", method = RequestMethod.GET)
  public Status receive(@PathVariable("tweet") String tweet) throws Exception {
    new RestTemplate().postForObject("https://natural-radar.glitch.me/hearthbeat", new HearthbeatRequest("HEROKU_TWEET", "STARTED"), HearthbeatRequest.class);
    OAuthAuthorization authorization = new OAuthAuthorization(ConfigurationContext.getInstance());
    Twitter twitter = new TwitterFactory().getInstance(authorization);
    try {
      twitter.updateStatus(tweet);
      new RestTemplate().postForObject("https://natural-radar.glitch.me/hearthbeat", new HearthbeatRequest("HEROKU_TWEET", "SUCCESS"), HearthbeatRequest.class);
    } catch (TwitterException e) {
      System.err.println("Error occurred while updating the status!");
      System.out.println(e.getMessage());
      new RestTemplate().postForObject("https://natural-radar.glitch.me/hearthbeat", new HearthbeatRequest("HEROKU_TWEET", "ERROR"), HearthbeatRequest.class);
    }
    return new Status("ok", tweet);
  }
}



