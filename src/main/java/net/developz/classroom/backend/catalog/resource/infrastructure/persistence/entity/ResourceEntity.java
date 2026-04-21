package net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;

import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "resource_id", length = 26))
public class ResourceEntity extends BaseEntity {

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", length = 20, nullable = false)
    private ResourceType resourceType;

    @Column(name = "content_url", length = 500, nullable = false)
    private String contentUrl;

    @Column(name = "subject_id", length = 26, nullable = false)
    private String subjectId;
}


