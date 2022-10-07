package com.toy.coupon.api.mvc.model.entity

import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class TbEvent(
    val eventName: String,
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val eventId: Long? = null

    val eventDate: LocalDate = LocalDate.now()

    @OneToMany(fetch = LAZY, mappedBy = "tbEvent", cascade = [CascadeType.ALL])
    var coupons: MutableList<TbCoupon> = mutableListOf()

    fun addCoupon(coupon: TbCoupon) {
        coupon.tbEvent = this
        coupons.add(coupon)
    }
}
