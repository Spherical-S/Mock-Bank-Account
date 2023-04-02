//Import needed libraries
import java.io.*;
import java.util.*;

//Users accounts are created from this class
class UserAccount {

    final String SAVINGS = "1", CHEQUING = "2";
  
    String firstName;
    String lastName;
    String ID; //8 character ID to identify this account
    ArrayList<Bank> accounts = new ArrayList<Bank>(); //List of bank accounts owned by this user
    
    //class constructor either creates or loads the users account
    UserAccount(boolean signedUp, String userID, String first, String last){
      
        if (signedUp){
            login(userID);
        }else{
            signUp(first, last);
        }
      
    }// end of sign up constructor

    //initial set up of a new account
    public void signUp(String first, String last){
        this.firstName = first;
        this.lastName = last;
        this.ID = generateID();
    }

    //load an existing account
    public void login(String userID){
        try{
            String acctData;
            double savingBal;
            double chequingBal;
            String firstName;
            String lastName;

            ArrayList<String> accounts = new ArrayList<String>();
            ArrayList<String> acctDetails = new ArrayList<String>();

            File file = new File("accounts.txt");
            Scanner fr = new Scanner(file);

            File file2 = new File("acctDetails.txt");
            Scanner fr2 = new Scanner(file2);
            int i = 0;

            while(fr.hasNextLine()){
                accounts.add(fr.nextLine());
                if (accounts.get(i).equals(userID)){
                    break;
                }
                i++;
            }

            fr.close();
            
            while(fr2.hasNextLine()){
                acctDetails.add(fr2.nextLine());
            }

            fr2.close();

            acctData = acctDetails.get(i);

            savingBal = Double.valueOf(acctData.split(", ")[0]);
            chequingBal = Double.valueOf(acctData.split(", ")[1]);
            firstName = acctData.split(", ")[2];
            lastName = acctData.split(", ")[3];

            this.firstName = firstName;
            this.lastName = lastName;
            this.ID = userID;
            
            if (savingBal > -1){
                this.accounts.add(new Bank(userID, SAVINGS, true, savingBal));
            }

            if (chequingBal > -1){
                this.accounts.add(new Bank(userID, CHEQUING, true, chequingBal));
            }
        }catch(Exception e){}

    }
  
    //Shows users info
    public String[] showUserInfo(){
        String[] userDetails = new String[5];
        String savingsBal = "N/A";
        String chequingBal = "N/A";

        for (int i = 0; i<this.accounts.size(); i++){
            if (this.accounts.get(i).type == SAVINGS){
                savingsBal = String.valueOf(this.accounts.get(i).balance);
            }
            if (this.accounts.get(i).type == CHEQUING){
                chequingBal = String.valueOf(this.accounts.get(i).balance);
            }
        }
        
        userDetails[0] = this.ID;
        userDetails[1] = this.firstName;
        userDetails[2] = this.lastName;
        userDetails[3] = savingsBal;
        userDetails[4] = chequingBal;
  
        return userDetails;
    }// end of showUserInfo()

    //returns balance of banks in this account
    public double acctBalance(String type){
        int savingsIndex = -1;
        int chequingIndex = -1;
        
        for(int i = 0; i<this.accounts.size(); i++){
            if (this.accounts.get(i).type == SAVINGS){
                savingsIndex = i;
            }
            if (this.accounts.get(i).type == CHEQUING){
                chequingIndex = i;
            }
        }
        
        if (type == SAVINGS){
            if (savingsIndex != -1){
                return this.accounts.get(savingsIndex).balance;
            }else{
                return savingsIndex;
            }
        }

        if (type == CHEQUING){
            if (chequingIndex != -1){
                return this.accounts.get(chequingIndex).balance;
            }else{
                return chequingIndex;
            }
        }

        return -1;
    }
    
    // Generates a new ID for a user
    public String generateID(){
      
        String newID = "";
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray(); //all the possible characters
  
        //Add a random character from charArray to newID 8 times
        for(int i = 0; i<8; i++){
            newID += charArray[(int)(Math.random()*62)];
        }
      
        return newID; //Return
    }
    
  }// end of class UserAccount