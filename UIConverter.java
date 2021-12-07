package converter;

import java.util.Objects;
import java.util.Scanner;

public class UIConverter {
    private final Scanner scanner = new Scanner(System.in);

    public void runApp() {
        while (true) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit");
            String input = scanner.nextLine();
            if (input.contains("/exit")) {
                scanner.close();
                System.exit(0);
            }

            int sourceBase = Integer.parseInt(input.split("\\s+")[0]);
            int targetBase = Integer.parseInt(input.split("\\s+")[1]);

            conversionMenu(sourceBase, targetBase);
        }
    }

    private void conversionMenu(int sourceBase, int targetBase) {
        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back\n", sourceBase, targetBase);
            String input = scanner.nextLine();
            if (input.contains("/back")) {
                break;
            }

            String result;
            String fractalPart = null;

            if (input.contains(".")) {
                fractalPart = input.split("\\.")[1];
                input = input.split("\\.")[0];
            }

            if (targetBase == 10) {
                result = Converter.anotherToDecimal(sourceBase, input);
                fractalPart = Objects.isNull(fractalPart) ? null : Converter.anotherFactorialToDecimal(sourceBase, fractalPart);
            } else if (sourceBase != 10) {
                String temp = Converter.anotherToDecimal(sourceBase, input);
                result = Converter.decimalToAnother(targetBase, temp);
                if (Objects.nonNull(fractalPart)) {
                    fractalPart = Converter.anotherFactorialToDecimal(sourceBase, fractalPart);
                    fractalPart = Converter.decimalFactorialToAnother(targetBase, fractalPart);
                }
            } else {
                result = Converter.decimalToAnother(targetBase, input);
                fractalPart = Objects.isNull(fractalPart) ? null : "." + Converter.decimalFactorialToAnother(sourceBase,  fractalPart);
            }

            System.out.printf("Conversion result: %s%s\n", result, fractalPart == null ? "" : "." +fractalPart);
        }
    }

}