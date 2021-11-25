package com.ds.antddun.config;

import com.ds.antddun.config.auth.PrincipalDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

    public LoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        boolean fromSocial = principalDetails.getMember().isFromSocial();
        List<String> roleList = new ArrayList<>();

        principalDetails.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
            @Override
            public void accept(GrantedAuthority grantedAuthority) {
                roleList.add(grantedAuthority.getAuthority());
                System.out.println(grantedAuthority.getAuthority()); //user
            }
        });
        log.info("getAuthorities: " + roleList);

        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("redirectURI");
            if (roleList.contains("SOCIAL") && fromSocial ) redirectUrl = "/member/socialJoin";

                    if (redirectUrl != null) {
                        session.removeAttribute("redirectURI");

                    } else {
                        super.onAuthenticationSuccess(request, response, authentication);
                    }

            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        }


    }

}
