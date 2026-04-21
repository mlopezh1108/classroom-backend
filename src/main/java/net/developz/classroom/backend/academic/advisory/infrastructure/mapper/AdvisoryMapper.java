package net.developz.classroom.backend.academic.advisory.infrastructure.mapper;

import net.developz.classroom.backend.academic.advisory.application.dto.AdvisoryDTO;
import net.developz.classroom.backend.academic.advisory.application.dto.ScheduleAdvisoryRequest;
import net.developz.classroom.backend.academic.advisory.domain.model.Advisory;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.AdvisoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdvisoryMapper {

    // Entity <-> Domain
    Advisory toModel(AdvisoryEntity entity);
    AdvisoryEntity toEntity(Advisory model);

    // Domain <-> DTO
    AdvisoryDTO toDTO(Advisory model);
    Advisory toModel(ScheduleAdvisoryRequest request);
}
