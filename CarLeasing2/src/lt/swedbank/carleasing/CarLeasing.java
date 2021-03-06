package lt.swedbank.carleasing;

public class CarLeasing {
    public static void main(String[] args) {
        double carPrice;
        int downPaymentSize;
        final double agreemetFee = 150;

        System.out.println("Input: " + "\n" + args[0] + " " + args[1]);
        System.out.println("Output: ");

        try {
            carPrice = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Car price including VAT is not valid " + e.getMessage());
            System.out.println("Terminating the program");
            return;
        }

        try {
            downPaymentSize = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Down payment size is not valid " + e.getMessage());
            System.out.println("Terminating the program");
            return;
        }

        System.out.println("Car price including VAT: " + Double.parseDouble(args[0]) + " EUR");
        System.out.println("Down payment size: " + args[1] + "%\n");


        boolean cPriceNegCheck = isCarPriceNotNegative(carPrice);
        boolean cPriceZeroCheck = isCarPriceNotZero(carPrice);
        boolean dPaySizeCheck = isDownPaymentSizeValid(downPaymentSize);

        if (dPaySizeCheck && cPriceNegCheck && cPriceZeroCheck) {
            double downPayment = calculateDownPayment(carPrice, downPaymentSize);
            System.out.println("Down payment: " + downPayment + " EUR");

            double loanSize = calculateLoanSize(carPrice, downPayment);
            System.out.println("Loan size: " + loanSize + " EUR\n");

            System.out.println("Agreement fee : " + agreemetFee + " EUR");

            double installment = calculateInitialInstalment(downPayment, agreemetFee);
            System.out.println("Initial installment: " + installment + " EUR");
        } else {
            System.out.println("Leasing parameters are invalid");
        }
    }


    public static double calculateDownPayment(double carPrice, int downPaymentSize) {
        return (carPrice * downPaymentSize) / 100;
    }

    public static double calculateLoanSize(double carPrcie, double downPayment) {
        return carPrcie - downPayment;
    }

    public static double calculateInitialInstalment(double downpayment, double agreementFee) {
        return downpayment + agreementFee;
    }


    public static boolean isCarPriceNotZero(double carPrice) {
        if (carPrice == 0) {
            priceZeroMessage(carPrice);
            return false;
        }

        return true;
    }

    public static boolean isCarPriceNotNegative(double carPrice) {
        if (carPrice < 0) {
            priceNegativeMessage(carPrice);
            return false;
        }
        return true;
    }

    public static boolean isDownPaymentSizeValid(double downPaymentSize) {
        if (downPaymentSize > 0 && downPaymentSize < 100) {
            return true;
        }
        downPaymentSizeMesaage(downPaymentSize);
        return false;
    }


    public static void priceZeroMessage(double carPrice) {
        System.out.println("Car price <" + carPrice + "> is invalid: it cannot be zero.");
    }

    public static void priceNegativeMessage(double carPrice) {
        System.out.println("Car price <" + carPrice + "> is invalid: it cannot be negative.");
    }

    public static void downPaymentSizeMesaage(double downpaymentSize) {
        System.out.println("Down payment size <" + downpaymentSize + "> it must be between 0 and 100..");
    }


}
