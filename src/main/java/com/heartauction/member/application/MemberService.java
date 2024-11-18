package com.heartauction.member.application;

import com.heartauction.auth.AuthService;
import com.heartauction.auth.LoginMember;
import com.heartauction.member.domain.Member;
import com.heartauction.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService implements AuthService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public LoginMember login(Long id) {
        Member member = findById(id);

        return new LoginMember(member.getId(), member.getName());
    }

    private Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member id"));
    }
}
