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
import ssafy.haruman.global.error.exception.MemberNotFoundException;
import ssafy.haruman.global.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final MemberRepository memberRepository;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authentication : {}", authorization); // TODO log 삭제

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization이 없습니다."); // TODO log 삭제
            filterChain.doFilter(request, response);
            throw MemberNotFoundException.EXCEPTION;
        }

        String token = authorization.split(" ")[1];

        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다."); // TODO log 삭제
            filterChain.doFilter(request, response);
            return;
        }

        Long memberId = JwtUtil.getMemberIdFromJwt(token, secretKey);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = { "/api/oauth/" };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
