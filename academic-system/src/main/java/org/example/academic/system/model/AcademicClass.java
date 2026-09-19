package org.example.academic.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Turma do sistema academico. O nome AcademicClass evita colisao com
 * java.lang.Class. A igualdade e definida apenas pelo codigo (TUS-2382),
 * de modo que campos descritivos mutaveis, como o titulo, nao afetam
 * o comportamento da turma em colecoes como HashSet.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of = {"code", "title"})
public class AcademicClass implements Identifiable<String> {

    @EqualsAndHashCode.Include
    @NotBlank(message = "O codigo da turma e obrigatorio")
    private String code;

    @NotBlank(message = "O titulo da turma e obrigatorio")
    private String title;

    private final List<Assessment> assessments = new ArrayList<>();

    public AcademicClass(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    /** Visao somente leitura: protege o encapsulamento da lista interna. */
    public List<Assessment> getAssessments() {
        return Collections.unmodifiableList(assessments);
    }

    @Override
    public String getId() {
        return code;
    }
}
