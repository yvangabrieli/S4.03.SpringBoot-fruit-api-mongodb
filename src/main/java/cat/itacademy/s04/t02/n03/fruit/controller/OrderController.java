package cat.itacademy.s04.t02.n03.fruit.controller;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.mapper.OrderMapper;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderMapper mapper;

    public OrderController(OrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO dto) {
        Order saved = service.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(saved));
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return service.getAllOrders().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable String id) {
        return mapper.toDTO(service.getOrderById(id));
    }

    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable String id, @Valid @RequestBody OrderRequestDTO dto) {
        return mapper.toDTO(service.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
