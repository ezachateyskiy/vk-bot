package com.zed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    ConnectionRepository connectionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(Model model) {

        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection != null) {
            Facebook facebook = connection.getApi();
            facebook.feedOperations().updateStatus("I'm trying out Spring Social!");
        }
        return "home";
    }

}