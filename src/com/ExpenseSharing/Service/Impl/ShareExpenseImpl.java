package com.ExpenseSharing.Service.Impl;

import com.ExpenseSharing.Model.*;
import com.ExpenseSharing.Service.ShareExpense;

import java.util.List;
import java.util.Map;

public class ShareExpenseImpl implements ShareExpense {


    @Override
    public void addUser(User user, List<User> friends) {
        friends.add(user);
        System.out.println("Added User: " + user.getUserId() + " to the group");
    }

    @Override
    public void addExpense(User lender, List<User> owesMoney, Double amount, SplitType splitType, Map<User, Double> map) {

        if(splitType == SplitType.EQUAL) {
            EqualSplit split = new EqualSplit(lender, owesMoney, amount);
            split.updateBalances();
        }
        if(splitType == SplitType.EXACT) {

            // Prepare a Map of User, Expense
            ExactSplit exactSplit = new ExactSplit(lender, owesMoney, amount);

            // If it is exact split, ask for expenseMap from Dashboard or User.
            exactSplit.setExpenses(map);
            exactSplit.updateBalances();
        }

        if(splitType == SplitType.PERCENT) {
            PercentSplit split = new PercentSplit(lender, owesMoney, amount);
            split.setPercentMap(map);
            split.updateBalances();
        }

        System.out.println("Expenses have been updated");
    }

    @Override
    public void showBalances() {

        // Basic iteration of ever Users' balances Map.
    }

    @Override
    public void showBalanceForUser(User user) {

        System.out.println("Showing Balances for the Current User");
        Map<User, Double> myMap = user.getBalance();

        for(Map.Entry<User, Double> entry: myMap.entrySet()) {

            if(entry.getKey() != user) {
                if(entry.getValue() > 0.0) {
                    System.out.println(entry.getKey().getUserId() + " owes " + user.getUserId() + " an amount of " + Math.abs(entry.getValue()));

                }
                else {
                    System.out.println(entry.getKey().getUserId() + " gets from " + user.getUserId() + " an amount of " + Math.abs(entry.getValue()));
                }
            }

        }

    }
}
