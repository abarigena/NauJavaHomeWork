package ru.danil.NauJava.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.service.userService.UserService;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrerUser(User user, Model model){
        try{
            userService.addUser(user);
            return "redirect:/login";
        }
        catch(Exception e){
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
