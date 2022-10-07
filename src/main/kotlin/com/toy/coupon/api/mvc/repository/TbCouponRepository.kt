package com.toy.coupon.api.mvc.repository

import com.toy.coupon.api.mvc.model.entity.TbCoupon
import org.springframework.data.jpa.repository.JpaRepository

interface TbCouponRepository: JpaRepository<TbCoupon, String> {
}