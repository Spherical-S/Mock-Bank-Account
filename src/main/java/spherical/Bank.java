package spherical;

//Class bank is the class that creates bank accounts
class Bank {

    final String SAVING = "1";
    final String CHEQUING = "2";
  
    String holderID; //user id of the person that owns the account
    String type; //savings or chequing
    double balance = 0;
  
    //Class constructor walks user through process of creating an account
    // @ Param userID - the user who is creating the accounts ID
    // @ acctType - 1 for saving, 2 for chequing
    Bank(String userID, String acctType, boolean signedUp, double bal){

        if (signedUp){
            loadBank(userID, acctType, bal);
        }else{
            createBank(userID, acctType);
        }
  
    }// End constructor

    public void createBank(String userID, String acctType){
  
        //Set class variables
        this.holderID = userID;
        this.type = acctType;

    }

    public void loadBank(String userID, String acctType, double bal){
        this.holderID = userID;
        this.type = acctType;
        this.balance = bal;
    }
  
    //Verifies if the user accessing the account is the real user
    public boolean verifyAccess(String userID){
      return userID.equals(this.holderID);
    }
  
    //Deposit money
    public double deposit(String userID, double depositAmt){
        this.balance += depositAmt;
        return this.balance;
    }// End deposit()
  
    //withdraw money
    public double withdraw(String userID, double withdrawAmt){
        this.balance -= withdrawAmt;
        return this.balance;
    }// End withdraw()
  
    
  } // end bank class