package ssafy.haruman.global.entity;

public enum RedisKey {
    ETC_KEY("기타"),
    MEAL_KEY("식사"),
    CAFE_KEY("카페"),
    SNACK_KEY("군것질"),
    FASHION_KEY("패션/뷰티"),
    LIFE_KEY("생활"),
    MEDICAL_KEY("건강"),
    PLEASURE_KEY("유흥"),
    TRANSPORT_KEY("교통"),
    LEISURE_KEY("여가");

    private final String key;

    RedisKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
