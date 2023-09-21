package ssafy.haruman.domain.member.controller;


import org.springframework.core.convert.converter.Converter;
import ssafy.haruman.domain.member.entity.OAuthServerType;

public class OAuthProviderConverter implements Converter<String, OAuthServerType> {

    @Override
    public OAuthServerType convert(String source) {
        return OAuthServerType.fromName(source);
    }
}
