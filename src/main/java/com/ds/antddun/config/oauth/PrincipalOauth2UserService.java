package com.ds.antddun.config.oauth;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.config.oauth.provider.FacebookUserInfo;
import com.ds.antddun.config.oauth.provider.GoogleUserInfo;
import com.ds.antddun.config.oauth.provider.NaverUserInfo;
import com.ds.antddun.config.oauth.provider.OAuth2UserInfo;
import com.ds.antddun.entity.AntMemberRoleSet;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

//여기서 후처리가됨
@Service
@Log4j2
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistration:" + userRequest.getClientRegistration()); //registrationId로 어떤 OAuth로 로그인 했는지 확인 가능.
        log.info("getAccessToken:" + userRequest.getAccessToken());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttribute:" + oAuth2User.getAttributes());

// =================== 각 소셜 로그인에 따른 aoAuth2UserInfo ===================
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            log.info("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            log.info("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            log.info("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }else {
            log.info("구글, 네이버, 페이스북만 로그인 가능!");
        }

// =================== 강제 회원가입 ===================
        //email(username)
        String username = oAuth2UserInfo.getEmail();

        //비밀번호
        String providerId = oAuth2UserInfo.getProviderId();
        String uuid = oAuth2UserInfo.getProvider() + "_" + providerId; // google_112343454352
        String password = bCryptPasswordEncoder.encode(uuid); // -> 고유


        //이름
        String firstname = oAuth2UserInfo.getFirstName();

        //성
        String lastname = oAuth2UserInfo.getLastName();

        //권한
        AntMemberRoleSet role = AntMemberRoleSet.USER;

        //폰번호(naver만 가능)
        String mobile = oAuth2UserInfo.getMobile();

        Member memberEntity = memberRepository.findByUsername(username);
        if (memberEntity == null) {
            log.info("최초 소셜 로그인");
            memberEntity = Member.builder()
                    .username(username)
                    .password(password)
                    .firstName(firstname)
                    .lastName(lastname)
                    .role(role)
                    .fromSocial(true)
                    .phoneNum(mobile)
                    .build();
            memberRepository.save(memberEntity);
        } else {
            log.info("소셜 로그인을 한 적이 있습니다.");
        }
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
    }
}
