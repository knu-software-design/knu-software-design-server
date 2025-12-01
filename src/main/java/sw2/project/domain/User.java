package sw2.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean hasFamilyHistory;
}

enum Gender {
    MALE,FEMALE;
}
