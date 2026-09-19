package org.example.academic.system.view;

import java.util.function.Supplier;

/** Opcao de menu: rotulo exibido e acao executada quando selecionada. */
public record MenuOption(String label, Supplier<MenuResult> action) {
}
