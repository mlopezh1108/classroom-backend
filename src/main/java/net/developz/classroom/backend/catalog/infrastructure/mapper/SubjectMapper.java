package net.developz.classroom.backend.catalog.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import net.developz.classroom.backend.catalog.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO toDto(Subject subject);

    Subject toEntity(CreateSubjectRequest request);

    void updateEntityFromRequest(UpdateSubjectRequest request, @MappingTarget Subject subject);
}
