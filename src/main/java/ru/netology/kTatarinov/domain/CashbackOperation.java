package ru.netology.kTatarinov.domain;

import java.util.Objects;

public class CashbackOperation extends Operation {
    private int cashbackSum;

    public int getCashbackSum() {
        return cashbackSum;
    }

    public void setCashbackSum(int cashbackSum) {
        this.cashbackSum = cashbackSum;
    }

    public CashbackOperation(Integer id, Integer sum, Currency currency, String merchant, Integer customerId, int cashbackSum) {
        super(id, sum, currency, merchant, customerId);
        this.cashbackSum = cashbackSum;
    }

    @Override
    public String toString() {
        return "Operation{ id = " + getId() +
                ", sum: " + getSum() +
                ", currency: " + getCurrency() +
                ", merchant: " + getMerchant() +
                ", cashbackSum " + cashbackSum + "}";
    }

    @Override
    public boolean equals(Object action) {
        if (action == this) {
            return true;
        }

        if (action == null || action.getClass() != this.getClass()) {
            return false;
        }


        CashbackOperation operation = (CashbackOperation) action;
        return Objects.equals(getId(), operation.getId())
                && (Objects.equals(getSum(), operation.getSum())
                && cashbackSum == operation.cashbackSum
                && (getCurrency() != null && getCurrency().equals(operation.getCurrency()))
                && (getMerchant() != null && getMerchant().equals(operation.getMerchant())));
    }

    @Override
    public int hashCode() {
        final int prime = 35;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + getSum();
        result = prime * result + cashbackSum;
        result = prime * result + ((getCurrency() != null) ? getCurrency().hashCode() : 0);
        result = prime * result + ((getCurrency() != null) ? getCurrency().hashCode() : 0);
        return result;
    }

    @Override
    public void printToConsole() {
        System.out.println("Твой id: " + getId() + "." +
                " Сумма банковской операции, которую ты совершил: " + getSum() +
                getCurrency() + "." +
                " Оператор, на которого была совершена операция: " + getMerchant() + "." +
                " Сумма полученного кэшбэка: " + cashbackSum + ".");
    }
}