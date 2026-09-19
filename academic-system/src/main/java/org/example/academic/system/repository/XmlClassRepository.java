package org.example.academic.system.repository;

import org.example.academic.system.exception.PersistenceOperationException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.util.List;

/**
 * Estrategia de persistencia em XML (US-2373), implementada com a API
 * DOM da propria plataforma Java, sem dependencias externas.
 */
public class XmlClassRepository implements ClassRepository {

    @Override
    public void save(List<AcademicClass> classes, Path target) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();

            Element root = document.createElement("academicSystem");
            document.appendChild(root);
            Element classesElement = document.createElement("classes");
            root.appendChild(classesElement);

            for (AcademicClass academicClass : classes) {
                Element classElement = document.createElement("class");
                classElement.setAttribute("code", academicClass.getCode());
                classElement.setAttribute("title", academicClass.getTitle());

                Element assessmentsElement = document.createElement("assessments");
                for (Assessment assessment : academicClass.getAssessments()) {
                    Element assessmentElement = document.createElement("assessment");
                    assessmentElement.setAttribute("type", assessment.getType().name());
                    assessmentElement.setAttribute("description", assessment.getDescription());
                    assessmentElement.setAttribute("value", String.valueOf(assessment.getValue()));
                    assessmentElement.setAttribute("weight", String.valueOf(assessment.getWeight()));
                    assessmentsElement.appendChild(assessmentElement);
                }
                classElement.appendChild(assessmentsElement);
                classesElement.appendChild(classElement);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(document), new StreamResult(target.toFile()));
        } catch (Exception e) {
            throw new PersistenceOperationException("Falha ao salvar o arquivo XML: " + target, e);
        }
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.XML;
    }
}
