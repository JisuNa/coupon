package com.toy.coupon.api.mvc.model.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class TbMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberId: Long,
    var memberName: String,
    val loginAccount: String,
    var password: String,
    @Column(insertable = false, updatable = false)
    val createDtm: LocalDateTime? = null,
    @Column(insertable = false, updatable = false)
    var updateDtm: LocalDateTime? = null
)
