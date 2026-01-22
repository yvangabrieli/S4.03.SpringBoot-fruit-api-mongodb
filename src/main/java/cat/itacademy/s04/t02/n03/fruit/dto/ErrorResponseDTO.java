package cat.itacademy.s04.t02.n03.fruit.dto;
import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO (
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        List<String> validationErrors
){}
