package com.joy.llkproject.controller;

import com.joy.llkproject.entity.Room;
import com.joy.llkproject.entity.User;
import com.joy.llkproject.service.LoolookProjectService;
import com.joy.llkproject.websocket.LooWebsocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoolookProjectController {
    static final String jsonFormat = "{\"status\":\"%s\",\"id\":\"%s\"}";
    private final LooWebsocketHandler websocketHandler;
    private final LoolookProjectService hpptProjectService;

    public LoolookProjectController(
            LoolookProjectService hpptProjectService,
            LooWebsocketHandler websocketHandler
    ) {
        this.hpptProjectService = hpptProjectService;
        this.websocketHandler = websocketHandler;
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
    public String lockRoom(@PathVariable String id){
        String result = "success";
        try {
            hpptProjectService.lockRoom(id);
            // WebSocket 확인
        }catch (Exception e){
            e.printStackTrace();
            result = "fail";
        }
        String message = String.format(jsonFormat,result,id);
        websocketHandler.sendResult(message);
        return "room/detail";
    }

    @PostMapping("/api/unlock/{id}")
    public String unlockRoom(@PathVariable String id){
        String result = "success";
        try {
            hpptProjectService.unlockRoom(id);
            // WebSocket 확인
            String message = String.format(jsonFormat,result,id);
            websocketHandler.sendResult(message);
        }catch (Exception e){
            e.printStackTrace();
            result = "fail";
        }
        String message = String.format(jsonFormat,result,id);
        websocketHandler.sendResult(message);
        return "room/detail";
    }

}
