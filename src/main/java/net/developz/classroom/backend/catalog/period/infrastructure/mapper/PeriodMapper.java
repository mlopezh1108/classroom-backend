package net.developz.classroom.backend.catalog.period.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import net.developz.classroom.backend.catalog.period.application.dto.PeriodResponse;
import net.developz.classroom.backend.catalog.period.application.dto.CreatePeriodRequest;
import net.developz.classroom.backend.catalog.period.application.dto.UpdatePeriodRequest;
import net.developz.classroom.backend.catalog.period.infrastructure.persistence.entity.Period;

@Mapper(componentModel = "spring")
public interface PeriodMapper {
    PeriodResponse toDto(Period period);
    Period toEntity(CreatePeriodRequest request);
    void updateEntityFromRequest(UpdatePeriodRequest request, @MappingTarget Period period);
}
