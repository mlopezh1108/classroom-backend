package net.developz.classroom.backend.academic.assessment.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@DiscriminatorValue("MATCHING")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchingAttemptAnswer extends AttemptAnswer {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "selected_pairs", columnDefinition = "jsonb")
    private Map<String, String> selectedPairs = new HashMap<>(); // key: leftSideId or text, value: rightSideId or text
}





