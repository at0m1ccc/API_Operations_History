package ru.netology.kTatarinov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.netology.kTatarinov.OperationHistoryApiApplicationTest;
import ru.netology.kTatarinov.domain.Customer;
import ru.netology.kTatarinov.domain.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objMapper;

    @Test
    public void customerControllerWorksTest() throws Exception {
        List<CustomerDTO> customersDto = new ArrayList<>(List.of(
                new CustomerDTO(1, "Spring"), new CustomerDTO(2, "Boot")
        ));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Spring", "Boot")));


        mvc.perform(MockMvcRequestBuilders.get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Spring")));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("Boot")));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/23"))
                .andExpect(status().isNotFound());

        Customer current_customer = new Customer(19, "kostya", "143749");
        mvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType("application/json")
                        .content(objMapper.writeValueAsString(current_customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(current_customer.getId())))
                .andExpect(jsonPath("$.name", equalTo(current_customer.getName())));

        customersDto.add(new CustomerDTO(current_customer.getId(), current_customer.getName()));
        mvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, current_customer.getId())))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Spring", "Boot", current_customer.getName())));
    }
}