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

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserRestController {

    private final UserAccountService userAccountService;

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/edit")
    public String showAddUserForm(UserAccountDto userAccountDto) {
        return "add-user";
    }

    @Secured({"SUPER_ADMIN"})
    @PostMapping("/edit")
    public String addUser(UserAccountDto userAccountDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userAccountService.createUser((UserDetails) userAccountDto);
        return "redirect:/users";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserAccountDto userAccountDto = userAccountService.getById(id);
        model.addAttribute("user", userAccountDto);
        return "update-user";
    }

    @Secured("SUPER_ADMIN")
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, UserAccountDto userAccountDto, BindingResult result) {
        if (result.hasErrors()) {
            userAccountDto.setId(id);
            return "update-user";
        }
        userAccountService.updateUser((UserDetails) userAccountDto);
        return "redirect:/users";
    }

    @Secured("SUPER_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userAccountService.deleteById(id);
        return "redirect:/users";
    }
}
