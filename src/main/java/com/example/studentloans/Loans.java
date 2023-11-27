package com.example.studentloans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Loans {
    // -- Instance Variables
    private int loanID;
    private String loanName;
    private int loanPrincipal;
    private int loanPeriod;
    private double loanInterestRate;
    private double loanInterestToDate;
    private double interestSinceLastPayment;
    private String loanDisbursementDate;
    private double remainingBalance;
    private String dateOfLastPayment;
    private int amountPaid;


    // -- Constructors
    public Loans(int loanID, String loanName) {
        this.loanID = loanID;
        this.loanName = loanName;
    }

    public Loans(int loanID, String loanName, int loanPrincipal, double loanInterestRate, String loanDisbursementDate) {
        this.loanID = loanID;
        this.loanName = loanName;
        this.loanPrincipal = loanPrincipal;
        this.loanInterestRate = loanInterestRate;
        this.loanDisbursementDate = loanDisbursementDate;
    }
    public Loans(int loanID, String loanName, int loanPrincipal,int loanPeriod, double loanInterestRate, String loanDisbursementDate, String dateOfLastPayment, int amountPaid) {
        this.loanID = loanID;
        this.loanName = loanName;
        this.loanPrincipal = loanPrincipal;
        this.loanPeriod = loanPeriod;
        this.loanInterestRate = loanInterestRate;
        this.loanDisbursementDate = loanDisbursementDate;
        this.dateOfLastPayment = dateOfLastPayment;
        this.amountPaid = amountPaid;
    }

    // -- Getters
    public int getLoanID() {
        return loanID;
    }
    public String getLoanName() {
        return loanName;
    }
    public int getLoanPrincipal() {
        return loanPrincipal;
    }
    public double getLoanInterestRate() {
        return loanInterestRate;
    }
    public double getLoanInterestToDate() {
        return loanInterestToDate;
    }
    public String getLoanDisbursementDate() {
        return loanDisbursementDate;
    }
    public double getRemainingBalance() {
        return remainingBalance;
    }
    public String getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public double getInterestSinceLastPayment() {
        return interestSinceLastPayment;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    // -- Setters
    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }
    public void setLoanInterestToDate(double loanInterestToDate) {
        this.loanInterestToDate = loanInterestToDate;
    }
    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
    public void setDateOfLastPayment(String dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setLoanDisbursementDate(String loanDisbursementDate) {
        this.loanDisbursementDate = loanDisbursementDate;
    }

    public void setLoanInterestRate(double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public void setLoanPrincipal(int loanPrincipal) {
        this.loanPrincipal = loanPrincipal;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public void setInterestSinceLastPayment(double interestSinceLastPayment) {
        this.interestSinceLastPayment = interestSinceLastPayment;
    }

    // -- Method to calculate the number of days since last payment
    public long numOfDaysSinceLastPayment() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate lastPaymentDate = LocalDate.parse(dateOfLastPayment, dateFormat);
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(lastPaymentDate, currentDate);
    }

    public long numOfDaysSinceLoanStart() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate disbursementDate = LocalDate.parse(loanDisbursementDate, dateFormat);
        return ChronoUnit.DAYS.between(disbursementDate, currentDate);
    }

    public double calculateDailyInterest(double loanInterestRate, int loanPeriod) {
        return (loanInterestRate/loanPeriod) * getRemainingBalance();
    }

    public double calculateInterestSinceLastPayment(double loanInterestRate, double remainingBalance, int numOfDays) {
        /*
         * Simple daily interest formula:
         *
         * Interest Amount = (Outstanding Principal Balance x Interest Rate Factor) x Number of Days Since Last Payment
         */
        double interest = loanPrincipal * Math.pow(1 + (loanInterestRate/loanPeriod), loanPeriod * (numOfDaysSinceLastPayment()/(double)loanPeriod)) - loanPrincipal;
        BigDecimal calcInterest = new BigDecimal(interest).setScale(2, RoundingMode.HALF_UP);
        double remaining = calcInterest.doubleValue();
        setInterestSinceLastPayment(remaining);
        return remaining;
    }

    public double calculateRemainingBalance() {
        double rem = loanPrincipal + calculateLoansWithInterestToDate() - amountPaid;
        BigDecimal remBalance = new BigDecimal(rem).setScale(2, RoundingMode.HALF_UP);
        double remaining = remBalance.doubleValue();
        setRemainingBalance(remaining);
        return remaining;
    }

    // -- A = P(1+r/n)^nt

    //    A	= final amount
    //    P	= initial principal balance
    //    r	= interest rate
    //    n	= number of times interest applied per time period
    //    t	= number of time periods elapsed
    public double calculateLoansWithInterestToDate() {
        return loanPrincipal * Math.pow(1 + (loanInterestRate/loanPeriod), loanPeriod * (numOfDaysSinceLoanStart()/(double)loanPeriod)) - loanPrincipal;
    }
}
