package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.model.entity.TbMember
import com.toy.coupon.api.mvc.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping
    fun getMembers(): List<TbMember> {
        return memberService.getMembers()
    }
}