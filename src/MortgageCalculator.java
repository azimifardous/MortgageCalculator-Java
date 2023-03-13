public class MortgageCalculator {
    private final byte MONTHS_IN_YEAR = 12;
    private final int principal;
    private final float annualInterestRate;
    private final byte periodInYears;

    public MortgageCalculator(int principal, float annualInterestRate, byte periodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.periodInYears = periodInYears;
    }

    public double calculateBalance(
            short numberOfPaymentsMade
    ) {
        short numberOfPayments = (short) getNumberOfPayments();
        float monthlyInterestRate = getMonthlyInterestRate();

        return principal
                * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

    }

    public double calculateMortgage() {
        short numberOfPayments = (short) getNumberOfPayments();
        float monthlyInterestRate = getMonthlyInterestRate();

        return principal * (monthlyInterestRate *
                Math.pow((1 + monthlyInterestRate), numberOfPayments)) /
                (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1);
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month - 1] = calculateBalance(month);
        return balances;
    }

    private int getNumberOfPayments() {
        return periodInYears * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterestRate() {
        byte PERCENT = 100;
        return annualInterestRate / PERCENT / MONTHS_IN_YEAR;
    }
}
