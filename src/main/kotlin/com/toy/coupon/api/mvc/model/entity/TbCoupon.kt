package com.toy.coupon.api.mvc.model.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class TbCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponId: Long? = null

    var userId: Long? = null

    @Column(updatable = false)
    val issueDate: LocalDate = LocalDate.now()

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "eventId")
    var tbEvent: TbEvent? = null
}
