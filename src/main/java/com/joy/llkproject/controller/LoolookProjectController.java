package com.joy.llkproject.controller;

import com.joy.llkproject.entity.Room;
import com.joy.llkproject.entity.User;
import com.joy.llkproject.service.LoolookProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoolookProjectController {

    private final LoolookProjectService hpptProjectService;

    public LoolookProjectController(
            LoolookProjectService hpptProjectService
    ) {
        this.hpptProjectService = hpptProjectService;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("data","hello");
        return "main";
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public String userFind(@PathVariable String userId, Model model){
        User user = hpptProjectService.findUser(userId);
        String message="FAIL";
        if(user!=null){
            message="SUCCESS";
        }
        return message;
    }

    @GetMapping("/page/{userId}")
    public String userPage(@PathVariable String userId, Model model){
        model.addAttribute("userId",userId);
        return "room/detail";
    }

    @GetMapping("/floor/{userId}/{type}")
    @ResponseBody
    public List<Integer> userFloor(@PathVariable String userId,@PathVariable String type, Model model){
        List<Integer> floors = hpptProjectService.findFloor(userId,type);
        return floors;
    }

    @GetMapping("/room/{userId}/{type}/{floor}")
    @ResponseBody
    public List<Room> userRooms(@PathVariable String userId,@PathVariable String type,
                                   @PathVariable String floor){
        List<Room> rooms = hpptProjectService.findRooms(userId,type,floor);
        return rooms;
    }

    @PostMapping("/api/lock/{id}")
    @ResponseBody
    public String lockRoom(@PathVariable String id){
        String result = "success";
        try {
            hpptProjectService.lockRoom(id);
        }catch (Exception e){
            result="fail";
        }
        return result;
    }

    @PostMapping("/api/unlock/{id}")
    @ResponseBody
    public String unlockRoom(@PathVariable String id){
        String result = "success";
        try {
            hpptProjectService.unlockRoom(id);
        }catch (Exception e){
            result="fail";
        }
        return result;
    }
}
