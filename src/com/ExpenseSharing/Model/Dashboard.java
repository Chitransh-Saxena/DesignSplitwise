package com.ExpenseSharing.Model;

import com.ExpenseSharing.Model.User;
import com.ExpenseSharing.Service.Impl.ShareExpenseImpl;
import com.ExpenseSharing.Service.ShareExpense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {

    Map<String, User> currentUser;
    Map<String, User> friends;
    Map<User, Double> percentOrExactMap;
    Map<String, User> allUsers = new HashMap<>();
    ShareExpense shareExpense = new ShareExpenseImpl();


    public Map<User, Double> getPercentOrExactMap() {
        return percentOrExactMap;
    }

    public void setPercentOrExactMap(Map<User, Double> percentOrExactMap) {
        this.percentOrExactMap = percentOrExactMap;
    }

    public Map<String, User> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Map<String, User> currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
    }

    public Dashboard(Map<String, User> currentUser, Map<String, User> friends) {
        this.currentUser = currentUser;
        this.friends = friends;
        allUsers.putAll(currentUser);
        allUsers.putAll(friends);
    }


    public void addExpense(Input input) {

        // Here we do the input parsing
        User lender = allUsers.get(input.getAdderUserId());
        List<User> owers = new ArrayList<>();

        for(String string: input.getSplittersUserId()) {
            owers.add(allUsers.get(string));
        }

        Double amount = input.getAmount();
        SplitType splitType = input.getSplitType();

        Map<User, Double> map = new HashMap<>();
        if(splitType != SplitType.EQUAL) {

            int n = input.getSplittersUserId().size();
            int m = input.getPercentOrExact().size();

            if(n != m) {
                System.out.println("Something seems wrong with the input, please try again !!!");
                System.exit(-1);
            }

            List<Double> percentOrExact = input.getPercentOrExact();

            for(int i = 0; i<n; i++) {

                User user = owers.get(i);
                Double num = percentOrExact.get(i);

                map.put(user, num);
            }

        }



        shareExpense.addExpense(lender, owers, amount, splitType, map);
    }

    public void showExpenseForUser(User user) {
        shareExpense.showBalanceForUser(allUsers.get("u1"));
    }

    public void addUser(User user, List<User> friends) {
        shareExpense.addUser(user, friends);
    }

}
