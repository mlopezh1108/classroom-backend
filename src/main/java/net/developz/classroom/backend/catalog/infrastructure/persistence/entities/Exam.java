package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;
 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.developz.classroom.backend.infrastructure.persistence.entities.BaseEntity;
 
import java.util.ArrayList;
import java.util.List;
 
@Entity
@Table(name = "exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "exam_id", length = 26))
public class Exam extends BaseEntity {
 
    @Column(name = "title", length = 100, nullable = false)
    private String title;
 
    @Column(name = "description", length = 500)
    private String description;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
 
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
}
