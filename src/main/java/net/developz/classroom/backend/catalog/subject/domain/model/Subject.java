package net.developz.classroom.backend.catalog.subject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Subject extends BaseModel {
    private String subjectCode;
    private String subjectName;
    @Builder.Default
    private Map<String, String> subjectNames = new HashMap<>();
    private Integer level;
}
