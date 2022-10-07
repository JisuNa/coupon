package com.toy.coupon.model.entity

import com.toy.coupon.api.mvc.model.entity.TbMember
import java.time.LocalDateTime

fun mockTbMember(
    memberId: Long = 1L,
    memberName: String = "김길동",
    loginAccount: String = "test",
    password: String = "1234",
    createDtm: LocalDateTime = LocalDateTime.now(),
    updateDtm: LocalDateTime = LocalDateTime.now()
): TbMember {
    return TbMember(
        memberId = memberId,
        memberName = memberName,
        loginAccount = loginAccount,
        password = password,
        createDtm = createDtm,
        updateDtm = updateDtm
    )
}
