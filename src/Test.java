import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SecurityType securityType = new SecurityType(input(scanner));
        securityType.generateSecurityMessage();
        securityType.printResult();
    }

    public static String input(Scanner scanner) {
        System.out.print("Input your message: ");

        String s = scanner.nextLine();
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (int j = 0; j < bytes.length; j++) {
            int val = bytes[j];
            for (int i = 0; i < 8; i++) {
                val <<= 1;
                binary.append((val & 128) == 0 ? 0 : 1);
            }
        }

        System.out.println("'" + s + "' to binary: " + binary);

        return binary.toString();
    }
}
