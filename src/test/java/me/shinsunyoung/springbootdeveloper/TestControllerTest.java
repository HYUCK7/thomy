package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 어플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동구성 (배포하지 않아도 MVC 환경 생성)
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        //given - 테스트 실행 준비 단계
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "A"));
        //when - 테스트 진행 단계
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
        // - perform 메소드는 요청을 전송하는 역할. 결과로 ResultActions를 받으며, ResultAction 객체는 반환값을
        // - 검증하고 확인하는 andExcept 메서드를 제공한다.
        // - accept 메서드 요청을 보낼 때, 무슨 타입으로 응답 받을지 결정
        //then - 테스트 결과 검증

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath(
                        "$[0].name").value(savedMember.getName()));
        //andExpect 는 응답을 검증.
        //jsonPath는 JSON의 응답 값을 가져오는 역할.
    }

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }
}