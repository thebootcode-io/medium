package io.thebootcode.medium.mediumapi.models.products;

import io.thebootcode.medium.mediumapi.models.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseModel  implements Serializable{

    @Serial
    private static final long serialVersionUID = 7727383412969738865L;

    private String uuid;

    private String name;

    public Product(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
    }

}

