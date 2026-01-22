package cat.itacademy.s04.t02.n03.fruit.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public record OrderRequestDTO (
    @NotBlank(message = "Client name cannot be blank") String clientName,

    @NotNull(message = "Delivery date must be in the future") LocalDate deliveryDate,

    @NotEmpty(message = "Order must have at least one item") List<@Valid OrderItemDTO> items
){}

