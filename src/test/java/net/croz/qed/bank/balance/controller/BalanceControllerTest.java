package net.croz.qed.bank.balance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.croz.qed.bank.balance.QedBankBalanceApplication;
import net.croz.qed.bank.balance.model.AddFund;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QedBankBalanceApplication.class)
@AutoConfigureMockMvc
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetBalancesByOib_thenStatus200() throws Exception {
        mockMvc.perform(get("/balance/oib/11111111111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].oib", is("11111111111")));
    }

    @Test
    public void whenGetBalancesByOib_thenStatus404() throws Exception {
        mockMvc.perform(get("/balance/oib/00000000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenGetBalanceByIban_thenStatus200() throws Exception {
        mockMvc.perform(get("/balance/iban/HR8023400092673653924")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.oib", is("11111111111")));
    }

    @Test
    public void whenGetBalanceByIban_thenStatus404() throws Exception {
        mockMvc.perform(get("/balance/iban/HR0099999990000000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenModifyBalance_thenStatus200() throws Exception {
        mockMvc.perform(post("/balance/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(new AddFund("HR8023400092673653924", BigDecimal.ONE))))
                .andExpect(status().isOk());
    }

    @Test
    public void whenModifyBalance_thenStatus404() throws Exception {
        mockMvc.perform(post("/balance/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(new AddFund("HR0099999990000000000", BigDecimal.ONE))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    private String asJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Fail to serialize json payload", e);
        }
    }
}
