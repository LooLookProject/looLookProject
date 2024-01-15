package com.joy.llkproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name = "llk_room")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    User user;
    Integer floor;
    String roomNumber;
    String roomType;
    @ColumnDefault("N")
    String occupy;
    @ColumnDefault("Y")
    String vh;

}
