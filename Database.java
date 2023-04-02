import java.io.*;
import java.util.*;

class Database {
    //saves a users details to accounts.txt and acctDetails.txt
    public static void save(UserAccount user){
        try{

            ArrayList<String> accounts = userList();
            ArrayList<String> acctDetails = userDetails();

            if (accounts.contains(user.ID) == false){
                saveNewAccount(user);
            }else{
                saveAccount(user, accounts, acctDetails);
            }


        }catch(Exception e){}

    }

    //saves an existing account
    public static void saveAccount(UserAccount user, ArrayList<String> accounts, ArrayList<String> acctDetails){
        try{
            FileWriter fw = new FileWriter("acctDetails.txt");
            String userDetails = String.valueOf(user.acctBalance("1")) + ", " + String.valueOf(user.acctBalance("2")) + ", " + user.firstName + ", " + user.lastName;
            String allUserDetails = "";
            int userIndex = -1;

            for (int i = 0; i<accounts.size(); i++){
                if (accounts.get(i).equals(user.ID)){
                    userIndex = i;
                    break;
                }
            }


            for (int i = 0; i<acctDetails.size(); i++){
                if (i == userIndex){
                    allUserDetails += userDetails;
                }else{
                    allUserDetails += acctDetails.get(i);
                }

                if (i+1 != acctDetails.size()){
                    allUserDetails += "\n";
                }
            }

            fw.append(allUserDetails);
            fw.close();

        }catch(Exception e){}
    }

    //adds a new account to the save files
    public static void saveNewAccount(UserAccount user){
        try{
            FileWriter fw = new FileWriter("accounts.txt", true);
            FileWriter fw2 = new FileWriter("acctDetails.txt", true);

            String acctDetails = String.valueOf(user.acctBalance("1")) + ", " + String.valueOf(user.acctBalance("2")) + ", " + user.firstName + ", " + user.lastName;

            fw.append("\n" + user.ID);
            fw2.append("\n" + acctDetails);

            fw.close();
            fw2.close();
        }catch(Exception e){}
    }

    //returns an array list which has every saved acccount id
    public static ArrayList<String> userList(){
        ArrayList<String> accounts = new ArrayList<String>();
        try{
            File file = new File("accounts.txt");
            Scanner fr = new Scanner(file);

            while(fr.hasNextLine()){
                accounts.add(fr.nextLine());
            }

            fr.close();
        }catch(Exception e){}

        return accounts;
    }

    //returns an array list which has every saved accounts details
    public static ArrayList<String> userDetails(){
        ArrayList<String> accounts = new ArrayList<String>();
        try{
            File file = new File("acctDetails.txt");
            Scanner fr = new Scanner(file);

            while(fr.hasNextLine()){
                accounts.add(fr.nextLine());
            }

            fr.close();
        }catch(Exception e){}
        
        return accounts;
    }
}
