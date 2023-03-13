public class Main {
    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal ($1K - $1M): ", 1000, 1000000);
        float annualInterestRate = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte periodInYears = (byte) Console.readNumber("Period (Years): ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterestRate, periodInYears);

        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }

}