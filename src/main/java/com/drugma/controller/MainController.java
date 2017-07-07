package com.drugma.controller;

import com.drugma.model.HearthbeatRequest;
import com.drugma.model.Status;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationContext;
import java.net.*;
import java.io.*;

@RestController
public class MainController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Status receive() {
    String tweet = URLConnectionReader.getText("https://raw.githubusercontent.com/gy0p4k/fedex/master/content");

    OAuthAuthorization authorization = new OAuthAuthorization(ConfigurationContext.getInstance());
    Twitter twitter = new TwitterFactory().getInstance(authorization);
    try {
      twitter.updateStatus(tweet);
    } catch (TwitterException e) {
      System.err.println("Error occurred while updating the status!");
      System.out.println(e.getMessage());
    }
    new RestTemplate().postForObject("https://natural-radar.glitch.me/hearthbeat", new HearthbeatRequest("GITHUB_BUILD", "SUCCESS"), HearthbeatRequest.class);
    return new Status("ok", tweet);
  }

}



class URLConnectionReader {
    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

}
