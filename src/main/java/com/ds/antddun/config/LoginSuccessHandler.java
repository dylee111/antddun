package com.ds.antddun.config;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.config.oauth.PrincipalOauth2UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Log4j2
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public LoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

/*        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("redirectURI");

            if (redirectUrl != null) {
                session.removeAttribute("redirectURI");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }*/



        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        boolean fromSocial = principalDetails.getMember().isFromSocial();
        log.info("Need Modify Member?" + fromSocial); //true

        List<String> roleList = new ArrayList<>();

        principalDetails.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
            @Override
            public void accept(GrantedAuthority grantedAuthority) {
                roleList.add(grantedAuthority.getAuthority());
                System.out.println(grantedAuthority.getAuthority()); //user
            }
        });
        log.info("getAuthorities: " + roleList);

        String sendUrl = "";
        if (roleList.contains("USER") && fromSocial) sendUrl = "/";
        if (roleList.contains("ADMIN") && fromSocial) sendUrl = "/admin";
        if (roleList.contains("SOCIAL") && fromSocial ) sendUrl = "/antddun/member/socialJoin";
        log.info("sendUrl: "+sendUrl);
        redirectStrategy.sendRedirect(request, response, sendUrl);
    }

}
