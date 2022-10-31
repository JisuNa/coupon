package com.toy.coupon.api.mvc.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [CustomException::class])
    fun handlingCustomException(ex: CustomException): ResponseEntity<ErrorResponseVo> {
        return ResponseEntity(
            ErrorResponseVo(
                errorCode = ex.errorCode.name,
                message = ex.errorCode.message
            ),
            ex.errorCode.status
        )
    }
}

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON(FORBIDDEN, "쿠폰이 모두 소진되었습니다."),
    NOT_ALLOW_PROVIDE_COUPON_ALREADY_PROVIDE_USER(FORBIDDEN, "이미 쿠폰을 받았습니다.")
    ;

    @Suppress("NOTHING_TO_INLINE")
    inline fun of(): CustomException {
        return CustomException(this)
    }
}

data class ErrorResponseVo(
    val errorCode: String,
    val message: String?
)
