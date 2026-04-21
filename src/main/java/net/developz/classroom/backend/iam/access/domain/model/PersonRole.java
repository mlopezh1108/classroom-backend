package net.developz.classroom.backend.iam.access.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonRole extends BaseModel {
    private String personId;
    private Role role;
    private Boolean active;
}
