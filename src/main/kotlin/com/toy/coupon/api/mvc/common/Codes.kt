package com.toy.coupon.api.mvc.common

interface CommonCode {
    val code: String
    val codeName: String
}

class Codes {
    enum class EventStatus(
        override val code: String,
        override val codeName: String
    ) : CommonCode {
        READY("READY", "준비중"),
        IN_PROGRESS("IN_PROGRESS", "진행중"),
        FINISH("FINISH", "종료")
    }
}
