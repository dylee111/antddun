package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.MemberService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JobListService jobListService;

    @Autowired
    private WishListService wishListService;

    @GetMapping("")
    public String main (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {

            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            model.addAttribute("member", principal.getMember());

            if(wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }

        }
        return "index";
    }

    @GetMapping("/member/mypage/info")
    public String userinfo (Model model, @AuthenticationPrincipal PrincipalDetails principal, MemberDTO memberDTO) {
        model.addAttribute("member", principal.getMember());
        return "member/mypage/info";
    }

    @GetMapping("/member/mypage/wallet")
    public String userwallet (Model model, @AuthenticationPrincipal PrincipalDetails principal, MemberWishListDTO memberWishListDTO) {
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

//        Long earn = principal.getMember().getSalary();
//        log.info("EARN >> " + earn);
//        Double monthly = (earn * 10000 / 12) - (earn * 10000 * 0.009);
//        log.info("MONTHLY" + monthly);
//        log.info("DTODTO11 >>> " + wishLists.get(0).getPrice());
//        log.info("DTODTO22 >>> " + wishLists.get(0).getRate());
//        double day = 0;
//        day = Math.ceil(wishLists.get(0).getPrice() / (monthly * (wishLists.get(0).getRate() / 100)));
//        log.info("DAY>>"+day);
//        for (int i = 0; i < wishLists.size(); i++) {
//            day = (int) Math.ceil(wishLists.get(i).getPrice() / (monthly * (wishLists.get(i).getRate() / 100)));
//        }

        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
            if (wishLists.size() != 0) {
                model.addAttribute("wishList", wishLists);
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }
        return "member/mypage/wallet";
    }

    @ResponseBody
    @PostMapping("/member/mypage/save")
    public int wishListSave(MemberWishListDTO wishListDTO, @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest request) {

//        String[] nameArr = request.getParameterValues("wishList");
//        String[] priceArr = request.getParameterValues("price");
//        String[] rateArr = request.getParameterValues("rate");
//            log.info("NAME ARR>>> " + nameArr[0] +"/"+nameArr[1] +"/"+ nameArr[2]);
//            log.info("PRICE ARR>>> " + priceArr[0] +"/"+ priceArr[1] + "/" +priceArr[2]);
//            log.info("RATE ARR>>> " + rateArr[0] +"/"+ rateArr[1] + "/" +rateArr[2]);
        int result = wishListService.wishListCnt(principalDetails.getMember().getMno());
        log.info("MNOCNT>>>" + result);
        // 위시리스트 3개까지 작성 가능.
        if (result < 3) {
            wishListService.register(wishListDTO, principalDetails.getMember());
        }
        return result;
    }


    @PostMapping("/member/mypage")
    public void wishList(MemberWishListDTO wishListDTO,
                               @AuthenticationPrincipal PrincipalDetails principalDetail) {

    }

    @GetMapping("/member/messenger")
    public String messenger (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
        }
        return "member/messenger";
    }
}