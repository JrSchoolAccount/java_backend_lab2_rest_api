package org.java.java_backend_lab2_rest_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "location", schema = "mydatabase")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String user;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private LocationStatus status;

    @Size(max = 255)
    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "coordinate", columnDefinition = "geometry not null")
    private Point<G2D> coordinate;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
