package cat.itacademy.s04.t02.n03.fruit.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
        private String fruitName;
        private int quantityInKilos;
    }

