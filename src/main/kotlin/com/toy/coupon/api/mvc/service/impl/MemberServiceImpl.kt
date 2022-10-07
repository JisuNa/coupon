package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.converter.toMemberVo
import com.toy.coupon.api.mvc.model.vo.MemberVo
import com.toy.coupon.api.mvc.repository.TbMemberRepository
import com.toy.coupon.api.mvc.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val tbMemberRepository: TbMemberRepository
) : MemberService {
    override fun getMembers(): List<MemberVo> {
        return tbMemberRepository.findAll().map { it.toMemberVo() }
    }

    override fun getMember(memberId: Long): MemberVo? {
        return tbMemberRepository.findMember(memberId)?.toMemberVo()
    }
}
