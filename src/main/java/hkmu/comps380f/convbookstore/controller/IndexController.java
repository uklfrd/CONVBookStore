package hkmu.comps380f.convbookstore.controller;

import hkmu.comps380f.convbookstore.dao.UserManagementService;
import hkmu.comps380f.convbookstore.model.StoreUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Resource
    UserManagementService umService;
    @GetMapping("/")
    public String index() {
        return "redirect:/book/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/header/{username}")
    public String header(@PathVariable("username") String username,
            ModelMap model) {
        model.addAttribute("storeUser", umService.getStoreUser());

        StoreUser storeUser = umService.getStoreUser(username);
        model.addAttribute("username", username);
        model.addAttribute("storeUser", storeUser);
        return "header";
    }
}

