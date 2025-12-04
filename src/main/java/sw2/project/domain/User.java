package sw2.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // [추가] 이름 필드

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean hasFamilyHistory;

    public User(String name, int age, Gender gender, boolean hasFamilyHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hasFamilyHistory = hasFamilyHistory;
    }
}