package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;
 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entities.enums.ResourceType;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;
 
@Entity
@Table(name = "resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "resource_id", length = 26))
public class Resource extends BaseEntity {
 
    @Column(name = "title", length = 100, nullable = false)
    private String title;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", length = 20, nullable = false)
    private ResourceType resourceType;
 
    @Column(name = "content_url", length = 500, nullable = false)
    private String contentUrl;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
