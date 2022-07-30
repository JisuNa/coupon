package com.toy.coupon.api.mvc.repository.impl

import com.toy.coupon.api.mvc.model.entity.QTbMember.tbMember
import com.toy.coupon.api.mvc.model.entity.TbMember
import com.toy.coupon.api.mvc.repository.DslTbMemberRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class TbMemberRepositoryImpl : QuerydslRepositorySupport(TbMember::class.java), DslTbMemberRepository {
    override fun findMember(memberNid: Long): TbMember? {
        return from(tbMember)
            .where(
                tbMember.memberNid.eq(memberNid)
            )
            .fetchOne()
    }
}
