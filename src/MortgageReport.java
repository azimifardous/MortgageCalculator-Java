import java.text.NumberFormat;

public class MortgageReport {
    private final NumberFormat currency;
    private final MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getNumberInstance();
    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        String mortgageFormat = currency.format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payment: $" + mortgageFormat);
    }
    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        for (double balance : calculator.getRemainingBalances())
            System.out.println("$" + currency.format(balance));
    }
}
