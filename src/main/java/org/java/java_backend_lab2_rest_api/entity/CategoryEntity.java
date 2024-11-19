package org.java.java_backend_lab2_rest_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories", schema = "mydatabase")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 55)
    @NotNull
    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
}
