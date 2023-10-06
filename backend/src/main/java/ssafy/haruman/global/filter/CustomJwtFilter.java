package ssafy.haruman.global.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.repository.MemberRepository;
import ssafy.haruman.global.error.exception.*;
import ssafy.haruman.global.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final MemberRepository memberRepository;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            throw AuthNoAuthorizationException.EXCEPTION;
        }

        if (!authorization.startsWith("Bearer ")) {
            throw AuthInvalidAuthorizationFormatException.EXCEPTION;
        }

        String token = authorization.split(" ")[1];

        if (JwtUtil.isExpired(token, secretKey)) {
            throw AuthTokenExpiredException.EXCEPTION;
        }

        UUID memberId = JwtUtil.getMemberIdFromJwt(token, secretKey);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> MemberNotFoundException.EXCEPTION);
        if (member.getProfile() == null) {
            throw MemberProfileNotFoundException.EXCEPTION;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = { "/api/oauth", "/api/members" };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
