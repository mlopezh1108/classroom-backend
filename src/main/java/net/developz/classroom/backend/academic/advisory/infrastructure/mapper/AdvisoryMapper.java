package net.developz.classroom.backend.academic.advisory.infrastructure.mapper;

import net.developz.classroom.backend.academic.advisory.application.dto.AdvisoryDTO;
import net.developz.classroom.backend.academic.advisory.application.dto.ScheduleAdvisoryRequest;
import net.developz.classroom.backend.academic.advisory.infrastructure.persistence.entity.Advisory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdvisoryMapper {

    AdvisoryDTO toDTO(Advisory advisory);

    Advisory toEntity(ScheduleAdvisoryRequest request);
}
