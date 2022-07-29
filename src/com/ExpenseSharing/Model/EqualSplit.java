package com.ExpenseSharing.Model;

import java.util.List;
import java.util.Map;

public class EqualSplit extends Split {

    public EqualSplit(User primary, List<User> splitters, Double amount) {
        super(primary, splitters, amount);
    }

    @Override
    public void updateBalances() {

        int numOfPeople = this.splitters.size();
        Double perPersonShare = amount / numOfPeople;

        for(User user: splitters) {

            Map<User, Double> balanceMap = user.getBalance();
            Double userBalance = balanceMap.getOrDefault(primary, 0.0);
            userBalance-=perPersonShare;


            balanceMap.put(primary, userBalance);
            user.setBalance(balanceMap);

            Map<User, Double> primaryBalance = primary.getBalance();
            Double primaryAmount = primaryBalance.getOrDefault(user, 0.0);
            primaryAmount+=perPersonShare;
            primaryBalance.put(user, primaryAmount);
            primary.setBalance(primaryBalance);
        }
    }
}
