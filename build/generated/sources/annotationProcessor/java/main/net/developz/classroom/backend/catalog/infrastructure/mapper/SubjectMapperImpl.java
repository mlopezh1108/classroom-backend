package net.developz.classroom.backend.catalog.infrastructure.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import net.developz.classroom.backend.catalog.application.dto.CreateSubjectRequest;
import net.developz.classroom.backend.catalog.application.dto.SubjectDTO;
import net.developz.classroom.backend.catalog.application.dto.UpdateSubjectRequest;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T20:23:54-0600",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectDTO toDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        String id = null;
        String subjectCode = null;
        Integer level = null;
        Map<String, String> subjectNames = null;

        id = subject.getId();
        subjectCode = subject.getSubjectCode();
        level = subject.getLevel();
        Map<String, String> map = subject.getSubjectNames();
        if ( map != null ) {
            subjectNames = new LinkedHashMap<String, String>( map );
        }

        SubjectDTO subjectDTO = new SubjectDTO( id, subjectCode, level, subjectNames );

        return subjectDTO;
    }

    @Override
    public Subject toEntity(CreateSubjectRequest request) {
        if ( request == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setSubjectCode( request.subjectCode() );
        Map<String, String> map = request.subjectNames();
        if ( map != null ) {
            subject.setSubjectNames( new LinkedHashMap<String, String>( map ) );
        }
        subject.setLevel( request.level() );

        return subject;
    }

    @Override
    public void updateEntityFromRequest(UpdateSubjectRequest request, Subject subject) {
        if ( request == null ) {
            return;
        }

        subject.setSubjectCode( request.subjectCode() );
        if ( subject.getSubjectNames() != null ) {
            Map<String, String> map = request.subjectNames();
            if ( map != null ) {
                subject.getSubjectNames().clear();
                subject.getSubjectNames().putAll( map );
            }
            else {
                subject.setSubjectNames( null );
            }
        }
        else {
            Map<String, String> map = request.subjectNames();
            if ( map != null ) {
                subject.setSubjectNames( new LinkedHashMap<String, String>( map ) );
            }
        }
        subject.setLevel( request.level() );
    }
}
