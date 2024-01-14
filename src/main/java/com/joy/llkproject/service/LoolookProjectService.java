package com.joy.llkproject.service;

import com.joy.llkproject.entity.Room;
import com.joy.llkproject.entity.RoomForm;
import com.joy.llkproject.entity.User;
import com.joy.llkproject.entity.UserForm;
import com.joy.llkproject.repository.RoomRepository;
import com.joy.llkproject.repository.RoomRepository2;
import com.joy.llkproject.repository.UserRepository;
import com.joy.llkproject.repository.UserRepository2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoolookProjectService {

    private final RoomRepository roomRepository;
    private final RoomRepository2 roomRepository2;
    private final UserRepository userRepository;
    private final UserRepository2 userRepository2;

    public LoolookProjectService(
            RoomRepository roomRepository,
            RoomRepository2 roomRepository2,
            UserRepository userRepository,
            UserRepository2 userRepository2
    ) {
        this.roomRepository = roomRepository;
        this.roomRepository2 = roomRepository2;
        this.userRepository = userRepository;
        this.userRepository2 = userRepository2;
    }

    public List<Integer> findFloor(String userId, String type) {
        return roomRepository.findAllByString(userId,type);
    }

    public List<Room> findRooms(String userId, String type, String floor) {
        return roomRepository.findAllByString2(userId,type,Integer.parseInt(floor));
    }

    @Transactional
    public void updateRoom(String userId, String id, String newOccupy){
        Room room = roomRepository.find(Long.parseLong(id));
        room.setId(Long.parseLong(id));
        room.setOccupy(newOccupy);
        roomRepository.save(room);
    }

    public User findUser(String userId) {
        return userRepository.find(userId);
    }

    public List<User> findAll() {
        return userRepository2.findAll();
    }

    @Transactional
    public void save(UserForm userForm) {
        User user = new User();
        user.setUserId(userForm.getUserId());
        user.setUserPw(userForm.getUserPw());
        user.setUserName(userForm.getUserName());
        user.setAddress(userForm.getAddress());
        userRepository2.save(user);

        List<Room> roomList = new ArrayList<>();
        int cnt = userForm.getRoomCount();
        for (int i = 1; i <= cnt; i++) {
            Room newRoom = new Room();
            newRoom.setUser(user);
            newRoom.setRoomType(userForm.getRoomType());
            newRoom.setFloor(userForm.getFloor());
            newRoom.setRoomNumber(i+"");
            roomList.add(newRoom);
        }
        roomRepository2.saveAll(roomList);
    }

    @Transactional
    public void delete(String userId) {
        userRepository2.deleteById(userId);
    }


    public List<Room> findUserRoom(String userId) {
        List<Room> allByUser = roomRepository.findAllByUserId(userId);
        for (Room room : allByUser){

        }
        return allByUser;
    }

    @Transactional
    public void update(UserForm userForm) {
        User user = userRepository.find(userForm.getUserId());
        user.setUserName(userForm.getUserName());
        user.setAddress(userForm.getAddress());

        List<Room> roomList = roomRepository2.getRoomByUserId(userForm.getUserId());
        List<RoomForm> roomFormList = userForm.getRoomList();
        for (int i = 1; i <= roomFormList.size(); i++) {
            for (int j = 0; j < roomList.size(); j++) {
                
            }
//            Room newRoom = new Room();
//            newRoom.setUser(user);
//            newRoom.setRoomType(userForm.getRoomType());
//            newRoom.setFloor(userForm.getFloor());
//            newRoom.setRoomNumber(i+"");
//            roomList.add(newRoom);
        }
    }
}
