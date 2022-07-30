package com.toy.coupon.api.mvc.service

import com.toy.coupon.api.mvc.model.vo.MemberVo

interface MemberService {
    fun getMembers(): List<MemberVo>
    fun getMember(memberNid: Long): MemberVo?
}
