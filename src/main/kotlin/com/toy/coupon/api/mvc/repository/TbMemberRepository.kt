package com.toy.coupon.api.mvc.repository

import com.toy.coupon.api.mvc.model.entity.TbMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TbMemberRepository : JpaRepository<TbMember, Long>, DslTbMemberRepository {
}

interface DslTbMemberRepository {
    fun findMember(memberNid: Long): TbMember?
}