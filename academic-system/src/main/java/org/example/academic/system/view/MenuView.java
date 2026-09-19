package org.example.academic.system.view;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.InvalidMenuOptionException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.AssessmentType;
import org.example.academic.system.model.Role;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.security.Session;
import org.example.academic.system.security.SystemOperation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu de linha de comando com renderizacao dinamica por papel
 * (US-2378) e numeracao sequencial a partir de 1 (US-2380).
 *
 * As opcoes sao construidas consultando a mesma matriz de permissoes
 * usada pela autorizacao, de modo que o menu apenas reflete as
 * permissoes: a checagem de autorizacao continua ativa no controller.
 */
public class MenuView {

    private final KeyboardReader reader;

    public MenuView(KeyboardReader reader) {
        this.reader = reader;
    }

    /** Exibe o menu do papel autenticado, executa a opcao e retorna o resultado. */
    public MenuResult displayAndExecute(AcademicSystemController controller, Session session) {
        List<MenuOption> options = buildOptions(controller, session);
        Role role = session.getUser().getRole();

        System.out.println();
        System.out.println("===== MENU - " + session.getUser().getUsername()
                + " (" + role.getLabel() + ") =====");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i).label());
        }

        int choice = reader.readInt("Escolha uma opcao: ");
        if (choice < 1 || choice > options.size()) {
            throw new InvalidMenuOptionException(choice);
        }
        return options.get(choice - 1).action().get();
    }

    private List<MenuOption> buildOptions(AcademicSystemController controller, Session session) {
        Role role = session.getUser().getRole();
        List<MenuOption> options = new ArrayList<>();

        if (AuthorizationService.isAllowed(role, SystemOperation.REGISTER_CLASS)) {
            options.add(new MenuOption(SystemOperation.REGISTER_CLASS.getLabel(),
                    () -> { registerClass(controller); return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.REGISTER_ASSESSMENT)) {
            options.add(new MenuOption(SystemOperation.REGISTER_ASSESSMENT.getLabel(),
                    () -> { registerAssessment(controller); return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.VIEW_ACADEMIC_DATA)) {
            options.add(new MenuOption(SystemOperation.VIEW_ACADEMIC_DATA.getLabel(),
                    () -> { listClasses(controller); return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.SAVE_DATA)) {
            options.add(new MenuOption(SystemOperation.SAVE_DATA.getLabel(),
                    () -> { saveData(controller); return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.CONFIGURE_PERSISTENCE)) {
            options.add(new MenuOption(SystemOperation.CONFIGURE_PERSISTENCE.getLabel(),
                    () -> { configurePersistence(controller); return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.GENERATE_SUMMARY_REPORT)) {
            options.add(new MenuOption(SystemOperation.GENERATE_SUMMARY_REPORT.getLabel(),
                    () -> { System.out.println(controller.generateClassAssessmentSummaryReport());
                            return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.GENERATE_WEIGHT_REPORT)) {
            options.add(new MenuOption(SystemOperation.GENERATE_WEIGHT_REPORT.getLabel(),
                    () -> { System.out.println(controller.generateAssessmentWeightReport());
                            return MenuResult.CONTINUE; }));
        }
        if (AuthorizationService.isAllowed(role, SystemOperation.GENERATE_PERSISTENCE_REPORT)) {
            options.add(new MenuOption(SystemOperation.GENERATE_PERSISTENCE_REPORT.getLabel(),
                    () -> { System.out.println(controller.generatePersistenceConfigurationReport());
                            return MenuResult.CONTINUE; }));
        }

        options.add(new MenuOption("Logout", () -> MenuResult.LOGOUT));
        options.add(new MenuOption("Encerrar o sistema", () -> MenuResult.EXIT));
        return options;
    }

    private void registerClass(AcademicSystemController controller) {
        String code = reader.readLine("Codigo da turma: ");
        String title = reader.readLine("Titulo da turma: ");
        AcademicClass academicClass = controller.registerClass(code, title);
        System.out.println("Turma cadastrada com sucesso: "
                + academicClass.getCode() + " - " + academicClass.getTitle());
    }

    private void registerAssessment(AcademicSystemController controller) {
        listClasses(controller);
        String code = reader.readLine("Codigo da turma: ");

        System.out.println("Tipos de avaliacao:");
        AssessmentType[] types = AssessmentType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i].getLabel());
        }
        int typeChoice = reader.readInt("Tipo: ");
        if (typeChoice < 1 || typeChoice > types.length) {
            throw new InvalidMenuOptionException(typeChoice);
        }

        String description = reader.readLine("Descricao da avaliacao: ");
        double value = reader.readDouble("Valor (ex.: 10.0): ");
        double weight = reader.readDouble("Peso (maior que 0 e ate 1, ex.: 0.4): ");

        controller.registerAssessment(code, types[typeChoice - 1].name(), description, value, weight);
        System.out.println("Avaliacao cadastrada com sucesso.");
    }

    private void listClasses(AcademicSystemController controller) {
        List<AcademicClass> classes = controller.listClasses();
        if (classes.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        System.out.println("Turmas cadastradas:");
        for (AcademicClass academicClass : classes) {
            System.out.println("- " + academicClass.getCode() + " - " + academicClass.getTitle());
            for (Assessment assessment : academicClass.getAssessments()) {
                System.out.println("    [" + assessment.getType().getLabel() + "] "
                        + assessment.getDescription()
                        + " | Valor: " + assessment.getValue()
                        + " | Peso: " + assessment.getWeight());
            }
        }
    }

    private void saveData(AcademicSystemController controller) {
        Path target = controller.saveAcademicData();
        System.out.println("Dados salvos com sucesso em: " + target
                + " (formato " + controller.getCurrentPersistenceType() + ")");
    }

    private void configurePersistence(AcademicSystemController controller) {
        System.out.println("Tipo atual: " + controller.getCurrentPersistenceType());
        String type = reader.readLine("Novo tipo de persistencia (TXT, XML ou JSON): ");
        controller.configurePersistence(type);
        System.out.println("Tipo de persistencia configurado para: "
                + controller.getCurrentPersistenceType());
    }
}
