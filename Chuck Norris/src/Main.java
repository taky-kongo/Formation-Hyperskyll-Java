import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String choice;
        boolean done = false;
        do {
            System.out.print("Please input operation (encode/decode/exit):\n");
            choice = sc.nextLine();
            switch (choice) {
                case "encode":
                    readString(sc);
                    break;
                case "decode":
                    decodeEncryption(sc);
                    break;
                case "exit":
                    System.out.print("Bye!\n");
                    done = true;
                    break;
                default:
                    System.out.print("There is no " + "'" + choice + "'" + " operation\n\n");
                    break;
            }
        } while (!done);
    }

    public static void readString(Scanner scanner) {

        System.out.print("Input string:\n");
        String input = scanner.nextLine();

        System.out.print("Encoded string:\n");
        String[] stringsOfChars = new String[input.length()];
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String format = String.format(Integer.toBinaryString(input.charAt(i)));
            if (format.length() == 7) {
                stringsOfChars[i] = format;
            } else {
                stringsOfChars[i] = "0" + format;
            }
            str.append(stringsOfChars[i]);
        }

        for (int j = 0; j < str.length(); j++) {
            int counter = 0;
            if (str.charAt(j) == '1') {
                System.out.print("0 ");
                while (j < str.length() && str.charAt(j) == '1') {
                    ++counter;
                    ++j;
                }
            } else {
                System.out.print("00 ");
                while (j < str.length() && str.charAt(j) == '0') {
                    ++counter;
                    ++j;
                }
            }
            j--;
            System.out.print("0".repeat(counter) + " ");
        }
        System.out.println("\n");
    }

    public static void decodeEncryption(Scanner scanner) {

        System.out.print("Input encoded string:\n");
        String input = scanner.nextLine();

        boolean continueOperation = verification(input);

        if (continueOperation) {
            StringBuilder strBinary = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i + 1) == ' ') {
                    i += 2;
                    while (i < input.length() && input.charAt(i) != ' ') {
                        strBinary.append(1);
                        ++i;
                    }
                } else {
                    i += 3;
                    while (i < input.length() && input.charAt(i) != ' ') {
                        strBinary.append(0);
                        ++i;
                    }
                }
            }

            if (strBinary.length() % 7 != 0) {
                System.out.print("Encoded string is not valid.\n\n");
                return;
            }

            System.out.print("Decoded string:\n");
            String[] newStrings = new String[strBinary.length() / 7];
            int counter;
            int count = 0;
            for (int i = 0; i < newStrings.length; i++) {
                counter = 0;
                newStrings[i] = "";
                for (int j = 0; j < strBinary.length(); j++) {
                    if (counter == 7) {
                        break;
                    }
                    newStrings[i] += strBinary.charAt(count);
                    counter++;
                    count++;
                }

                if (i == newStrings.length - 1) {
                    System.out.println((char) Integer.parseInt(newStrings[i], 2));
                } else {
                    System.out.print((char) Integer.parseInt(newStrings[i], 2));
                }
            }
        } else {
            System.out.print("Encoded string is not valid.\n\n");
            return;
        }
        System.out.print("\n");
    }

    public static boolean verification(String input) {
        boolean verified = true;
        String[] strings = input.split(" ");
        int numberOfBlocs = strings.length;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (!("0".equals(Character.toString(ch)) || " ".equals(Character.toString(ch)))) {
                return false;
            }
        }

        if (!(input.startsWith("00 ") || input.startsWith("0 "))) {
            return false;
        } else if (numberOfBlocs % 2 != 0) {
            return false;
        }

        int blocs = 0;
        int number = 0;
        for (int i = 0; i < input.length(); i++) {
            int j = i;
            if (input.charAt(i) == ' ') {
                ++blocs;
                if (blocs % 2 != 0) {
                    while (j + 1 < input.length() && input.charAt(j + 1) != ' ') {
                        ++number;
                        j++;
                    }
                }
            }
        }

        if (!(number % 7 == 0)) {
            return false;
        }
        return verified;
    }
}