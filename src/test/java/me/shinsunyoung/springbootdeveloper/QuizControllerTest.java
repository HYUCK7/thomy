package me.shinsunyoung.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    // class와 객체 간의 변환 처리.(객체 직렬화)

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(get(url).param("code", "1")
        );
        //then
        result.andExpect(status().isCreated())
                .andExpect(content().string("created!"));
    }

    @DisplayName("quiz(): GET /quiz?code=2이면 응답코드는 400, 응답 본문은 Bad Request!를 리턴")
    @Test
    public void getQuiz2() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(get(url).param("code", "2"));
        result.andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz(): POST /quiz?code=1이면, 응답 코드는 403, 응답 본문은 Forbidden!을 리턴")
    @Test
    public void postQuiz1() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Code(1)))
        );
        //then
        result.andExpect(status().isForbidden()).andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz?code=13 이면 응답 코드는 200, 응답 본문은 OK!를 리턴 한다.")
    @Test
    public void postQuiz13() throws Exception {
        final String url = "/quiz";
        final ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new Code(13))));
        result.andExpect(status().isOk()).andExpect(content().string("OK!"));
    }
}