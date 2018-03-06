package lt.swedbank.interestcalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CompundInterestCalculator {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amount;
        double interestRate;
        int periodLength;
        String compundfreq;
        int frequency;
        double interest = 0;
        double interestAmounts;
        ArrayList<Double> interestAmountArray = new ArrayList<>();


        System.out.println("Enter amount of money: ");
        amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter interest rate: ");
        interestRate = (Double.parseDouble(scanner.nextLine()) / 100);

        System.out.println("Enter period length");
        periodLength = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter compound frequency");
        compundfreq = scanner.nextLine();

        double totalAmount = 0;
        frequency = findFrequency(compundfreq);


        double b;

        for (int i = 0; i < periodLength*frequency; i++) {
            double temp = 0;

            temp = interest + amount;

            interest = amount * Math.pow((1 + (interestRate / frequency)), frequency * (i + 1)) - amount;


            b = temp * Math.pow((1 + (interestRate / frequency)), frequency) - temp;


            interestAmountArray.add(b);
            totalAmount = interest;

            if(i % frequency == 0) {
                System.out.printf("Interest amount after year %d: %.2f\n", i, interest);
            }
        }
        System.out.printf("Total amount %.2f\n", totalAmount + amount);
        System.out.println(interestAmountArray.toString());
    }

    public static int findFrequency(String compundfreq) {
        int frequency = 0;
        switch (compundfreq) {
            case "D":
                frequency = 365;
                break;
            case "W":
                frequency = 52;
                break;
            case "M":
                frequency = 12;
                break;
            case "Q":
                frequency = 4;
                break;
            case "H":
                frequency = 2;
                break;
            case "Y":
                frequency = 1;
                break;
            default:
                frequency = 1;

        }
        return frequency;
    }
}
