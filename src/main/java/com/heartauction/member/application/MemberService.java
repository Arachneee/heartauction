package com.heartauction.member.application;

import com.heartauction.member.application.response.MemberResponse;
import com.heartauction.member.domain.Member;
import com.heartauction.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member id"));

        return MemberResponse.from(member);
    }

    @Transactional(readOnly = true)
    public void validateId(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid member id");
        }
    }
}
