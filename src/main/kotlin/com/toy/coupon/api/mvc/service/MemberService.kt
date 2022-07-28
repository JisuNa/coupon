package com.toy.coupon.api.mvc.service

import com.toy.coupon.api.mvc.model.entity.TbMember

interface MemberService {
    fun getMembers(): List<TbMember>
}