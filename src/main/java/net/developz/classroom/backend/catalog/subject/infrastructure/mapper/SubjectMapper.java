package net.developz.classroom.backend.catalog.subject.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import net.developz.classroom.backend.catalog.subject.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.subject.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.subject.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.subject.domain.model.Subject;
import net.developz.classroom.backend.catalog.subject.infrastructure.persistence.entity.SubjectEntity;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    // Entity <-> Domain
    Subject toModel(SubjectEntity entity);
    SubjectEntity toEntity(Subject model);

    // Domain <-> DTO
    SubjectDTO toDto(Subject model);
    Subject toModel(CreateSubjectRequest request);
    void updateModelFromRequest(UpdateSubjectRequest request, @MappingTarget Subject subject);
}


