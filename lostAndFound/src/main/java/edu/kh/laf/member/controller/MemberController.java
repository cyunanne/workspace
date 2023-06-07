package edu.kh.laf.member.controller;

import edu.kh.laf.member.model.dto.Member;
import edu.kh.laf.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/")
    public String home(String memberId, String memberPw, Model model) {
        model.addAttribute("message", "서버에서 보낸 메시지~!");
        model.addAttribute("message2", service.getMember(1L));
        return "main";
    }
}
