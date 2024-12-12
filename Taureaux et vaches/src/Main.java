import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MAX_ATTEMPTS;
        String SECRET_NUMBER;
        int turn = 0;
        boolean trueValue = true;

        System.out.println("Please, enter the secret code's length:");

        int lengthOfCode;

        if (!sc.hasNextInt()) {
            String input = sc.nextLine();
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
        } else {

            lengthOfCode = sc.nextInt();
            sc.nextLine();

            if (lengthOfCode > 36 || lengthOfCode < 1) {
                if (lengthOfCode > 36) {
                    System.out.println("Error: Unable to generate a secret number with a length of 36 because there aren't enough unique digits and characters.");
                } else {
                    System.out.println("Error: the secret code's length must be at least 1.");
                }
            } else {
                System.out.println("Input the number of possible symbols in the code:");

                if (!sc.hasNextInt()) {
                    String input = sc.nextLine();
                    System.out.println("Error: \"" + input + "\" isn't a valid number.");
                } else {

                    int lengthOfSymbols = sc.nextInt();
                    sc.nextLine();

                    if (lengthOfSymbols > 36 || lengthOfCode > lengthOfSymbols) {
                        if (lengthOfCode > lengthOfSymbols) {
                            System.out.println("Error: it's not possible to generate a code with a length of " + lengthOfCode + " with " + lengthOfSymbols + " unique symbols.");
                        } else {
                            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                        }
                    } else {

                        if (lengthOfSymbols <= 10) {
                            SECRET_NUMBER = SecretCodeWithRandom.generateSecretCodeWithRandom(lengthOfCode, lengthOfSymbols);
                            MAX_ATTEMPTS = lengthOfCode;
                            System.out.println("The secret is prepared: " + "*".repeat(lengthOfCode) + " (0-" + (lengthOfSymbols - 1) + ").");
                        } else {
                            SECRET_NUMBER = SecretCode.generateSecretCode(lengthOfCode, lengthOfSymbols);
                            MAX_ATTEMPTS = lengthOfCode;
                            char s = (char) ((lengthOfSymbols - 11) + 'a');
                            System.out.println("The secret is prepared: " + "*".repeat(lengthOfCode) + " (0-9, a-" + s + ").");
                        }
                        System.out.println("Okay, let's start a game!");

                        do {
                            System.out.println("Turn " + ++turn + ":");
                            String input = sc.nextLine();
                            int bulls = 0;
                            int cows = 0;

                            for (int i = 0; i < MAX_ATTEMPTS; i++) {
                                for (int j = 0; j < MAX_ATTEMPTS; j++) {
                                    if (SECRET_NUMBER.charAt(i) == input.charAt(j)) {
                                        if (i == j) {
                                            ++bulls;
                                        } else {
                                            ++cows;
                                        }
                                    }
                                }
                            }

                            if (bulls == MAX_ATTEMPTS || bulls != 0 && cows == 0) {
                                System.out.println("Grade: " + bulls + " bull(s)");
                                if (bulls == MAX_ATTEMPTS) {
                                    System.out.println("Congratulations! You guessed the secret code.");
                                    trueValue = false;
                                }
                            } else if (cows == MAX_ATTEMPTS || bulls == 0 && cows != 0) {
                                System.out.println("Grade: " + cows + " cow(s)");
                            } else if (bulls != 0 && cows != 0) {
                                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
                            } else if (bulls == 0 && cows == 0) {
                                System.out.println("Grade: None");
                            }
                        } while (trueValue);
                    }
                }
            }
        }
    }
}

final class SecretCodeWithRandom {

    private static String newString = "";

    public static String generateSecretCodeWithRandom(int length, int lengthOfSymbols) {

        while (newString.length() < length) {
            int randomNumber = (int) (Math.random() * lengthOfSymbols);
            String randomNumberString = String.valueOf(randomNumber);
            if (newString.contains(randomNumberString)) {
                continue;
            } else {
                newString += (randomNumberString);
            }
        }

        return newString;
    }

    private SecretCodeWithRandom() {}
}

final class SecretCode {

    private static String newString = "";

    public static String generateSecretCode(int length, int lengthOfSymbols) {

        while (newString.length() < length) {
            Random random = new Random();

            int interval = random.nextInt(2);

            int randomNumber;
            if (interval == 0) {
                randomNumber = 48 + random.nextInt(10);
            } else {
                randomNumber = 97 + random.nextInt(lengthOfSymbols - 11);
            }

            char randomChar = (char) randomNumber;
            String randomCharString = String.valueOf(randomChar);

            if (newString.contains(randomCharString)) {
                continue;
            } else {
                newString += (randomCharString);
            }
        }

        return newString;
    }

    private SecretCode() {}
}
