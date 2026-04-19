package net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity;

import jakarta.persistence.*;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.enums.AdvisoryStatus;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.Enrollment;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "advisory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "advisory_id", length = 26))
public class Advisory extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private AdvisoryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "notes", length = 1000)
    private String notes;
}
