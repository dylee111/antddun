package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.entity.Ddun;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private SosoCateService sosoCateService;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private DdunService ddunService;

    @Autowired
    private QnaService qnaService;

    @Autowired
    private JayuBoardService jayuBoardService;

    @Autowired
    private SosoJobService sosoJobService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails principal, PageRequestDTO requestDTO){

        // index sosojob slick
        model.addAttribute("sosoCateList", sosoCateService.getCateList());

        List<JobList> list = jobListService.getList();
        model.addAttribute("jobList", list);

        requestDTO.setSize(5);
        model.addAttribute("getQnaFive", qnaService.getListAll(requestDTO));
        model.addAttribute("getPeongFive", jayuBoardService.getPeongList(requestDTO));
        model.addAttribute("getJayuFive", jayuBoardService.getList(requestDTO));
//        model.addAttribute("getSosoFive", sosoJobService.getList(requestDTO)); //??????????????? ?????????

        if (principal != null) {

            List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

            model.addAttribute("unreadMsg", messageService.unreadMsg(principal.getMember().getMno()));
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            model.addAttribute("member", principal.getMember());

            if (wishLists.size() != 0) {
                model.addAttribute("wishListIndex", wishLists.get(0));
            }

        }
        return "index";
    }

    @GetMapping("/member/mypage/info")
    public String userinfo(Model model, @AuthenticationPrincipal PrincipalDetails principal, MemberDTO memberDTO) {
        List<MemberWishList> wishLists = wishListService.getListByMno(principal.getMember().getMno());

        model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
        if (wishLists.size() != 0) {
            model.addAttribute("wishListIndex", wishLists.get(0));
        }

        model.addAttribute("member", principal.getMember());
        model.addAttribute("jobList", jobListService.getList());
        return "member/mypage/info";
    }

    @PostMapping("/member/mypage/info")
    public void userInfoPost() {

    }

    @GetMapping("/member/mypage/wallet")
    public String userWallet(Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        Long mno = principal.getMember().getMno();

        log.info("WALLET"+mno);
        List<MemberWishList> wishLists = wishListService.getListByMno(mno);
        List<Ddun> ddunList = ddunService.getListBymno(mno);
        Long totalDdun = ddunService.totalAmountByMno(mno);

        if (principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
            model.addAttribute("ddunList", ddunList);
            model.addAttribute("totalDdun", totalDdun);
            if (wishLists.size() != 0) {
                model.addAttribute("wishList", wishLists);
                model.addAttribute("wishListIndex", wishLists.get(0));
            }
        }
        return "member/mypage/wallet";
    }

    /* ??????????????? ?????? */
    @ResponseBody
    @PostMapping("/member/mypage/wishlist/save")
    public int saveWishList(MemberWishListDTO wishListDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // ?????? ??? ??????????????? ?????? ?????????
        int result = wishListService.wishListCnt(principalDetails.getMember().getMno());
        log.info("MNOCNT>>>" + result);
        // ??????????????? 3????????? ?????? ??????.
        if (result < 3) {
            wishListService.register(wishListDTO, principalDetails.getMember());
        }
        return result;
    }

    /* ??????????????? ?????? */
    @DeleteMapping("/member/mypage/wishlist/delete/{wno}")
    public ResponseEntity<String> removeWishList(@PathVariable("wno") Long wno) {
        wishListService.remove(wno);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

    /* ??????????????? ?????? */
    @ResponseBody
    @PutMapping("/member/mypage/wishlist/modify/{wno}")
    public ResponseEntity<String> modifyWishList(@RequestBody MemberWishListDTO memberWishListDTO,
                                                 @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                 HttpServletRequest request) {

        log.info("DDAY>>>"+request.getParameter("dDay"));
        wishListService.modify(memberWishListDTO, principalDetails.getMember());

        return new ResponseEntity<>("modify", HttpStatus.OK);
    }

    /* ?????? ?????? ?????? */
    // put??? ????????? ????????? ???????????? ????????? ?????? ????????? patch ?????? post??? ????????? ???.
    @ResponseBody
    @PostMapping(value = "/member/mypage/info/modify/{mno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDTO> modifyMember(@RequestBody MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // ?????? ???????????? ?????? ?????? ?????? (Null ??????)
        memberDTO.setFirstName(principalDetails.getMember().getFirstName());
        memberDTO.setUsername(principalDetails.getUsername());
        memberDTO.setLastName(principalDetails.getMember().getLastName());
        memberDTO.setRole(principalDetails.getMember().getRole().toString());
        memberDTO.setCreateDate(principalDetails.getMember().getCreateDate());

        memberService.modifyMember(memberDTO);

        // JSON ???????????? ?????? Http Body ?????? ?????? ??????, String?????? ?????? ???, parseError ??????
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("/member/mypage")
    public void wishList(MemberWishListDTO wishListDTO,
                         @AuthenticationPrincipal PrincipalDetails principalDetail) {

    }

}