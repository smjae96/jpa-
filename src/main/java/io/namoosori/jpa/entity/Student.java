package io.namoosori.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="STUDENT_TB")
public class Student {

    @Id
    @GeneratedValue
    private Long studnetId;

    private String name;

    private String grade;

    // Major 객체 타입으로 가지고 있는 것이 객체 다운 것이다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAJORID") // 외래키로 받는 컬럼의 이름 지정
    private Major major;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
