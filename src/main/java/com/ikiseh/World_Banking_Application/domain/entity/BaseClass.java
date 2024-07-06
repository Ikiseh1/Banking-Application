package com.ikiseh.World.Banking.Application.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@EnableJpaAuditing //to audit our jpa repository
@Getter
@Setter
public abstract class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //add timestamp for creation and date modified which will be a column in the database

    @CreatedDate
    private LocalDateTime dateCreated;

    @LastModifiedDate
    private LocalDateTime dateModified;

    //to get the override, use command + N
    //this is optional till the end of the base class is optional and our application will
    //still run without these, but it's done to make our application professional by overriding
    //the default equals method
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        BaseClass that = (BaseClass) obj;
        return Objects.equals(id, that.id);
    }

    @PrePersist // this tells our database to persist this information
    @PreUpdate
    public void prePersist(){
        if(dateCreated == null){
            dateCreated = LocalDateTime.now();
        }
        dateModified= LocalDateTime.now();
    }

    //lets override our hash code too

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
