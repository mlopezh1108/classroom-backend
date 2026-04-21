package net.developz.classroom.backend.academic.course.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.academic.course.domain.model.enums.CourseStatus;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseModel {
    private String courseCode;
    private CourseStatus status;
    private String teacherId;
    private String groupId;
    private String subjectId;
    private String periodId;
    private String teacherName;
    private String groupCode;
    private String subjectName;
    private String periodCode;
}
