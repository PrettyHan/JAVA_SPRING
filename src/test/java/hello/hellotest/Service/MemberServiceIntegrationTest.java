package hello.hellotest.Service;

import hello.hellotest.Domain.Member;
import hello.hellotest.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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