package sw2.project.domain;

import jakarta.persistence.*;
import lombok.*;
import sw2.project.common.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Routine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String timeOfDay;

    @Column(nullable = false)
    private boolean completed;

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
