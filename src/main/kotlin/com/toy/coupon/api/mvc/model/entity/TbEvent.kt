package com.toy.coupon.api.mvc.model.entity

import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
class TbEvent(
    val eventName: String,
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val eventId: Long? = null

    @OneToMany(fetch = LAZY, mappedBy = "tbEvent", cascade = [CascadeType.ALL])
    var coupons: MutableList<TbCoupon> = mutableListOf()

    fun addCoupon(coupon: TbCoupon) {
        coupon.tbEvent = this
        coupons.add(coupon)
    }
}
