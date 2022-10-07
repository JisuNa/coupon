package com.toy.coupon.api.mvc.repository.impl

import com.toy.coupon.api.mvc.repository.AbstractRepository
import com.toy.coupon.api.mvc.repository.TbMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class TbMemberRepositoryImplTest : AbstractRepository() {

    @Autowired
    lateinit var tbMemberRepository: TbMemberRepository

    @Test
    @DisplayName("회원 조회 성공")
    fun findMember() {
        // given
        val memberId = 1L

        val result = tbMemberRepository.findMember(1L)

        assertThat(result?.memberId).isEqualTo(memberId)
    }
}
