* Expense Sharing Application - Expenses can be added to some dashboard and be split among people in some custom way

* Requirements
    * Should be able to add users
    * Users should be able to upload some amount, and split it among people
    * For now, assumption is there do we need some admin, or anyone can add anyone into some sort of group?
        * Considering, at the start of program, we will onboard few Users, and that will be our group of people
    
* Sequence
    * Onboard Users
        * For our test case, onboard 4 users
    * Input parsing
        * Which user pays
        * How is it split
            * Abstract Factory pattern here
        * Amongst how many is it split
        * Update the balances of all the users.
    * APIs
        * addUser()
        * addExpense()
        * showBalances()
        * showBalanceForUser()
    

* Development
    * Creation
      * For creation, going to use basic POJO creation, nothing fancy so far.
    
    * In main function
        * Onboard the users
        * Now, in this onboarding, we have currentOwner and others
        * Based on the input that we have, update the metrics of balances
        "# DesignSplitwise" 
