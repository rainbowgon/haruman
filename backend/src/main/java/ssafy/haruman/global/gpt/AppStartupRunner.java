package ssafy.haruman.global.gpt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import ssafy.haruman.global.entity.RedisKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final RedisTemplate<String, Float> floatRedisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, Float> valueOperations = floatRedisTemplate.opsForValue();

        for (RedisKey redisKey : RedisKey.values()) {
            valueOperations.set(redisKey.getKey(), 0.0f);
        }
        valueOperations.set("cnt", 0.0f);
    }
}