package cat.itacademy.s04.t02.n03.fruit.mapper;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderRequestDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderResponseDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setClientName(dto.clientName());
        order.setDeliveryDate(dto.deliveryDate());
        order.setItems(dto.items().stream()
                .map(this::toEntity)
                .collect(Collectors.toList()));
        return order;
    }

    public OrderItem toEntity(OrderItemDTO dto) {
        return new OrderItem(dto.fruitName(), dto.quantityInKilos());
    }

    public OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getClientName(),
                order.getDeliveryDate(),
                order.getItems() == null
                ? List.of()
                : order.getItems().stream()
                        .map(i -> new OrderItemDTO(i.getFruitName(), i.getQuantityInKilos()))
                        .collect(Collectors.toList())
        );
    }
}
