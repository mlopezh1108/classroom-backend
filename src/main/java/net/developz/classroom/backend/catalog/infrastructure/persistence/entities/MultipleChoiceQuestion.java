package net.developz.classroom.backend.catalog.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options = new ArrayList<>();
}

