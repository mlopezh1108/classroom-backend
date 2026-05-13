package net.developz.classroom.backend.academic.course.infrastructure.persistence.adapter;

import net.developz.classroom.backend.academic.course.application.port.CourseRepositoryPort;
import net.developz.classroom.backend.academic.course.domain.model.Course;
import net.developz.classroom.backend.academic.course.infrastructure.mapper.CourseMapper;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseEntity;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.entity.CourseDetailsProjection;
import net.developz.classroom.backend.academic.course.infrastructure.persistence.repository.CourseRepository;
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
public class CourseRepositoryAdapter
        extends JpaRepositoryAdapter<Course, CourseEntity, String, CourseRepository>
        implements CourseRepositoryPort {

    private final CourseMapper courseMapper;

    public CourseRepositoryAdapter(CourseRepository repository, CourseMapper courseMapper) {
        super(repository);
        this.courseMapper = courseMapper;
    }

    @Override
    protected Course toModel(CourseEntity entity) {
        return courseMapper.toModel(entity);
    }

    @Override
    protected CourseEntity toEntity(Course model) {
        return courseMapper.toEntity(model);
    }

    @Override
    public java.util.Optional<Course> findById(String id) {
        return repository.findDetailedById(id).map(courseMapper::toModel);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAllDetailed().stream()
                .map(courseMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<Course> findAll(PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<CourseDetailsProjection> page = repository.findAllDetailed(pageable);
        return toPaginatedResultFromProjection(page);
    }

    @Override
    public List<Course> findByTeacherId(String teacherId) {
        return repository.findDetailedByTeacherId(teacherId).stream()
                .map(courseMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<Course> findByTeacherId(String teacherId, PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<CourseDetailsProjection> page = repository.findDetailedByTeacherId(teacherId, pageable);
        return toPaginatedResultFromProjection(page);
    }

    @Override
    public List<Course> findByPeriodId(String periodId) {
        return repository.findDetailedByPeriodId(periodId).stream()
                .map(courseMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<Course> findByPeriodId(String periodId, PaginationCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.page(), criteria.size());
        Page<CourseDetailsProjection> page = repository.findDetailedByPeriodId(periodId, pageable);
        return toPaginatedResultFromProjection(page);
    }

    private PaginatedResult<Course> toPaginatedResultFromProjection(Page<CourseDetailsProjection> page) {
        return new PaginatedResult<>(
                page.getContent().stream().map(courseMapper::toModel).toList(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize()
        );
    }
}
