package net.developz.classroom.backend.iam.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseModel {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String governmentId;
    private String email;
    private String password;
    private String defaultRole;
}
