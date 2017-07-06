package com.drugma.controller;

import com.drugma.model.Status;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationContext;

@RestController
public class MainController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Status receive(@RequestParam(name = "tweet") String tweet, HttpServletRequest request) {
    String ACCESS_TOKEN = "883061129190281217-H08rqBOTeWiUafq49Kl7Ve557fl9NwV";
    String ACCESS_TOKEN_SECRET = "2PAVrjoqfUNQYoJhS3KmZxYZKMQjT6nh8vKORl0SX6mcN";
    String CONSUMER_KEY = "toU7QGnysDInPRhmtWooHU7rI";
    String CONSUMER_SECRET = "hBpVvPvxy0bI4mr0sL6iQE9ASov2h8SnkK5d7v2eYNxmtAK3Tf";
    if (request.getParameter("tweet") != null) {
      tweet = request.getParameter("tweet");
    }
//    AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
    OAuthAuthorization authorization = new OAuthAuthorization(ConfigurationContext.getInstance());
    Twitter twitter = new TwitterFactory().getInstance(authorization);
    try {
      //twitter.updateStatus("Hello World!");
      twitter.updateStatus(tweet);
    } catch (TwitterException e) {
      System.err.println("Error occurred while updating the status!");
    }
    return new Status("ok", tweet);
  }

}
