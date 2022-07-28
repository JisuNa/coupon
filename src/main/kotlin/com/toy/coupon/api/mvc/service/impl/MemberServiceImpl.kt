package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.entity.TbMember
import com.toy.coupon.api.mvc.repository.TbMemberRepository
import com.toy.coupon.api.mvc.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val tbMemberRepository: TbMemberRepository
) : MemberService {
    override fun getMembers(): List<TbMember> {
        return tbMemberRepository.findAll()
    }
}