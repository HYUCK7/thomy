package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers(){
        //WHEN
        List<Member> members = memberRepository.findAll();
        //THEN
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberById(){
        //when
        Member member = memberRepository.findById(2L).get();
        //then
        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName(){
        //when
        Member member = memberRepository.findByName("C").get();
        //then
        assertThat(member.getId()).isEqualTo(3);
    }

    //p.151
    @Test
    void saveMember(){
        Member member = new Member(1L, "A");
    }
}