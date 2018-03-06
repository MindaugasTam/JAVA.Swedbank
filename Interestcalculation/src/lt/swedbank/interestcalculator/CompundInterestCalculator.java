package lt.swedbank.interestcalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class CompundInterestCalculator {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amount;
        double interestRate;
        int periodLength;
        String compoundFreq;
        int frequency;
        double interest;
        double intermediateInterest;
        boolean check = true;


        Double[][] interestAmountArray;
        ArrayList<Double> interestRateArray = new ArrayList<>();


        System.out.print("Enter amount of money: ");
        amount = Integer.parseInt(scanner.nextLine());

        while (check) {
            System.out.print("Enter interest rate (%): ");
            interestRate = (Double.parseDouble(scanner.nextLine()) / 100);
            if (interestRate == 0) {
                check = false;
            } else {
                interestRateArray.add(interestRate);
            }
        }


        System.out.print("Enter period length: ");
        periodLength = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter compound frequency: ");
        compoundFreq = scanner.nextLine();

        frequency = findFrequency(compoundFreq);
        double intermediateAmount;
        interestAmountArray = new Double[interestRateArray.size()][periodLength * frequency];

        for (int i = 0; i < interestRateArray.size(); i++) {
            interest = 0;
            for (int j = 0; j < periodLength * frequency; j++) {

                intermediateAmount = interest + amount;
                interest = amount * Math.pow((1 + (interestRateArray.get(i) / frequency)), frequency * (j + 1)) - amount;
                intermediateInterest = intermediateAmount * Math.pow((1 + (interestRateArray.get(i) / frequency)), frequency) - intermediateAmount;
                interestAmountArray[i][j] = intermediateInterest;

            }
        }

        printArray(interestAmountArray);
    }

    private static int findFrequency(String compoundFreq) {
        int frequency;
        switch (compoundFreq) {
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

    private static void printArray(Double[][] array) {
        System.out.println();
        for (Double[] i : array) {
            for (double j : i) {
                System.out.printf("%.2f", j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
