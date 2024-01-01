package io.thebootcode.medium.mediumapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
public abstract class AtomicModel implements Serializable {

    protected ZonedDateTime createdAt;

    protected ZonedDateTime updatedAt;

    public AtomicModel() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public String getUpdatedDateStringPretty(){
        return getUpdatedAt() != null ? DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(getUpdatedAt()) : "n/a";
    }

    @JsonIgnore
    public String getCreatedDateStringPretty(){
        return getCreatedAt() != null ? DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(getCreatedAt()) : "n/a";
    }

}
