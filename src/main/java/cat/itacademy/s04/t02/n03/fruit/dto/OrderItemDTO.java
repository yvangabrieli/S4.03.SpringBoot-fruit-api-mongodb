package cat.itacademy.s04.t02.n03.fruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record OrderItemDTO (
    @NotBlank(message = "Fruit name cannot be blank") String fruitName,

    @Positive(message = "Quantity must be positive") int quantityInKilos
) {}
