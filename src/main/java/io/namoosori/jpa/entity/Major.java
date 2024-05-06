package io.namoosori.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="MAJOR_TB")
public class Major {

    @Id
    @GeneratedValue
    private Long majorId;

    private String name;

    private String category;

    private List<Student> students; // 양방향 관계일 때


    public Major(String name, String category) {
        this.name=name;
        this.category = category;
    }
}
