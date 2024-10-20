package eu.dar3.notatki.controller;

import eu.dar3.notatki.dto.UserAccountDto;
import eu.dar3.notatki.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserAccountService userAccountService;

    @GetMapping("/users")
    @Secured({"SUPER_ADMIN"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", userAccountService.findAll());
        return "users";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/user/edit")
    public String showAddUserForm(UserAccountDto userAccountDto) {
        return "add-user";
    }

    @Secured({"SUPER_ADMIN"})
    @PostMapping("/user/edit")
    public String addUser(UserAccountDto userAccountDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userAccountService.createUser((UserDetails) userAccountDto);
        return "redirect:/users";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserAccountDto userAccountDto = userAccountService.getById(id);
        model.addAttribute("user", userAccountDto);
        return "update-user";
    }

    @Secured("SUPER_ADMIN")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") long id, UserAccountDto userAccountDto, BindingResult result) {
        if (result.hasErrors()) {
            userAccountDto.setId(id);
            return "update-user";
        }
        userAccountService.updateUser((UserDetails) userAccountDto);
        return "redirect:/users";
    }

    @Secured("SUPER_ADMIN")
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userAccountService.deleteById(id);
        return "redirect:/users";
    }
}
