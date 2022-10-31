package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.converter.toMemberVo
import com.toy.coupon.api.mvc.model.vo.MemberVo
import com.toy.coupon.api.mvc.repository.TbMemberRepository
import com.toy.coupon.api.mvc.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
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
