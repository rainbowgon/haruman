package ssafy.haruman.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.domain.member.service.OAuthService;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
@Slf4j
public class OAuthController {

    private final OAuthService oauthService;

    @GetMapping("/{oauthServerType}")
    public ResponseEntity<?> redirectAuthCodeRequestUrl(@PathVariable OAuthServerType oauthServerType) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
    }

    @GetMapping("/{oauthServerType}/login")
    ResponseEntity<String> login(
            @PathVariable OAuthServerType oauthServerType,
            @RequestParam("code") String code) {
        String jwtToken = oauthService.oauthLogin(oauthServerType, code);
        return ResponseEntity.ok(jwtToken);
    }
}
