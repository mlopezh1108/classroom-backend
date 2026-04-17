package net.developz.classroom.backend.catalog.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.infrastructure.persistence.entities.BaseEntity;

@Entity
@Table(name = "matching_pair")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "pair_id", length = 26))
public class MatchingPair extends BaseEntity {

    @Column(name = "left_side", length = 500, nullable = false)
    private String leftSide;

    @Column(name = "right_side", length = 500, nullable = false)
    private String rightSide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private MatchingQuestion question;
}
