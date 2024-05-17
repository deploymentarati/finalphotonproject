package net.codejava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserApiController.class)
public class UserApiControllerTests {

    private static final String END_POINT_PATH = "/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        User newUser = new User();
        newUser.setName("");
        newUser.setProject("");
        newUser.setBoundedContext("");
        newUser.setNamespace("");
        newUser.setStatus("");
        newUser.setTags(Arrays.asList());

        String requestBody = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testAddShouldReturn201Created() throws Exception {
        User newUser = new User();
        newUser.setName("account_tokenization_service");
        newUser.setProject("GPPAPG");
        newUser.setBoundedContext("account_management");
        newUser.setNamespace("account");
        newUser.setStatus("PRODUCTION");
        newUser.setTags(Arrays.asList("GBP1.0", "GBPAPG", "account"));

        User persistedUser = new User();
        persistedUser.setId(1L);
        persistedUser.setName(newUser.getName());
        persistedUser.setProject(newUser.getProject());
        persistedUser.setBoundedContext(newUser.getBoundedContext());
        persistedUser.setNamespace(newUser.getNamespace());
        persistedUser.setStatus(newUser.getStatus());
        persistedUser.setTags(newUser.getTags());

        Mockito.when(service.add(newUser)).thenReturn(persistedUser);

        String requestBody = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(service, times(1)).add(newUser);
    }
}
