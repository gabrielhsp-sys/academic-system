package org.example.academic.system.view;

import org.example.academic.system.exception.InvalidNumberInputException;

import java.util.Scanner;

/**
 * Encapsula a leitura do teclado e converte erros de entrada em
 * excecoes da hierarquia KeyboardInputException (US-2368).
 */
public class KeyboardReader {

    private final Scanner scanner;

    public KeyboardReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        String input = readLine(prompt);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException(input, e);
        }
    }

    public double readDouble(String prompt) {
        String input = readLine(prompt);
        try {
            return Double.parseDouble(input.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new InvalidNumberInputException(input, e);
        }
    }
}
