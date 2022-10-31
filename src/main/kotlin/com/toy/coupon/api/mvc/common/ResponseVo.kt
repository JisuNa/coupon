package com.toy.coupon.api.mvc.common

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResponseVo<T>(
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val data: T?,
    val resultMessage: String? = null,
    val resultCode: String? = null,
    val ts: Long = System.currentTimeMillis()
)