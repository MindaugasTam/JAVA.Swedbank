package lt.swedbank.interestcalculator;

import java.util.ArrayList;
import java.util.Scanner;

//Compund?
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
        //This is cheating! You should use arrays for this assignment only!
        //Another thing, use less specific types for collection variable declaration (ArrayList -> List)
        ArrayList<Double> interestRateArray = new ArrayList<>();


        System.out.print("Enter amount of money: ");
        amount = Integer.parseInt(scanner.nextLine());

        //You can change this "while (check) ..." into "do-while (true) ...", then additional "check" won't be needed.
        //Like this:
/*
        do {
            System.out.print("Enter interest rate (%): ");
            interestRate = (Double.parseDouble(scanner.nextLine()) / 100);
            if (interestRate > 0) {
                interestRateArray.add(interestRate);
            }
        } while (interestRate > 0);
*/

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
        //There is no need for this to be separated into a two lines
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
        //You don't need to save "switch" result into a separate variable, you can return it directly from "case: ".
        // In this way, "break" statements could be eliminated and "findFrequency" size would be cut in half :)
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

    //Method name is confusing. Technically "Double[][] array" is an array, but method name should reflect business
    // functionality it implements (ex.: printComparisonTable(...))
    private static void printArray(Double[][] array) {
        System.out.println();
        //Naming! "i -> row/tableRow" should work better
        for (Double[] i : array) {
            //Same here. "j -> number/interestAmount"
            for (double j : i) {
                System.out.printf("%.2f", j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
