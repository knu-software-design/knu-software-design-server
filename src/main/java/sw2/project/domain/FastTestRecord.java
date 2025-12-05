package sw2.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sw2.project.common.BaseTimeEntity;

@Entity
@Table(name = "fast_test_records")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FastTestRecord extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String userId;

    @Lob
    @Column(columnDefinition = "JSON")
    private String result;

    @Builder
    public FastTestRecord(String userId, String result) {
        this.userId = userId;
        this.result = result;
    }
}
