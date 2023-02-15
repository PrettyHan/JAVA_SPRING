package hello.hellotest.Service;

import hello.hellotest.Domain.Member;
import hello.hellotest.Repository.MemberRepository;
import hello.hellotest.Repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 会員加入() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long memberId = memberService.join(member);

        //then
        Member member1 = memberService.findOne(memberId).get();
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void validateDuplicateException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertThat(illegalStateException.getMessage()).isEqualTo("存在する名前です");
        //then
    }
    @Test
    void すべてのメンバー探す() {

    }

    @Test
    void メンバーをネームを基準で探す() {

    }
}