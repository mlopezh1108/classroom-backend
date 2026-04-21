package net.developz.classroom.backend.iam.user.infrastructure.mapper;

import net.developz.classroom.backend.iam.user.domain.model.*;
import net.developz.classroom.backend.iam.user.infrastructure.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // Person
    Person toModel(PersonEntity entity);

    PersonEntity toEntity(Person model);

    // Student
    Student toModel(StudentEntity entity);

    StudentEntity toEntity(Student model);

    // Teacher
    Teacher toModel(TeacherEntity entity);

    TeacherEntity toEntity(Teacher model);

    // Administrator
    Administrator toModel(AdministratorEntity entity);

    AdministratorEntity toEntity(Administrator model);
}
