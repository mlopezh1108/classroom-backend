package net.developz.classroom.backend.catalog.exam.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.catalog.exam.infrastructure.persistence.entity.QuestionType;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Question extends BaseModel {
    private String text;
    private Integer orderIndex;
    private Double points;
    private QuestionType questionType;
    private Exam exam;
}
