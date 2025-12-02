package sw2.project.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class HealthLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;
    //1. 혈압 (수축기/이완기)
    private Integer systolicBp;
    private Integer diastolicBp;

    //2. 혈당
    private Integer bloodSugar;
    //3. 운동
    private Integer exerciseMinutes;

    //4. 식단 관련 (섭취량으로 확인)
    private Integer sodiumIntake; //나트륨 섭취량
    private String dietDescription; //식단 기록

    @Builder
    public HealthLog(User user, Integer systolicBp, Integer diastolicBp, Integer bloodSugar, Integer exerciseMinutes, Integer sodiumIntake) {
        this.user = user;
        this.date = LocalDate.now();
        this.systolicBp = systolicBp;
        this.diastolicBp = diastolicBp;
        this.bloodSugar = bloodSugar;
        this.exerciseMinutes = exerciseMinutes;
        this.sodiumIntake = sodiumIntake;
    }
}
