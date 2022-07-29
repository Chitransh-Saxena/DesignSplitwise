package com.ExpenseSharing;

import com.ExpenseSharing.Model.Dashboard;
import com.ExpenseSharing.Model.Input;
import com.ExpenseSharing.Model.SplitType;
import com.ExpenseSharing.Model.User;
import com.ExpenseSharing.Service.Impl.ShareExpenseImpl;
import com.ExpenseSharing.Service.ShareExpense;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Load the Dashboard
        // Onboard Users

        User u1 = new User("u1");
        User u2 = new User("u2");
        User u3 = new User("u3");
        User u4 = new User("u4");

        List<User> others = new ArrayList<>();
        others.add(u2);
        others.add(u3);
        others.add(u4);

        Map<String, User> currentUser = new HashMap<>();
        currentUser.put(u1.getUserId(), u1);

        Map<String, User> friends = new HashMap<>();
        friends.put(u2.getUserId(), u2);
        friends.put(u3.getUserId(), u3);
        friends.put(u4.getUserId(), u4);

        Map<String, User> allUsers = new HashMap<>();
        allUsers.put(u1.getUserId(), u1);
        allUsers.put(u2.getUserId(), u2);;
        allUsers.put(u3.getUserId(), u3);
        allUsers.put(u4.getUserId(), u4);


        // Input parsing
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();

        String input1 = "u1 1000 4 u1 u2 u3 u4 EQUAL";
        String input2 = "u1 1250 2 u2 u3 EXACT 370 880";
        String input3 = "u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20";


        // Input will come in a standard pattern
        // primaryUpdater, amount, numberOfPeopleToSplitWith, ListOfPeopleToSplitWith, SplitType
        Input inputObj = prepareInput(input1);

        // Dashboard can take in the input, prepare the proper List of arguments and call the methods.
        Dashboard dashboard = new Dashboard(currentUser, friends);
        dashboard.addExpense(inputObj);
        dashboard.showExpenseForUser(allUsers.get("u1"));

        dashboard.addExpense(prepareInput(input2));
        dashboard.showExpenseForUser(allUsers.get("u1"));

        dashboard.addExpense(prepareInput(input3));
        dashboard.showExpenseForUser(allUsers.get("u1"));

    }

    public static Input prepareInput(String input) {

        String delimiter = "[ ]";
        String[] inputItems = input.split(delimiter);
        Input inputObj = new Input();
        inputObj.setAdderUserId(inputItems[0]);
        inputObj.setAmount(Double.parseDouble(inputItems[1]));
        inputObj.setNumOfPeopleToSplitWith(Integer.parseInt(inputItems[2]));

        List<String> splitterIds = new LinkedList<>();
        int i = 3;
        while(true) {

            if(inputItems[i].equals("EQUAL") || inputItems[i].equals("EXACT") || inputItems[i].equals("PERCENT") || inputItems.equals(" ")) {
                break;
            }
            splitterIds.add(inputItems[i]);
            i++;
        }

        inputObj.setSplittersUserId(splitterIds);
        SplitType splitType = SplitType.valueOf(inputItems[i++]);
        inputObj.setSplitType(splitType);

        List<Double> percentOrExact = new LinkedList<>();
        if(splitType != SplitType.EQUAL) {

            for(; i<inputItems.length; i++) {
                Double number = Double.parseDouble(inputItems[i]);
                percentOrExact.add(number);
            }

            inputObj.setPercentOrExact(percentOrExact);
        }

        return inputObj;
    }
}
