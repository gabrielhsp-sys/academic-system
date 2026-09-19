package org.example.academic.system.security;

/**
 * Operacoes protegidas do sistema, usadas nas verificacoes de
 * autorizacao e na renderizacao dinamica dos menus (US-2378).
 */
public enum SystemOperation {
    REGISTER_CLASS("Cadastrar turma"),
    REGISTER_ASSESSMENT("Cadastrar avaliacao"),
    VIEW_ACADEMIC_DATA("Visualizar turmas e avaliacoes"),
    SAVE_DATA("Salvar dados academicos"),
    CONFIGURE_PERSISTENCE("Configurar tipo de persistencia"),
    GENERATE_SUMMARY_REPORT("Relatorio de avaliacoes por turma"),
    GENERATE_WEIGHT_REPORT("Relatorio de pesos das avaliacoes"),
    GENERATE_PERSISTENCE_REPORT("Relatorio de configuracao de persistencia");

    private final String label;

    SystemOperation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
