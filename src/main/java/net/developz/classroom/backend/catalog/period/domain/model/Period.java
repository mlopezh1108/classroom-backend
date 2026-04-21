package net.developz.classroom.backend.catalog.period.domain.model;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Period extends BaseModel {
    private String periodCode;
    private LocalDate startDate;
    private LocalDate endDate;
}
