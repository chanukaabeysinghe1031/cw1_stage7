import java.util.ArrayList;

class Transaction {
    public static void transferCurrency(ArrayList<BankAccount> loggedBankAccounts) {
        BankAccount sendBankAccount = null;
        BankAccount receiveBankAccount = null;
        int transferFromAccountNumber,
                transferToAccountNumber,
                transferCurrency;

        System.out.println("Please enter the bank account number you want to send from");
        System.out.print("Enter : ");
        transferFromAccountNumber = UserInterface.validateAccountNumber();
        System.out.println("Please enter the bank account number you want to send to");
        System.out.print("Enter : ");
        transferToAccountNumber = UserInterface.validateAccountNumber();

        //check the array list and get the bank account which want to send money
        for (int i = 0; i < loggedBankAccounts.size(); i++) {
            if (transferFromAccountNumber == loggedBankAccounts.get(i).getAccountNumber()) {
                sendBankAccount = loggedBankAccounts.get(i);
            }
        }
        //check the array list and get the bank account which is wanted to be received money
        for (int i = 0; i < loggedBankAccounts.size(); i++) {
            if (transferToAccountNumber == loggedBankAccounts.get(i).getAccountNumber()) {
                receiveBankAccount = loggedBankAccounts.get(i);
            }
        }

        char input;
        if (!(sendBankAccount == null && receiveBankAccount == null)) {
            System.out.println("===================================================================================================================");
            System.out.println("Please enter the amount of currency you want to transfer ");
            transferCurrency = UserInterface.validate();

            if (sendBankAccount.getAccountBalance() - transferCurrency < 10
                    && sendBankAccount.getAccountBalance() >= 0) {

                System.out.println("Please notice that tour balance "
                        + "will be reduce than 10. ");
                displayForecastAfterTransaction(sendBankAccount,receiveBankAccount,transferCurrency);
                System.out.println("To confirm the transfer, please enter [Y]es or any other character");
                input = UserInterface.userInput.next().charAt(0);

                if(input=='y'||input=='Y'){
                    doTransfer(sendBankAccount,receiveBankAccount,transferCurrency);
                    displayAccountBalance(sendBankAccount,receiveBankAccount,transferCurrency);

                    //To undo the transaction
                    System.out.println("If you want to undo the transaction please enter [y]es, or else any other character");
                    char input2=UserInterface.userInput.next().charAt(0);
                    if(input2=='y'||input2=='Y'){
                        rollBack(sendBankAccount,receiveBankAccount,transferCurrency);
                        displayAccountBalance(sendBankAccount,receiveBankAccount,transferCurrency);
                    }else {
                        System.out.println("Your transaction will not be done.");
                    }
                }

            } else if (!(sendBankAccount.getAccountBalance() - transferCurrency < 10
                    || receiveBankAccount.getAccountBalance() + transferCurrency > 100000)) {
                displayForecastAfterTransaction(sendBankAccount,receiveBankAccount,transferCurrency);

                System.out.println("To confirm the transfer, please enter [Y]es or any other character");
                input = UserInterface.userInput.next().charAt(0);
                if(input=='y'||input=='Y'){
                    doTransfer(sendBankAccount,receiveBankAccount,transferCurrency);
                    displayAccountBalance(sendBankAccount,receiveBankAccount,transferCurrency);

                    //To undo the transaction
                    System.out.println("If you want to undo the transaction please enter [y]es, or else any other character");
                    char input2=UserInterface.userInput.next().charAt(0);
                    if(input2=='y'||input2=='Y'){
                        rollBack(sendBankAccount,receiveBankAccount,transferCurrency);
                        displayAccountBalance(sendBankAccount,receiveBankAccount,transferCurrency);
                    }else {
                        System.out.println("Your transaction will not be done.");
                    }
                }

            } else {
                System.out.println("Sorry!! You can't proceed the transaction");
            }
        }
    }

    //This method is to display forecast of the account balances that will be after the transaction
    public static void displayForecastAfterTransaction(BankAccount sendBankAccount , BankAccount receiveBankAccount,double amount){
        System.out.println("===================================================================================================================");
        System.out.println("The balance of the bank account which send currency(" + sendBankAccount.getAccountNumber()+ ") will be  "
                + (sendBankAccount.getAccountBalance()-amount));
        System.out.println("The balance of the bank account which is  received currency("+receiveBankAccount.getAccountNumber()+") will be  "
                + (receiveBankAccount.getAccountBalance()+amount));
        System.out.println("===================================================================================================================");
    }

    //This method is to display account balances that will be after the transaction
    public static void displayAccountBalance(BankAccount sendBankAccount , BankAccount receiveBankAccount,double amount){
        System.out.println("===================================================================================================================");
        System.out.println("The balance of the bank account which send currency(" + sendBankAccount.getAccountNumber()+ ") is "
                + sendBankAccount.getAccountBalance());
        System.out.println("The balance of the bank account which is  received currency("+receiveBankAccount.getAccountNumber()+") is "
                + receiveBankAccount.getAccountBalance());
        System.out.println("===================================================================================================================");
    }

    //This method is to change the account balance after the
    public static void doTransfer(BankAccount sendBankAccount,BankAccount receivedBankAccount, double amount){
        sendBankAccount.setAccountBalance(sendBankAccount.getAccountBalance()-amount);
        receivedBankAccount.setAccountBalance(receivedBankAccount.getAccountBalance()+amount);
    }

    //This method is to undo the transaction
    public static void rollBack(BankAccount sendBankAccount,BankAccount receivedBankAccount, double amount) {
        sendBankAccount.setAccountBalance(sendBankAccount.getAccountBalance()+amount);
        receivedBankAccount.setAccountBalance(receivedBankAccount.getAccountBalance()-amount);
    }
}
