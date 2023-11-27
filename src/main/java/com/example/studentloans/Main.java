package com.example.studentloans;

public class Main {
    public static void main(String[] args) {
        Loans loan = new Loans(1, "Student Loan", 10000, 365, 0.05,"06/15/2021", "05/21/2023", 5000);

        // Get loan details
        System.out.println("Loan ID: " + loan.getLoanID());
        System.out.println("Loan Name: " + loan.getLoanName());
        System.out.println("Loan Principal: " + loan.getLoanPrincipal());
        System.out.println("Loan Interest Rate: " + loan.getLoanInterestRate());
        System.out.println("Loan Disbursement Date: " + loan.getLoanDisbursementDate());

        // Calculate the number of days since the last payment
        long daysSinceLastPayment = loan.numOfDaysSinceLastPayment();
        System.out.println("Days since last payment: " + daysSinceLastPayment);

        // Calculate daily interest and interest since last payment
        double dailyInterest = loan.calculateLoansWithInterestToDate();
        double interestSinceLastPayment = loan.calculateInterestSinceLastPayment(loan.getLoanInterestRate(), loan.getRemainingBalance(), (int) daysSinceLastPayment);

        System.out.println("Interest: " + dailyInterest + " over the course of " + loan.numOfDaysSinceLoanStart() + " days");
        System.out.println("Interest since last payment: " + interestSinceLastPayment);

        double remaining = loan.calculateRemainingBalance();
        System.out.println("Remaining: " + remaining);

        double rem = loan.getRemainingBalance();
        System.out.println("Rem: " + rem);

        double interest = loan.getInterestSinceLastPayment();
        System.out.println("Interest since last payment: " + interest);

    }
}