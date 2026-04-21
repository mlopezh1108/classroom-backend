package net.developz.classroom.backend.catalog.period.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import net.developz.classroom.backend.catalog.period.application.dto.PeriodResponse;
import net.developz.classroom.backend.catalog.period.application.dto.CreatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.dto.UpdatePeriodRequest;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.PeriodEntity;

@Mapper(componentModel = "spring")
public interface PeriodMapper {
    // Entity <-> Domain
    Period toModel(PeriodEntity entity);
    PeriodEntity toEntity(Period model);

    // Domain <-> DTO
    PeriodResponse toDto(Period model);
    Period toModel(CreatePeriodRequest request);
    void updateModelFromRequest(UpdatePeriodRequest request, @MappingTarget Period model);
}
