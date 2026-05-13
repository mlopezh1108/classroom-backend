package net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.enrollment.application.port.EnrollmentRepositoryPort;
import net.developz.classroom.backend.academic.enrollment.domain.model.Enrollment;
import net.developz.classroom.backend.academic.enrollment.infrastructure.mapper.EnrollmentMapper;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentDetailsProjection;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.entity.EnrollmentEntity;
import net.developz.classroom.backend.academic.enrollment.infrastructure.persistence.repository.EnrollmentRepository;
import net.developz.classroom.backend.shared.domain.pagination.PaginatedResult;
import net.developz.classroom.backend.shared.domain.pagination.PaginationCriteria;
import net.developz.classroom.backend.shared.infrastructure.persistence.adapter.JpaRepositoryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentRepositoryAdapter
        extends JpaRepositoryAdapter<Enrollment, EnrollmentEntity, String, EnrollmentRepository>
        implements EnrollmentRepositoryPort {

    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentRepositoryAdapter(EnrollmentRepository repository, EnrollmentMapper enrollmentMapper) {
        super(repository);
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    protected Enrollment toModel(EnrollmentEntity entity) {
        return enrollmentMapper.toModel(entity);
    }

    @Override
    protected EnrollmentEntity toEntity(Enrollment model) {
        return enrollmentMapper.toEntity(model);
    }

    @Override
    public java.util.Optional<Enrollment> findById(String id) {
        return repository.findDetailedById(id).map(enrollmentMapper::toModel);
    }

    @Override
    public List<Enrollment> findAll() {
        return repository.findAllDetailed().stream()
                .map(enrollmentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<Enrollment> findAll(PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<EnrollmentDetailsProjection> page = repository.findAllDetailed(pageable);
        return toPaginatedResultFromProjection(page);
    }

    @Override
    public PaginatedResult<Enrollment> findByCourseId(String courseId, PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<EnrollmentDetailsProjection> page = repository.findDetailedByCourseId(courseId, pageable);
        return toPaginatedResultFromProjection(page);
    }

    @Override
    public List<Enrollment> findByCourseId(String courseId) {
        return repository.findDetailedByCourseId(courseId).stream()
                .map(enrollmentMapper::toModel)
                .collect(Collectors.toList());
    }

    private PaginatedResult<Enrollment> toPaginatedResultFromProjection(Page<EnrollmentDetailsProjection> page) {
        return new PaginatedResult<>(
                page.getContent().stream().map(enrollmentMapper::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
