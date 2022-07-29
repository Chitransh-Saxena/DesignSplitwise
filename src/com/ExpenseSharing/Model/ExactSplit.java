package com.ExpenseSharing.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplit extends Split {

    Map<User, Double> expenses = new HashMap<>();

    public ExactSplit(User primary, List<User> splitters, Double amount) {
        super(primary, splitters, amount);
    }

    public Map<User, Double> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<User, Double> expenses) {
        this.expenses = expenses;
    }

    @Override
    public void updateBalances() {

       for(User user: splitters) {

           // This will ggive the expense that has to be added
           Map<User, Double> baalnces = user.getBalance();
           Double money = baalnces.getOrDefault(primary, 0.0);

           money-=expenses.getOrDefault(user, 0.0);
           baalnces.put(primary, money);
           // User owes this money to primary
           // Update this in splitters as well as primary's profile
           user.setBalance(baalnces);

           // Could have used a MQ or observer kind of pattern here, so that we could update Primary when secondary was being updated.
           Map<User, Double> primaryBalance = primary.getBalance();
           Double moneyPrimary = primaryBalance.getOrDefault(user, 0.0);
           moneyPrimary+=expenses.get(user);

           primaryBalance.put(user, moneyPrimary);
           primary.setBalance(primaryBalance);
       }
    }
}
