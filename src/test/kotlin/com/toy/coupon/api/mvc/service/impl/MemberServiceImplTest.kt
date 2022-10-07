package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.repository.TbMemberRepository
import com.toy.coupon.model.entity.mockTbMember
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class MemberServiceImplTest {

    private val tbMemberRepository = mock<TbMemberRepository>()

    private val memberService = MemberServiceImpl(tbMemberRepository)

    @Test
    @DisplayName("회원 목록 조회 성공")
    fun getMembers() {
        // given
        given(tbMemberRepository.findAll()).willReturn(listOf(mockTbMember()))

        // when
        memberService.getMembers()

        // then
        verify(tbMemberRepository).findAll()
    }

    @Test
    @DisplayName("회원Nid로 조회 성공")
    fun getMember() {
        val memberId = 1L

        // given
        given(tbMemberRepository.findMember(any())).willReturn(mockTbMember())

        // when
        val result = memberService.getMember(memberId)

        // then
        assertThat(result?.memberId).isEqualTo(memberId)
    }
}
