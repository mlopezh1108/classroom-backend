package net.developz.classroom.backend.academic.course.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.developz.classroom.backend.shared.domain.model.BaseModel;

import java.time.LocalTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseSchedule extends BaseModel {
    private Course course;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dayId;
}
