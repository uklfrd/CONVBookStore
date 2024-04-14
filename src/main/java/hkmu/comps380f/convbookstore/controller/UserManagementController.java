package hkmu.comps380f.convbookstore.controller;

import hkmu.comps380f.convbookstore.dao.UserManagementService;
import hkmu.comps380f.convbookstore.model.StoreUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserManagementController {
    @Resource
    UserManagementService umService;

    @GetMapping({"", "/", "/list"})
    public String list(HttpServletRequest request, ModelMap model) {

        String action = request.getParameter("action");
        if (action == null)
            action = "browse";

        switch (action) {
            case "userInformation":
                return userInformation(model);
            case "browse":
            default:
                return browse(model);
        }
    }
    private  String userInformation(ModelMap model) {
        model.addAttribute("storeUser", umService.getStoreUser());
        return "userInformation";
    }

    private  String browse(ModelMap model) {
        model.addAttribute("storeUser", umService.getStoreUser());
        return "listUser";
    }


    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "storeUserForm", new Form());
    }

    public static class Form {
        private String username;
        private String password;
        private String fullname;
        private String email;
        private String address;
        private String[] roles;

        // getters and setters for all properties
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

    }

    @PostMapping("/create")
    public String create(Form form) throws IOException {
        umService.createStoreUser(form.getUsername(),
                form.getPassword(), form.getFullname(), form.getEmail(), form.getAddress(), form.getRoles());
        return "redirect:/user/list";
    }

    @GetMapping("/view/{username}")
    public String view(@PathVariable("username") String username,
                       ModelMap model) {
        StoreUser storeUser = umService.getStoreUser(username);
        model.addAttribute("username", username);
        model.addAttribute("storeUser", storeUser);
        return "userInformation";
    }

    @GetMapping("/edit/{username}")
    public ModelAndView showEdit(@PathVariable("username") String username,
                                 Principal principal, HttpServletRequest request) {
        StoreUser storeUser = umService.getStoreUser(username);
        if (storeUser == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(storeUser.getUsername()))) {
            return new ModelAndView(new RedirectView("/user/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("storeUser", storeUser);
        Form storeUserForm = new Form();
        storeUserForm.setUsername(storeUser.getUsername());
        storeUserForm.setPassword(storeUser.getPassword());
        storeUserForm.setFullname(storeUser.getFullname());
        storeUserForm.setEmail(storeUser.getEmail());
        storeUserForm.setAddress(storeUser.getAddress());
        modelAndView.addObject("storeUserForm", storeUserForm);
        return modelAndView;
    }

    @PostMapping("/edit/{username}")
    public String edit(@PathVariable("username") String username, UserManagementController.Form form,
                       Principal principal, HttpServletRequest request) {
        StoreUser storeUser = umService.getStoreUser(username);
        if (username == null
                || (!request.isUserInRole("ROLE_ADMIN")
                && !principal.getName().equals(storeUser.getUsername()))) {
            return "redirect:/user/list";
        }
        umService.updateUser(username,form.getPassword(),form.getFullname() ,form.getEmail() ,form.getAddress());
        return "redirect:/user/view/" + username;
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        umService.delete(username);
        return "redirect:/user/list";
    }
}