package com.ExpenseSharing.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplit extends Split {

    private Map<User, Double> percentMap = new HashMap<>();

    public Map<User, Double> getPercentMap() {
        return percentMap;
    }

    public void setPercentMap(Map<User, Double> percentMap) {
        this.percentMap = percentMap;
    }

    public PercentSplit(User primary, List<User> splitters, Double amount) {
        super(primary, splitters, amount);
    }

    @Override
    public void updateBalances() {

        for(User user: splitters) {

            Double percent = percentMap.get(user);
            Double money = ( percent / 100) * amount;

            // Update this for both ower and lender
            // Update for lender
            Map<User, Double> primaryBalance = primary.getBalance();
            Double primaryMoney = primaryBalance.getOrDefault(user, 0.0);

            primaryMoney+=money;
            primaryBalance.put(user, primaryMoney);
            primary.setBalance(primaryBalance);

            // Ower update
            Map<User, Double> userBalance = user.getBalance();
            Double userMoney = userBalance.getOrDefault(user, 0.0);
            userMoney-=money;
            userBalance.put(primary, userMoney);

            user.setBalance(userBalance);
        }

    }
}
