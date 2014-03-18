package com.zed.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private Facebook facebook;

    @Autowired
    private VKontakte vkontakte;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @RequestMapping(value = "/facebook", method=RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
        model.addAttribute("friends", friends);

        return "helloFacebook";
    }

    @RequestMapping(value = "/vkontakte", method=RequestMethod.GET)
    public String helloVkontakte(Model model) {
        try {
        if (!vkontakte.isAuthorized()) {
            return "redirect:/connect/vkontakte";
        }} catch (Exception e) {
            return "redirect:/connect/vkontakte";
        }

        model.addAttribute(vkontakte.usersOperations().getProfile());
        List<VKontakteProfile> friends = vkontakte.friendsOperations().get();
        model.addAttribute("friends", friends);

        return "helloVkontakte";
    }

    @RequestMapping(value = "/postHelloWorld", method=RequestMethod.GET)
    public String postHelloWorld(Model model) {
        try {
            if (!vkontakte.isAuthorized()) {
                return "redirect:/connect/vkontakte";
            }} catch (Exception e) {
            return "redirect:/connect/vkontakte";
        }

        vkontakte.wallOperations().post("Hello, World!");

        model.addAttribute(vkontakte.usersOperations().getProfile());
        List<VKontakteProfile> friends = vkontakte.friendsOperations().get();
        model.addAttribute("friends", friends);

        return "helloVkontakte";
    }

}