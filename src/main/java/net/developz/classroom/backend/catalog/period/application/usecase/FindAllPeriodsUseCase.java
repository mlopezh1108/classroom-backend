package net.developz.classroom.backend.catalog.period.application.usecase;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.catalog.period.application.port.PeriodRepositoryPort;
import net.developz.classroom.backend.catalog.period.domain.model.Period;
import net.developz.classroom.backend.shared.application.annotation.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllPeriodsUseCase {
    private final PeriodRepositoryPort periodRepositoryPort;
    
    public Page<Period> execute(Pageable pageable) {
        return periodRepositoryPort.findAllSorted(pageable);
    }

    public List<Period> execute() { 
        return periodRepositoryPort.findAll(); 
    }
}
