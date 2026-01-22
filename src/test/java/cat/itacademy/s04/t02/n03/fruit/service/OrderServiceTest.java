package cat.itacademy.s04.t02.n03.fruit.service;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.exception.OrderNotFoundException;
import cat.itacademy.s04.t02.n03.fruit.mapper.OrderMapper;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock private OrderRepository orderRepository;
    @Mock private OrderMapper orderMapper;
    @InjectMocks private OrderService orderService;

    private Order order;
    private OrderRequestDTO orderRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        OrderItemDTO itemDTO = new OrderItemDTO("Apple", 5);
        orderRequestDTO = new OrderRequestDTO("John", LocalDate.now().plusDays(1), List.of(itemDTO));

        OrderItem item = new OrderItem("Apple", 5);
        order = new Order("1", "John", LocalDate.now().plusDays(1), List.of(item));

        when(orderMapper.toEntity(orderRequestDTO)).thenReturn(order);
    }

    @Test
    void createOrder_success() {
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(orderRequestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getClientName()).isEqualTo("John");
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void getOrderById_success() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById("1");

        assertThat(result).isEqualTo(order);
        verify(orderRepository).findById("1");
    }

    @Test
    void getOrderById_notFound() {
        when(orderRepository.findById("2")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById("2"));
        verify(orderRepository).findById("2");
    }

    @Test
    void deleteOrder_success() {
        when(orderRepository.existsById("1")).thenReturn(true);
        doNothing().when(orderRepository).deleteById("1");

        orderService.deleteOrder("1");

        verify(orderRepository).deleteById("1");
    }

    @Test
    void deleteOrder_notFound() {
        when(orderRepository.existsById("2")).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder("2"));
    }
}
