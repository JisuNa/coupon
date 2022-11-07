package com.toy.coupon.api.mvc.model.entity

import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class TbCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponId: Long? = null

    var userId: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "eventId")
    var tbEvent: TbEvent? = null
}
