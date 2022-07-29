package com.toy.coupon.api.mvc.model.converter

import com.toy.coupon.api.mvc.model.entity.TbMember
import com.toy.coupon.api.mvc.model.vo.MemberVo

fun TbMember.toMemberVo(): MemberVo {
    return MemberVo(
        memberNid = memberNid,
        memberName = memberName,
        loginAccount = loginAccount
    )
}