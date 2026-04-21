package net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MATCHING")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MatchingQuestionEntity extends QuestionEntity {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchingPairEntity> pairs = new ArrayList<>();
}


