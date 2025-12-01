package sw2.project.global;

public enum RiskLevel {
        SAFE("안전"),
        CAUTION("주의"),
        WARNING("경고"),
        DANGER("위험");
        private final String description;
        RiskLevel(String description) {this.description = description;}
}
