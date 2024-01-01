package io.thebootcode.medium.mediumapi.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.thebootcode.medium.mediumapi.models.BaseModel;
import io.thebootcode.medium.mediumapi.models.enums.AppRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email")
        })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 7727388512969738865L;

    private String email;

    private String uuid;

    @JsonIgnore
    private String password;

    @ElementCollection(targetClass = AppRole.class)
    @Enumerated(EnumType.STRING)
    private Set<AppRole> roles = new HashSet<>();


}
