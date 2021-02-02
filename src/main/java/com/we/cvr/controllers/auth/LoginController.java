package com.we.cvr.controllers.auth;

import com.we.cvr.Constants;
import com.we.cvr.models.auth.User;
import com.we.cvr.services.cvbuilder.CvManagerService;
import com.we.cvr.services.cvbuilder.TemplateService;
import com.we.cvr.services.cvbuilder.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    UserManagerService userManagerService;


    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        //System.out.println(model.getAttribute("error"));
        User user = (User) request.getSession().getAttribute(Constants.USER_TAG);
        if (user != null)
            return "redirect:/welcome";
        return Constants.LOGIN_PAGE;
    }



    @PostMapping("/login")
    public String login(@ModelAttribute User user, RedirectAttributes model, HttpServletRequest request) {
        User authenticatedUser = userManagerService.login(user);

        if (authenticatedUser == null) {
            model.addFlashAttribute("error", "Login failed");
            return "redirect:/login";
        }

        request.getSession().setAttribute("user", authenticatedUser);

        model.addAttribute("user", authenticatedUser);

        return "redirect:/welcome";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if(request.getSession().getAttribute(Constants.USER_TAG)!=null)
            request.getSession().removeAttribute(Constants.USER_TAG);

        return "redirect:/login";
    }


    @GetMapping("/signup")
    public String signup(HttpServletRequest request) {
        return Constants.SIGNUP_PAGE;
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, HttpServletRequest request) {
        userManagerService.addUser(user);
        return "redirect:/login";
    }



}
