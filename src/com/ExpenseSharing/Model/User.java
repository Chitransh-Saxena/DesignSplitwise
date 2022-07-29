package com.ExpenseSharing.Model;

import java.util.HashMap;
import java.util.Map;

public class User {

    String userId;

    // Get or Pay, based on -ve or +ve
    Map<User, Double> balance = new HashMap<>();

    public Map<User, Double> getBalance() {
        return balance;
    }

    public void setBalance(Map<User, Double> balance) {
        this.balance = balance;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
