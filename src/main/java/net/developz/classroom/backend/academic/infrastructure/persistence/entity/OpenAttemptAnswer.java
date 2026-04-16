package net.developz.classroom.backend.academic.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("OPEN")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OpenAttemptAnswer extends AttemptAnswer {

    @Column(name = "response_text", length = 2000)
    private String responseText;
}
