package cat.itacademy.s04.t02.n03.fruit.service;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import cat.itacademy.s04.t02.n03.fruit.exception.OrderNotFoundException;
import cat.itacademy.s04.t02.n03.fruit.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Order createOrder(OrderRequestDTO dto) {
        Order order = mapper.toEntity(dto);
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
    }

    public Order updateOrder(String id, OrderRequestDTO dto) {
        Order existing = getOrderById(id);
        existing.setClientName(dto.clientName());
        existing.setDeliveryDate(dto.deliveryDate());
        existing.setItems(dto.items() == null ? List.of() :
                dto.items().stream()
                        .map(mapper::toEntity)
                        .toList()
        );
        return repository.save(existing);
    }

    public void deleteOrder(String id) {
        if (!repository.existsById(id)) {
            throw new OrderNotFoundException("Order not found: " + id);
        }
        repository.deleteById(id);
    }
}
