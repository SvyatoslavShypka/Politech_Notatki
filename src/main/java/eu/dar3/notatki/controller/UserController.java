package eu.dar3.notatki.controller;

import eu.dar3.notatki.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    private final UserAccountService userAccountService;

    @GetMapping("/users")
    @Secured({"SUPER_ADMIN"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", userAccountService.findAll());
        return "users";
    }
    //TODO 1. User Edit, Add form and functions
    //TODO 4. Add pagination
    //TODO 5. Add default start page

}
