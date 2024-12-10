import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int quantityOfWater = 400;
        int quantityOfMilk = 540;
        int quantityOfCoffeeBeans = 120;
        int disposableCups = 9;
        int moneyOfMachine = 550;
        int cupsOfCoffee = 0;

        boolean isMachineOn = true;

        do {
            System.out.println("\nWrite action (buy, fill, take, clean, remaining, exit):");
            String action = sc.nextLine();
            System.out.println();

            switch (action) {
                case "buy":
                    if (cupsOfCoffee == 10) {
                        System.out.println("I need cleaning!");
                    } else {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        String coffeeOptionBack;
                        int coffeeOption;
                        if (!sc.hasNextInt()) {
                            coffeeOptionBack = sc.nextLine();
                            if (!coffeeOptionBack.equals("back")) {
                                System.out.println("Invalid option");
                            }
                            break;
                        } else {
                            coffeeOption = sc.nextInt();
                            sc.nextLine();
                            if (coffeeOption == 1) {
                                if (quantityOfWater < 250) {
                                    System.out.println("Sorry, not enough water!");
                                } else if (quantityOfCoffeeBeans < 16) {
                                    System.out.println("Sorry, not enough coffee beans!");
                                } else if (disposableCups <= 0) {
                                    System.out.println("Sorry, not enough disposable cups!");
                                } else {
                                    quantityOfWater -= 250;
                                    quantityOfCoffeeBeans -= 16;
                                    disposableCups -= 1;
                                    moneyOfMachine += 4;
                                    ++cupsOfCoffee;
                                    System.out.println("I have enough resources, making you a coffee!");
                                }
                            } else if (coffeeOption == 2) {
                                if (quantityOfWater < 350) {
                                    System.out.println("Sorry, not enough water!");
                                } else if (quantityOfMilk < 75) {
                                    System.out.println("Sorry, not enough milk!");
                                } else if (quantityOfCoffeeBeans < 20) {
                                    System.out.println("Sorry, not enough coffee beans!");
                                } else if (disposableCups <= 0) {
                                    System.out.println("Sorry, not enough disposable cups!");
                                } else {
                                    quantityOfWater -= 350;
                                    quantityOfMilk -= 75;
                                    quantityOfCoffeeBeans -= 20;
                                    disposableCups -= 1;
                                    moneyOfMachine += 7;
                                    ++cupsOfCoffee;
                                    System.out.println("I have enough resources, making you a coffee!");
                                }
                            } else if (coffeeOption == 3) {
                                if (quantityOfWater < 200) {
                                    System.out.println("Sorry, not enough water!");
                                } else if (quantityOfMilk < 100) {
                                    System.out.println("Sorry, not enough milk!");
                                } else if (quantityOfCoffeeBeans < 12) {
                                    System.out.println("Sorry, not enough coffee beans!");
                                } else if (disposableCups <= 0) {
                                    System.out.println("Sorry, not enough disposable cups!");
                                } else {
                                    quantityOfWater -= 200;
                                    quantityOfMilk -= 100;
                                    quantityOfCoffeeBeans -= 12;
                                    disposableCups -= 1;
                                    moneyOfMachine += 6;
                                    ++cupsOfCoffee;
                                    System.out.println("I have enough resources, making you a coffee!");
                                }
                            } else {
                                System.out.println("Invalid coffee option");
                            }
                        }
                    }
                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add:");
                    int addedWater = sc.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    int addedMilk = sc.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    int addedCoffeeBeans = sc.nextInt();
                    System.out.println("Write how many disposable cups you want to add:");
                    int addedCups = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    quantityOfWater += addedWater;
                    quantityOfMilk += addedMilk;
                    quantityOfCoffeeBeans += addedCoffeeBeans;
                    disposableCups += addedCups;
                    break;
                case "take":
                    System.out.println("I gave you $" + moneyOfMachine);
                    moneyOfMachine = 0;
                    break;
                case "remaining":
                    printStateOfMachine(quantityOfWater, quantityOfMilk, quantityOfCoffeeBeans, disposableCups, moneyOfMachine);
                    break;
                case "exit":
                    isMachineOn = false;
                    break;
                case "clean":
                    quantityOfWater = 400;
                    cupsOfCoffee = 0;
                    System.out.println("I have been cleaned!");
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        } while (isMachineOn);
    }

    public static void printStateOfMachine(int water, int milk, int coffeeBeans, int cups, int moneyOfMachine) {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + moneyOfMachine + " of money");
    }
}