package com.heartauction.member.application;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.heartauction.member.domain.Member;
import com.heartauction.member.domain.MemberRepository;
import com.heartauction.support.ServiceTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberServiceTest extends ServiceTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원 ID가 존재하면 로그인 된다.")
    @Test
    void loginTrue() {
        Member member = memberRepository.save(new Member("회원1"));

        assertThatCode(() -> memberService.login(member.getId()))
                .doesNotThrowAnyException();
    }

    @DisplayName("회원 ID가 존재하지 않으면 로그인 되지 않는다.")
    @Test
    void validateIdFalse() {
        assertThatThrownBy(() -> memberService.login(1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
