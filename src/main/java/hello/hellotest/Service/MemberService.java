package hello.hellotest.Service;

import hello.hellotest.Domain.Member;
import hello.hellotest.Repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Member sign
     */
    public long join(Member member) {
        validateDuplicateMember(member); // 同じ名前の場合、加入不可

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("存在する名前です");
                });
    }

    /**
     * すべての会員情報を取り出す
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     *  メンバーIDをもとに探す
     */
    public Optional<Member> findOne(Long member_id) {
        return memberRepository.findById(member_id);
    }
}
