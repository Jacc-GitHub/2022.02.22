package br.com.letscode.cookbook.view;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUtils {
    private static final String INVALID_OPTION_MSG = "Invalid option. Please try again!";

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getUserInput(String question) {
        return getUserOption(question);
    }

    public static String getUserOption(String message, String... options) {
        System.out.printf(message.concat("%n# : "));
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().trim();
        if (options.length > 0) {
            while (!isValid(option, options)) {
                System.out.printf("%s%n# : ", INVALID_OPTION_MSG);
                option = scanner.nextLine().trim();
            }
        }
        return option;
    }

    public static String getUserString(String message) {
        System.out.printf(message.concat("%n# : "));
        Scanner scanner = new Scanner(System.in);
        String texto= scanner.nextLine().trim();
        if (texto.length() > 0) {
            while (!isValid(texto, texto)) {
                System.out.printf("%s%n# : ", INVALID_OPTION_MSG);
                texto = scanner.nextLine().trim();
            }
        }
        return texto;
    }

    public static double getUserDouble(String message) {
        System.out.printf(message.concat("%n# : "));
        Scanner scanner = new Scanner(System.in);
        double numeroDouble = scanner.nextDouble();
        while (numeroDouble <= 0.0) {
            System.out.printf("%s%n# : ", INVALID_OPTION_MSG);
            numeroDouble = scanner.nextDouble();
        }
        return numeroDouble;
    }

    public static int getUserInt(String message) {
        System.out.printf(message.concat("%n# : "));
        Scanner scanner = new Scanner(System.in);
        int numeroInteiro = scanner.nextInt();
        while (numeroInteiro <= 0) {
            System.out.printf("%s%n# : ", INVALID_OPTION_MSG);
            numeroInteiro = scanner.nextInt();
        }
        return numeroInteiro;
    }

    private static boolean isValid(String option, String... options) {
        for (String v : options) {
            if (v != null && v.equalsIgnoreCase(option)) {
                return true;
            }
        }
        return false;
    }
}
