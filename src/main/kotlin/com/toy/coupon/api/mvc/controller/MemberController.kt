package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.model.vo.MemberVo
import com.toy.coupon.api.mvc.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping
    fun getMembers(): List<MemberVo> {
        return memberService.getMembers()
    }

    @GetMapping("/{memberNid}")
    fun getMember(@PathVariable memberNid: Long): MemberVo? {
        return memberService.getMember(memberNid)
    }
}