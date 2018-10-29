package com.myproject.projecta.web;

import com.myproject.projecta.domain.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class mainController implements ErrorController {

    UserRepository ur;
    MessageRepository mr;

    @Autowired
    public mainController(UserRepository ur, MessageRepository mr) {
        this.ur = ur;
        this.mr = mr;
    }

    //METHOD FOR CUSTOM ERROR PAGE
    final String PATH = "/error";
    @RequestMapping(value = PATH)
    public String returnError() {
        return "error";
    }
    @Override
    public String getErrorPath() {
        return PATH;
    }

    //REDIRECT FROM '/' TO PROFILE PAGE
    @RequestMapping("/")
    public String returnLogin() {
        return "redirect:profile";
    }
    //LOG-IN PAGE
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    /********************************/
    /*          USER SECTION        */
    /********************************/

    //SIGNUP USER
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(@ModelAttribute("signupform")SignupForm signupForm, Model model) {
        model.addAttribute("signupform", signupForm);
        model.addAttribute("number", 0);
        return "signup";
    }

    //SIGNUP SAVE USER
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult,
                           RedirectAttributes redirAttrs) {
        System.out.println(signupForm.toString());
        if(!bindingResult.hasErrors()) {
            Iterable<User> users = ur.findAll();
            boolean exists = false;

            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(signupForm.getUsername())) {
                    exists = true;
                    break;
                }
            }

            if(!exists) {
                String psw = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPsw = bc.encode(psw);
                ur.save(new User(signupForm.getUsername(), signupForm.getFirstname(), hashPsw, signupForm.getRole(), signupForm.getEmail()));
            } else {
                bindingResult.rejectValue("username", "err.username", "Username is already taken");
                return "signup";
            }
        } else {
            return "signup";
        }
        //SENDS ALERT TO REDIRECTED PAGE AFTER REGISTERING ACCOUNT
        redirAttrs.addFlashAttribute("alert", "Successfully registered!");
        return "redirect:login";
    }

    /********************************/
    /*          INDEX SECTION        */
    /********************************/
    // :(
    @RequestMapping(value = "/index")
    public String index() {
        return "redirect:/profile";
    }

    /********************************/
    /*          PROFILE SECTION      */
    /********************************/

    //PROFILE PAGE
    //RETURNS LOGGED IN USER'S FIRSTNAME AND EMAIL
    @RequestMapping(value = "/profile")
    public String myProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String cUser = authentication.getName();
        model.addAttribute("currentUser", ur.findByUsername(cUser));
        model.addAttribute("list", ur.findByUsername(cUser).getMessages());
        return "profile";
    }
    //MESSAGES PAGE
    @RequestMapping(value = "/messages")
    public String messages(Message message, Model model) {
        model.addAttribute("pubList", publicList());
        model.addAttribute("msgObj", message);
        return "messages";
    }
    List<Message> publicList() {
        Iterable<Message> it = mr.findAll();
        List<Message> publicList = new ArrayList<>();
        it.forEach(p -> {
            if(p.getVisibility() == true) publicList.add(p);
        });
        return publicList;
    }


    /********************************/
    /*          MSG SECTION         */
    /********************************/

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public String saveMsg(@ModelAttribute("form") Message message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        System.out.println(currentUser);
        message.setUser(ur.findByUsername(currentUser));
        mr.save(message);
        return "redirect:/profile";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMsg(@PathVariable("id") long id, Model model) {
        Optional<Message> msg = mr.findById(id);
        model.addAttribute("msgObj", msg);
        return "edit";
    }
    //DELETE MESSAGES IN PROFILE PAGE/MESSAGES PAGE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMsg(@PathVariable("id") long id, HttpServletRequest request) {
        //get the referer url to know where to redirect after deleting a message
        final String referer = request.getHeader("referer");
        mr.deleteById(id);
        return (referer.contains("profile") ? "redirect:/profile" : "redirect:/messages");
    }
}
