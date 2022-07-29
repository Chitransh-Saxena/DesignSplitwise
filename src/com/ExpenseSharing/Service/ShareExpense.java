package com.ExpenseSharing.Service;

import com.ExpenseSharing.Model.SplitType;
import com.ExpenseSharing.Model.User;
import java.util.List;
import java.util.Map;

public interface ShareExpense {

    void addUser(User user, List<User> friends);
    void addExpense(User lender, List<User> owesMoney, Double amount, SplitType splitType, Map<User, Double> map);
    void showBalances(); // TODO: Clarify this later
    void showBalanceForUser(User user);
}
