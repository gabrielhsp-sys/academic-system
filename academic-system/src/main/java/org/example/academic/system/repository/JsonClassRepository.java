package org.example.academic.system.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Estrategia de persistencia em JSON (US-2374), implementada com Jackson.
 * A estrutura e montada em mapas para nao acoplar o modelo de dominio
 * a anotacoes do framework de serializacao.
 */
public class JsonClassRepository implements ClassRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(List<AcademicClass> classes, Path target) {
        List<Map<String, Object>> payload = new ArrayList<>();
        for (AcademicClass academicClass : classes) {
            Map<String, Object> classMap = new LinkedHashMap<>();
            classMap.put("code", academicClass.getCode());
            classMap.put("title", academicClass.getTitle());

            List<Map<String, Object>> assessments = new ArrayList<>();
            for (Assessment assessment : academicClass.getAssessments()) {
                Map<String, Object> assessmentMap = new LinkedHashMap<>();
                assessmentMap.put("type", assessment.getType().name());
                assessmentMap.put("description", assessment.getDescription());
                assessmentMap.put("value", assessment.getValue());
                assessmentMap.put("weight", assessment.getWeight());
                assessments.add(assessmentMap);
            }
            classMap.put("assessments", assessments);
            payload.add(classMap);
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(target.toFile(), payload);
        } catch (IOException e) {
            throw new PersistenceOperationException("Falha ao salvar o arquivo JSON: " + target, e);
        }
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JSON;
    }
}
