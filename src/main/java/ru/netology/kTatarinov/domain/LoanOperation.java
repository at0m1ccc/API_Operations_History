package ru.netology.kTatarinov.domain;

import java.util.Objects;

public class LoanOperation extends Operation {
    private int creditId;

    public int getLoanId() {
        return creditId;
    }

    public void setLoanId(int loanId) {
        this.creditId = loanId;
    }

    public LoanOperation(Integer id, Integer sum, Currency currency, String merchant, Integer customerId, int creditId) {
        super(id, sum, currency, merchant, customerId);
        this.creditId = creditId;
    }

    @Override
    public String toString() {
        return "Operation{ id = " + getId() +
                ", loandID: " + creditId +
                ", sum: " + getSum() +
                ", currency: " + getCurrency() +
                ", merchant: " + getMerchant() + "}";
    }

    @Override
    public boolean equals(Object action) {
        if (action == this) {
            return true;
        }

        if (action == null || action.getClass() != this.getClass()) {
            return false;
        }

        LoanOperation operation = (LoanOperation) action;
        return Objects.equals(getId(), operation.getId())
                && creditId == operation.creditId
                && (Objects.equals(getSum(), operation.getSum())
                && (getMerchant() != null && getMerchant().equals(operation.getMerchant()))
                && (getCurrency() != null && getCurrency().equals(operation.getCurrency())));
    }

    @Override
    public int hashCode() {
        final int prime = 35;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + getSum();
        result = prime * result + creditId;
        result = prime * result + ((getCurrency() != null) ? getCurrency().hashCode() : 0);
        result = prime * result + ((getCurrency() != null) ? getCurrency().hashCode() : 0);
        return result;
    }

    @Override
    public void printToConsole() {
        System.out.println("Твой id: " + getId() + "." +
                " Id совершённого займа: " + creditId + "." +
                " Сумма займа: " + getSum() +
                getCurrency() + "." +
                " Цель займа: " + getMerchant() + ".");
    }
}