import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n");
        int numberOfRows = sc.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = sc.nextInt();

        System.out.println();

        boolean continueTheProgram = true;
        int rowNumber = 0;
        int seatNumber = 0;
        int purchasedTickets = 0;
        int incomePrice = 0;

        String[][] seats = new String[numberOfRows][numberOfSeats];

        do {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    printRoomWithSeatsChoices(seats, rowNumber, seatNumber, numberOfRows, numberOfSeats);
                    System.out.println();
                    break;
                case 2:
                    boolean continueSales = false;

                    do {
                        System.out.println("\nEnter a row number:");
                        rowNumber = sc.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        seatNumber = sc.nextInt();

                        if (rowNumber > numberOfRows || seatNumber > numberOfSeats || rowNumber <= 0 || seatNumber <= 0) {
                            System.out.println("\nWrong input!");
                            continueSales = true;
                        } else {
                            continueSales = verifyArray(seats, rowNumber, seatNumber, numberOfRows, numberOfSeats);
                            if (continueSales) {
                                System.out.println("That ticket has already been purchased!");
                            }
                        }
                    } while (continueSales);

                    int ticketPrice = getTicketPrice(seats, numberOfRows, numberOfSeats, rowNumber, seatNumber);
                    System.out.println("\nTicket price: $" + ticketPrice + "\n");
                    break;
                case 3:
                    getStatistics(seats, numberOfRows, numberOfSeats, purchasedTickets, incomePrice);
                    break;
                case 0:
                    continueTheProgram = false;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 3!");
                    break;
            }
        } while (continueTheProgram);

    }

    public static int getTicketPrice(String[][] seats, int numberOfRows, int numberOfSeats, int rowNumber, int seatNumber) {
        int totalNumberOfSeats = numberOfSeats * numberOfRows;
        int price;

        if (totalNumberOfSeats <= 60) {
            price = 10;
        } else {
            if (rowNumber <= (numberOfRows/2)) {
                price = 10;
            } else {
                price = 8;
            }
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if (i == rowNumber - 1 && j == seatNumber - 1) {
                    seats[i][j] = " B";
                } else {
                    if ((" B").equals(seats[i][j])) {
                        seats[i][j] = " B";
                    } else {
                        seats[i][j] = " S";
                    }
                }
            }
        }
        return price;
    }

    public static boolean verifyArray(String[][] seats, int rowNumber, int seatNumber, int numberOfRows, int numberOfSeats) {
        boolean ticketSold = false;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if (" B".equals(seats[rowNumber - 1][seatNumber - 1])) {
                    ticketSold = true;
                    break;
                }
            }
        }
        return ticketSold;
    }


    public static void printRoomWithSeatsChoices(String[][] seats, int rowNumber, int seatNumber, int numberOfRows, int numberOfSeats) {
        System.out.println("Cinema:");

        for (int i = 0; i <= numberOfRows; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(i);
            }
            for (int j = 0; j <= numberOfSeats; j++) {
                if (j == 0) {
                    System.out.print("");
                } else {
                    if (i == 0) {
                        System.out.print(" " + j);
                    } else {
                        if (i == rowNumber && j == seatNumber) {
                            seats[i - 1][j - 1] = " B";
                        } else {
                            if (Objects.equals(seats[i - 1][j - 1], " B")) {
                                seats[i - 1][j - 1] = " B";
                            } else {
                                seats[i - 1][j - 1] = " S";
                            }
                        }
                        System.out.print(seats[i - 1][j - 1]);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void getStatistics(String[][] seats, int numberOfRows, int numberOfSeats, int purchasedTickets, int incomePrice) {
        int totalIncome = calculateTheProfit(numberOfRows, numberOfSeats);

        int totalOfSeats = numberOfSeats * numberOfRows;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if (Objects.equals(seats[i][j], " B")) {
                    ++purchasedTickets;
                    if (totalOfSeats > 60) {
                        if (i < numberOfRows / 2) {
                            incomePrice += 10;
                        } else {
                            incomePrice += 8;
                        }
                    } else {
                        incomePrice += 10;
                    }
                }
            }
        }

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f", (double) purchasedTickets * 100 / totalOfSeats);
        System.out.println("%\nCurrent income: $" + incomePrice);
        System.out.println("Total income: $" + totalIncome + "\n");
    }

    public static int calculateTheProfit(int numberOfRows, int numberOfSeats) {
        int totalNumberOfSeats = numberOfSeats * numberOfRows;
        int price;

        if (totalNumberOfSeats <= 60) {
            price = totalNumberOfSeats * 10;
        } else {
            if (numberOfRows % 2 == 0) {
                price = (numberOfRows / 2) * numberOfSeats * 10 + (numberOfRows / 2) * numberOfSeats * 8;
            } else {
                price = (numberOfRows / 2) * numberOfSeats * 10 + ((numberOfRows / 2) + 1) * numberOfSeats * 8;
            }
        }
        return price;
    }
}