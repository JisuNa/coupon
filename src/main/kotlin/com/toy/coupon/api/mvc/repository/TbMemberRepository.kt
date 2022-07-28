package com.toy.coupon.api.mvc.repository

import com.toy.coupon.api.mvc.model.entity.TbMember
import org.springframework.data.jpa.repository.JpaRepository

interface TbMemberRepository : JpaRepository<TbMember, Long> {
}