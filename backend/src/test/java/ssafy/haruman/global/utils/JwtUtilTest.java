package ssafy.haruman.global.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static ssafy.haruman.global.utils.JwtUtil.createJwt;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-secret.yml")
class JwtUtilTest {

    @Value("${jwt.secret}")
    private String secretKey;

    @Test
    @DisplayName("API 테스트를 위한 superuser 토큰 얻기")
    void getJwtToken() {

        /* superuser(하루만)의 UUID (초기데이터) */
        UUID uuid = UUID.fromString("24ce4681-c966-419f-b739-811ebb6f1872");

        /* 9월 26일 기준, 10월 7일까지 */
        Long expiredMs = 1000000000L;

        String token = createJwt(uuid, secretKey, expiredMs);
        System.out.println(token);
    }

}