package eu.dar3.notatki.controller;

import eu.dar3.notatki.role.RoleTypes;
import jakarta.servlet.ServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {

    @GetMapping("/")
    @Secured({"USER", "ADMIN", "SUPER_ADMIN"})
    public String redirectByRoles(ServletRequest request, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(RoleTypes.SUPER_ADMIN.name()))) {
                return "redirect:/note/list";
            } else if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(RoleTypes.ADMIN.name()))) {
                    return "redirect:/note/list-rw";
                } else if (userDetails.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(RoleTypes.USER.name()))) {
                    return "redirect:/note/list-ro";
                }
        }
        return "nothing";
    }
}
