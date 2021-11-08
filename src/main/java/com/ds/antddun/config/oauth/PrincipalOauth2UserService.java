package com.ds.antddun.config.oauth;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.config.oauth.provider.FacebookUserInfo;
import com.ds.antddun.config.oauth.provider.GoogleUserInfo;
import com.ds.antddun.config.oauth.provider.OAuth2UserInfo;
import com.ds.antddun.entity.AntMemberRoleSet;
import com.ds.antddun.entity.Member;
import com.ds.antddun.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

//여기서 후처리가됨
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration:" + userRequest.getClientRegistration()); //registrationId로 어떤 OAuth로 로그인 했는지 확인 가능.
        System.out.println("getAccessToken:" + userRequest.getAccessToken());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttribute:" + oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("우리는 구글과 페이스북만 지원해요");
        }

        //회원가입 시킴
        Long mno = oAuth2UserInfo.getProviderId(); // 109742856182916427686
        String username = oAuth2UserInfo.getEmail();
        String provider = oAuth2UserInfo.getProvider() + "_" + username; // google_112343454352
        String password = bCryptPasswordEncoder.encode(provider); //social_uuid를 비밀번호로 넣어둠 -> 고유
        String name = oAuth2UserInfo.getName();
        String firstname = oAuth2UserInfo.getFirstName();
        String lastname = oAuth2UserInfo.getLastName();
        AntMemberRoleSet role = AntMemberRoleSet.USER;

        Member memberEntity = memberRepository.findByUsername(username);
        if (memberEntity == null) {
            System.out.println("최초 소셜 로그인");
            memberEntity = Member.builder()
                    .mno(mno)
                    .username(username)
                    .password(password)
                    .firstName(firstname)
                    .lastName(lastname)
                    .role(role)
                    .fromSocial(true)
                    .build();
            memberRepository.save(memberEntity);
        } else {
            System.out.println("소셜 로그인을 한 적이 있습니다.");
        }
        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
    }
}
