package com.ExpenseSharing.Model;

import java.util.List;

public abstract class Split {

    User primary;
    List<User> splitters;
    Double amount;

    public Split(User primary, List<User> splitters, Double amount) {
        this.primary = primary;
        this.splitters = splitters;
        this.amount = amount;
    }

    abstract void updateBalances();
}
