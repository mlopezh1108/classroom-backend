package net.developz.classroom.backend.catalog.resource.domain.model;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.catalog.resource.infrastructure.persistence.entity.constant.ResourceType;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Resource extends BaseModel {
    private String title;
    private ResourceType resourceType;
    private String contentUrl;
    private String subjectId;
}
