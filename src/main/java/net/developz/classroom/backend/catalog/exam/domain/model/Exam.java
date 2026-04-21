package net.developz.classroom.backend.catalog.exam.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder; import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Exam extends BaseModel {
    private String title;
    private String description;
    private String subjectId;
    @Builder.Default
    private List<Question> questions = new ArrayList<>();
}
