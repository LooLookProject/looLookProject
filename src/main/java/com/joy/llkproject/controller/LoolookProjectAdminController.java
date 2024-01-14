package com.joy.llkproject.controller;

import com.joy.llkproject.entity.Room;
import com.joy.llkproject.entity.User;
import com.joy.llkproject.entity.UserForm;
import com.joy.llkproject.service.LoolookProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class LoolookProjectAdminController {

    private final LoolookProjectService hpptProjectService;

    public LoolookProjectAdminController(
            LoolookProjectService hpptProjectService
    ) {
        this.hpptProjectService = hpptProjectService;
    }

    @GetMapping("/admin")
    public String main(Model model){
        List<User> userList = hpptProjectService.findAll();
        model.addAttribute("userList",userList);
        return "admin/main";
    }

    /**
     * User Detail
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/admin/detail/{userId}")
    public String detail(@PathVariable String userId, Model model){
        User user = hpptProjectService.findUser(userId);
        List<Room> room = hpptProjectService.findUserRoom(userId);
        model.addAttribute("user",user);
        model.addAttribute("roomList",room);
        return "admin/detail";
    }

    /**
     * Create User Form
     * @param model
     * @return
     */
    @GetMapping("/admin/create")
    public String createForm(@ModelAttribute UserForm userForm, Model model){
        model.addAttribute("userForm",userForm);
        return "admin/form";
    }

    /**
     * Create User
     * @param model
     * @return
     */
    @PostMapping("/admin/create")
    public RedirectView create(UserForm userForm, BindingResult result, Model model){
//        hpptProjectService.save(user);
        hpptProjectService.save(userForm);
        return new RedirectView("/admin");
    }

    /**
     * Update User
     * @param model
     * @return
     */
    @PostMapping("/admin/update")
    public RedirectView update(UserForm userForm, Model model){
        hpptProjectService.update(userForm);
        return new RedirectView("/admin");
    }

    /**
     * Delete User/Room
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/admin/delete/{userId}")
    public RedirectView delete(@PathVariable String userId, Model model){
        hpptProjectService.delete(userId);
        return new RedirectView("/admin");
    }

}
