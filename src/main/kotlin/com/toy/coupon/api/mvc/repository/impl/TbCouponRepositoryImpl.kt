package com.toy.coupon.api.mvc.repository.impl

import com.toy.coupon.api.mvc.model.entity.QTbCoupon.tbCoupon
import com.toy.coupon.api.mvc.model.entity.TbCoupon
import com.toy.coupon.api.mvc.repository.DslTbCouponRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class TbCouponRepositoryImpl : QuerydslRepositorySupport(TbCoupon::class.java), DslTbCouponRepository {
    override fun findAllocableCoupon(eventId: Long): TbCoupon? {
        return from(tbCoupon)
            .where(
                tbCoupon.tbEvent.eventId.eq(eventId),
                tbCoupon.userId.isNull
            ).limit(1).fetchOne()
    }
}