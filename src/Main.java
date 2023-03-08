import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {
        int principal = (int) readNumber("Principal ($1K - $1M): ", 1000, 1000000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte periodInYears = (byte) readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterestRate, periodInYears);
        printPaymentSchedule(principal, annualInterestRate, periodInYears);
    }

    private static void printMortgage(int principal, float annualInterestRate, byte periodInYears) {
        double mortgage = calculateMortgage(principal, annualInterestRate, periodInYears);
        String mortgageFormat = NumberFormat.getNumberInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payment: $" + mortgageFormat);
    }

    private static void printPaymentSchedule(int principal, float annualInterestRate, byte periodInYears) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= periodInYears * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterestRate, periodInYears, month);
            System.out.println("$" + NumberFormat.getNumberInstance().format(balance));
        }
    }

    public static double calculateBalance(
            int principal,
            float annualInterestRate,
            byte periodInYears,
            short numberOfPaymentsMade
    ) {
        short numberOfPayments = (short) (periodInYears * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        return principal
                * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

    }
    
    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte periodInYears) {
        short numberOfPayments = (short) (periodInYears * MONTHS_IN_YEAR);
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        return principal * (monthlyInterestRate *
                Math.pow((1 + monthlyInterestRate), numberOfPayments)) /
                (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1);
    }

    public static double readNumber(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextDouble();
            if (value >= min && value <= max) break;
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
        return value;
    }
}