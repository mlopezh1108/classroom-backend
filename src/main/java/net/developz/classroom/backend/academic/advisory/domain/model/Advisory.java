package net.developz.classroom.backend.academic.advisory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.academic.advisory.domain.model.enums.AdvisoryStatus;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Advisory extends BaseModel {
    private AdvisoryStatus status;
    private String enrollmentId;
    private LocalDate date;
    private LocalTime time;
    private String notes;
}
