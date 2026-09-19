package org.example.academic.system.security;

import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Autorizacao baseada em papeis (RBAC). A matriz de permissoes e a
 * unica fonte de verdade: tanto as verificacoes de acesso quanto a
 * renderizacao dos menus (US-2378) consultam o mesmo mapa, garantindo
 * que a interface nunca substitua a checagem de autorizacao (AC6).
 */
public class AuthorizationService {

    private static final Logger AUDIT = LoggerFactory.getLogger("AUDIT");

    private static final Map<SystemOperation, Set<Role>> PERMISSIONS = new EnumMap<>(SystemOperation.class);

    static {
        PERMISSIONS.put(SystemOperation.REGISTER_CLASS, Set.of(Role.ADMIN));
        PERMISSIONS.put(SystemOperation.SAVE_DATA, Set.of(Role.ADMIN));
        PERMISSIONS.put(SystemOperation.CONFIGURE_PERSISTENCE, Set.of(Role.ADMIN));
        PERMISSIONS.put(SystemOperation.GENERATE_PERSISTENCE_REPORT, Set.of(Role.ADMIN));
        PERMISSIONS.put(SystemOperation.REGISTER_ASSESSMENT, Set.of(Role.PROFESSOR));
        PERMISSIONS.put(SystemOperation.VIEW_ACADEMIC_DATA, Set.of(Role.ADMIN, Role.PROFESSOR));
        PERMISSIONS.put(SystemOperation.GENERATE_SUMMARY_REPORT, Set.of(Role.ADMIN, Role.PROFESSOR));
        PERMISSIONS.put(SystemOperation.GENERATE_WEIGHT_REPORT, Set.of(Role.ADMIN, Role.PROFESSOR));
    }

    /** Consulta usada pelos menus e telas para exibir apenas o permitido. */
    public static boolean isAllowed(Role role, SystemOperation operation) {
        return PERMISSIONS.getOrDefault(operation, Set.of()).contains(role);
    }

    /**
     * Garante que a sessao esta ativa e que o papel do usuario tem
     * permissao para a operacao. Falhas sao registradas para auditoria
     * (TUS-2392) e resultam em AuthorizationException.
     */
    public void authorize(Session session, SystemOperation operation) {
        if (session == null || !session.isActive()) {
            AUDIT.warn("Tentativa de acesso sem sessao ativa a operacao [{}]", operation);
            throw new AuthorizationException(
                    "Sessao invalida ou expirada para a operacao: " + operation.getLabel());
        }
        Role role = session.getUser().getRole();
        if (!isAllowed(role, operation)) {
            AUDIT.warn("Acesso negado: usuario [{}] com papel [{}] tentou executar a operacao [{}]",
                    session.getUser().getUsername(), role, operation);
            throw new AuthorizationException(
                    "Acesso negado: o papel " + role + " nao tem permissao para: " + operation.getLabel());
        }
    }
}
