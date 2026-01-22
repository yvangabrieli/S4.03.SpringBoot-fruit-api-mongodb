package cat.itacademy.s04.t02.n03.fruit.controller;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.mapper.OrderMapper;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private OrderService orderService;
    @MockBean private OrderMapper orderMapper;

    private OrderRequestDTO orderRequestDTO;
    private Order order;

    @BeforeEach
    void setUp() {
        OrderItemDTO itemDTO = new OrderItemDTO("Apple", 5);
        orderRequestDTO = new OrderRequestDTO("John", LocalDate.now().plusDays(1), List.of(itemDTO));

        OrderItem item = new OrderItem("Apple", 5);
        order = new Order("1", "John", LocalDate.now().plusDays(1), List.of(item));
    }

    @Test
    void createOrder_success() throws Exception {
        when(orderMapper.toEntity(orderRequestDTO)).thenReturn(order);
        when(orderService.createOrder(orderRequestDTO)).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void getOrderById_success() throws Exception {
        when(orderService.getOrderById("1")).thenReturn(order);
        when(orderMapper.toDTO(order)).thenReturn(
                new OrderResponseDTO(
                        "1",
                        "John",
                        order.getDeliveryDate(),
                        List.of(new OrderItemDTO("Apple", 5))));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName").value("John"));
    }

    @Test
    void deleteOrder_success() throws Exception {
        doNothing().when(orderService).deleteOrder("1");

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());
    }
}
