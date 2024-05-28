package eu.chrost.jacksonerror;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class JacksonApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void upperCamelCaseForSingleObject() throws Exception {
        mockMvc.perform(get("/on"))
                .andExpect(jsonPath("$.IdCycle", is(1)));
    }

    @Test
    void upperCamelCaseForList() throws Exception {
        mockMvc.perform(get("/on/list"))
                .andExpect(jsonPath("$[0].IdCycle", is(1)));
    }

    @Test
    void defaultForSingleObject() throws Exception {
        mockMvc.perform(get("/off"))
                .andExpect(jsonPath("$.idCycle", is(1)));
    }

    @Test
    void defaultForList() throws Exception {
        mockMvc.perform(get("/off/list"))
                .andExpect(jsonPath("$[0].idCycle", is(1)));
    }

}
