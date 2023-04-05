package spherical;

//import needed libraries
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

class MyFrame extends JFrame implements ActionListener{

    final Font FONT = new Font("Arial", Font.PLAIN, 25);

    //the 2 panels that will change throughout the program
    MyPanel interaction;
    MyPanel navigation;

    //variables that will be used throughout the program
    ArrayList<String> accounts = new ArrayList<String>();
    UserAccount user;

    //variables for login screen
    JButton login;
    JButton signup;
    JTextField loginField;
    JLabel errorLabel;

    //variables for the sign up screen
    JButton confirmSignUp;
    JTextField firstEntry;
    JTextField lastEntry;

    //variables for the navigation panel
    JButton acctDetails;
    JButton newAcct;
    JButton deposit;
    JButton withdraw;
    JButton logout;

    //variables for the change name screen
    JButton editName;
    JButton saveName;
    JTextField newFirst;
    JTextField newLast;

    //variables for the open new account page
    JButton openSavings;
    JButton openChequing;

    //variables for the deposit page
    JButton depositToSavings;
    JButton depositToChequing;
    JButton confirmDeposit;
    JTextField depositAmt;
    JLabel depositMessage;
    String depositAccount;

    //variables for the withdraw page
    JButton withdrawFromSavings;
    JButton withdrawFromChequing;
    JButton confirmWithdraw;
    JTextField withdrawAmt;
    JLabel withdrawMessage;
    String withdrawAccount;

    //class contructor creates the main window
    MyFrame(){

        getAccounts(); //load all the accounts from the saved files

        //set main settings for the window
        this.setTitle("Bank of Carreiro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        //set up the header
        MyPanel header = new MyPanel(100, 700, Color.GRAY);
        header.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        JLabel title = new JLabel("Bank of Carreiro");
        title.setFont(new Font("Cinzel Black", Font.PLAIN, 50));
        header.add(title);

        //set up the navigation and interaction panels
        navigation = new MyPanel(600, 200, Color.GRAY);
        navigation.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        navigation.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        interaction = new MyPanel(600, 700, Color.GRAY);
        interaction.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        //add the panels to the window
        this.add(header, BorderLayout.NORTH);
        this.add(navigation, BorderLayout.WEST);
        this.add(interaction, BorderLayout.CENTER);


        displayLogin(); //displays the login screen

        this.setVisible(true); //set the window to visible

    }

    //next 12 methods simply display their respective page
    public void displayLogin(){

        //clear the navigation bar
        navigation.removeAll();
        navigation.revalidate();
        navigation.repaint();

        //set up the components for the login screen
        JLabel text = new JLabel("Enter User-ID");
        text.setFont(FONT);

        JLabel signUpLabel = new JLabel("Dont have an account? Sign up instead!");
        signUpLabel.setFont(FONT);

        loginField = new JTextField();
        loginField.setFont(FONT);
        loginField.setPreferredSize(new Dimension(300, 30));

        login = new JButton("Log in");
        login.setFont(FONT);
        login.addActionListener(this);

        signup = new JButton("Sign up");
        signup.setFont(FONT);
        signup.addActionListener(this);

        errorLabel = new JLabel("");
        errorLabel.setFont(FONT);
        errorLabel.setForeground(Color.RED);

        //add the componenets to the interaction panel
        interaction.add(text);
        interaction.add(loginField);
        interaction.add(login);
        interaction.add(signUpLabel);
        interaction.add(signup);
        interaction.add(errorLabel);

        //update the interactions panel
        interaction.revalidate();
        interaction.repaint();

    }

    public void displaySignUp(){
        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Create New Account");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel entryLabelsPanel = new MyPanel(50, 690, Color.GRAY);
        entryLabelsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));

        JLabel firstLabel = new JLabel("Enter First Name:              ");
        JLabel lastLabel = new JLabel("Enter Last Name:");
        firstLabel.setFont(FONT);
        lastLabel.setFont(FONT);
        entryLabelsPanel.add(firstLabel);
        entryLabelsPanel.add(lastLabel);

        MyPanel entrysPanel = new MyPanel(50, 690, Color.GRAY);
        entrysPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        firstEntry = new JTextField();
        firstEntry.setFont(FONT);
        firstEntry.setPreferredSize(new Dimension(300, 30));

        lastEntry = new JTextField();
        lastEntry.setFont(FONT);
        lastEntry.setPreferredSize(new Dimension(300, 30));

        entrysPanel.add(firstEntry);
        entrysPanel.add(lastEntry);

        confirmSignUp = new JButton("Sign Up");
        confirmSignUp.setFont(FONT);
        confirmSignUp.addActionListener(this);

        interaction.add(topPanel);
        interaction.add(entryLabelsPanel);
        interaction.add(entrysPanel);
        interaction.add(confirmSignUp);

        interaction.revalidate();
        interaction.repaint();
    }

    public void displayHomeScreen(){

        //set up the welcome message for the home screen
        MyPanel titlePanel = new MyPanel(50, 690, Color.GRAY);

        JLabel welcome = new JLabel("Welcome!");
        welcome.setFont(new Font("Arial", Font.PLAIN, 40));
        
        JLabel name = new JLabel(user.lastName + ", " + user.firstName);
        name.setFont(FONT);

        //add the welcome and name label to the interaction panel
        titlePanel.add(welcome);
        interaction.add(titlePanel);
        interaction.add(name);

        //set up the navigation bar buttons
        acctDetails = new JButton("Account Details");
        acctDetails.setFont(new Font("Arial", Font.PLAIN, 20));
        newAcct = new JButton(" Open Account ");
        newAcct.setFont(new Font("Arial", Font.PLAIN, 20));
        deposit = new JButton("      Deposit      ");
        deposit.setFont(new Font("Arial", Font.PLAIN, 20));
        withdraw = new JButton("     Withdraw    ");
        withdraw.setFont(new Font("Arial", Font.PLAIN, 20));
        logout = new JButton("      Logout       ");
        logout.setFont(new Font("Arial", Font.PLAIN, 20));
        
        //add action listeners for each navigation button
        acctDetails.addActionListener(this);
        newAcct.addActionListener(this);
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        logout.addActionListener(this);
        
        //add each button to the navigation bar
        navigation.add(acctDetails);
        navigation.add(newAcct);
        navigation.add(deposit);
        navigation.add(withdraw);
        navigation.add(logout);

        //refresh both navigation and interaction bar
        navigation.revalidate();
        navigation.repaint();

        interaction.revalidate();
        interaction.repaint();
    }

    public void displayAccountDetails(){
        String[] userDetails = user.showUserInfo(); //get the users details

        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Account Details");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel centerPanel = new MyPanel(430, 690, Color.GRAY);
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel iDLabel = new JLabel("User ID: "+userDetails[0]);
        JLabel name = new JLabel("Name: "+userDetails[2]+", "+userDetails[1]);
        JLabel savingsID = new JLabel("Savings Balance: "+userDetails[3]);
        JLabel chequingID = new JLabel("Chequing Balance: "+userDetails[4]);

        iDLabel.setFont(FONT);
        name.setFont(FONT);
        savingsID.setFont(FONT);
        chequingID.setFont(FONT);

        centerPanel.add(iDLabel);
        centerPanel.add(new MyPanel(10, 600, Color.GRAY));
        centerPanel.add(name);
        centerPanel.add(new MyPanel(10, 600, Color.GRAY));
        centerPanel.add(savingsID);
        centerPanel.add(new MyPanel(10, 600, Color.GRAY));
        centerPanel.add(chequingID);
        centerPanel.add(new MyPanel(10, 600, Color.GRAY));

        MyPanel bottomPanel = new MyPanel(50, 700, Color.GRAY);

        editName = new JButton("Edit Name");
        editName.setFont(FONT);
        editName.addActionListener(this);

        bottomPanel.add(editName);

        interaction.add(topPanel);
        interaction.add(centerPanel);
        interaction.add(bottomPanel);

        interaction.revalidate();
        interaction.repaint();

    }

    public void displayEditName(){
        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Edit Account Name");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel currentNamePanel = new MyPanel(50, 690, Color.GRAY);
        currentNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));

        JLabel currentName = new JLabel("Current Name: "+user.firstName + " "+ user.lastName);
        currentName.setFont(FONT);
        currentNamePanel.add(currentName);

        MyPanel entryLabelsPanel = new MyPanel(50, 690, Color.GRAY);
        entryLabelsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));

        JLabel newFirstLabel = new JLabel("New First Name:                ");
        JLabel newLastLabel = new JLabel("New Last Name:");
        newFirstLabel.setFont(FONT);
        newLastLabel.setFont(FONT);
        entryLabelsPanel.add(newFirstLabel);
        entryLabelsPanel.add(newLastLabel);

        MyPanel entrysPanel = new MyPanel(50, 690, Color.GRAY);
        entrysPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        newFirst = new JTextField();
        newFirst.setFont(FONT);
        newFirst.setPreferredSize(new Dimension(300, 30));
        newFirst.setText(user.firstName);

        newLast = new JTextField();
        newLast.setFont(FONT);
        newLast.setPreferredSize(new Dimension(300, 30));
        newLast.setText(user.lastName);

        entrysPanel.add(newFirst);
        entrysPanel.add(newLast);

        saveName = new JButton("Save");
        saveName.setFont(FONT);
        saveName.addActionListener(this);

        interaction.add(topPanel);
        interaction.add(currentNamePanel);
        interaction.add(entryLabelsPanel);
        interaction.add(entrysPanel);
        interaction.add(saveName);

        interaction.revalidate();
        interaction.repaint();

    }

    public void displayOpenNewAccount(){
        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Open New Account");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel centerPanel = new MyPanel(450, 690, Color.GRAY);
        if (user.accounts.size() == 2){
            JLabel message = new JLabel("You already own the maximum amount of accounts.");
            message.setFont(FONT);

            centerPanel.add(message);

            interaction.add(topPanel);
            interaction.add(centerPanel);

            interaction.revalidate();
            interaction.repaint();
        }else if(user.accounts.size() == 0){
            JLabel message = new JLabel("Select account type:");
            message.setFont(FONT);

            openSavings = new JButton("Savings");
            openSavings.setFont(FONT);
            openSavings.addActionListener(this);

            openChequing = new JButton("Chequing");
            openChequing.setFont(FONT);
            openChequing.addActionListener(this);

            centerPanel.add(message);
            centerPanel.add(new MyPanel(10, 600, Color.GRAY));
            centerPanel.add(openSavings);
            centerPanel.add(openChequing);

            interaction.add(topPanel);
            interaction.add(centerPanel);

            interaction.revalidate();
            interaction.repaint();
        }else if(user.accounts.get(0).type == "2"){
            interaction.removeAll();
            displayOpenedSavings();
        }else if(user.accounts.get(0).type == "1"){
            interaction.removeAll();
            displayOpenedChequing();
        }

    }

    public void displayOpenedSavings(){
        user.accounts.add(new Bank(user.ID, "1", false, 0));
        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Open New Account");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel centerPanel = new MyPanel(450, 690, Color.GRAY);
        JLabel message = new JLabel("Succesfully opened new Savings account!");
        message.setFont(FONT);

        centerPanel.add(message);

        interaction.add(topPanel);
        interaction.add(centerPanel);

        interaction.revalidate();
        interaction.repaint();
    }

    public void displayOpenedChequing(){
        user.accounts.add(new Bank(user.ID, "2", false, 0));
        MyPanel topPanel = new MyPanel(50, 690, Color.GRAY);

        JLabel title = new JLabel("Open New Account");
        title.setFont(FONT);

        topPanel.add(title);

        MyPanel centerPanel = new MyPanel(450, 690, Color.GRAY);
        JLabel message = new JLabel("Succesfully opened new Chequing account!");
        message.setFont(FONT);

        centerPanel.add(message);

        interaction.add(topPanel);
        interaction.add(centerPanel);

        interaction.revalidate();
        interaction.repaint();
    }

    //Cut deposit in 2 halves so that we can add onto the panel once the user selects the account they with to deposit to
    public void displayDepositTop(){
        MyPanel topPanel = new MyPanel(150, 690, Color.GRAY);

        JLabel title = new JLabel("Deposit");
        title.setFont(FONT);
        topPanel.add(title);
        topPanel.add(new MyPanel(10, 600, Color.GRAY));

        JLabel select = new JLabel("Select account to deposit to:");
        select.setFont(FONT);
        topPanel.add(select);
        topPanel.add(new MyPanel(10, 600, Color.GRAY));

        if (user.accounts.size() == 2){
            depositToSavings = new JButton("Savings");
            depositToSavings.setFont(FONT);
            depositToSavings.addActionListener(this);

            depositToChequing = new JButton("Chequing");
            depositToChequing.setFont(FONT);
            depositToChequing.addActionListener(this);

            topPanel.add(depositToSavings);
            topPanel.add(depositToChequing);
        }else if(user.accounts.size()==0){
            JLabel noAccounts = new JLabel("No accounts to deposit to. Open an account instead!");
            noAccounts.setFont(FONT);

            topPanel.add(noAccounts);
        }else if(user.accounts.get(0).type == "1"){
            depositToSavings = new JButton("Savings");
            depositToSavings.setFont(FONT);
            depositToSavings.addActionListener(this);

            topPanel.add(depositToSavings);
        }else if(user.accounts.get(0).type == "2"){
            depositToChequing = new JButton("Chequing");
            depositToChequing.setFont(FONT);
            depositToChequing.addActionListener(this);

            topPanel.add(depositToChequing);
        }

        interaction.add(topPanel);

        interaction.revalidate();
        interaction.repaint();

    }

    public void displayDepositBottom(String accountType){
        interaction.removeAll();
        displayDepositTop();

        MyPanel bottomPanel = new MyPanel(350, 690, Color.GRAY);
        
        JLabel depositAmountLabel = new JLabel("Deposit amount ("+ accountType+"):");
        depositAmountLabel.setFont(FONT);
        bottomPanel.add(depositAmountLabel);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        depositAmt = new JTextField();
        depositAmt.setFont(FONT);
        depositAmt.setPreferredSize(new Dimension(300, 30));
        bottomPanel.add(depositAmt);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        confirmDeposit = new JButton("Confirm");
        confirmDeposit.setFont(FONT);
        confirmDeposit.addActionListener(this);
        bottomPanel.add(confirmDeposit);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        depositMessage = new JLabel("");
        depositMessage.setFont(FONT);
        bottomPanel.add(depositMessage);

        interaction.add(bottomPanel);

        interaction.revalidate();
        interaction.repaint();
    }

    //Cut withdraw in 2 halves so that we can add onto the panel once the user selects the account they with to withdraw from
    public void displayWithdrawTop(){
        MyPanel topPanel = new MyPanel(150, 690, Color.GRAY);

        JLabel title = new JLabel("Withdraw");
        title.setFont(FONT);
        topPanel.add(title);
        topPanel.add(new MyPanel(10, 600, Color.GRAY));

        JLabel select = new JLabel("Select account to withdraw from:");
        select.setFont(FONT);
        topPanel.add(select);
        topPanel.add(new MyPanel(10, 600, Color.GRAY));

        if (user.accounts.size() == 2){
            withdrawFromSavings = new JButton("Savings");
            withdrawFromSavings.setFont(FONT);
            withdrawFromSavings.addActionListener(this);

            withdrawFromChequing = new JButton("Chequing");
            withdrawFromChequing.setFont(FONT);
            withdrawFromChequing.addActionListener(this);

            topPanel.add(withdrawFromSavings);
            topPanel.add(withdrawFromChequing);
        }else if(user.accounts.size()==0){
            JLabel noAccounts = new JLabel("No accounts to withdraw from. Open an account instead!");
            noAccounts.setFont(FONT);

            topPanel.add(noAccounts);
        }else if(user.accounts.get(0).type == "1"){
            withdrawFromSavings = new JButton("Savings");
            withdrawFromSavings.setFont(FONT);
            withdrawFromSavings.addActionListener(this);

            topPanel.add(withdrawFromSavings);
        }else if(user.accounts.get(0).type == "2"){
            withdrawFromChequing = new JButton("Chequing");
            withdrawFromChequing.setFont(FONT);
            withdrawFromChequing.addActionListener(this);

            topPanel.add(withdrawFromChequing);
        }

        interaction.add(topPanel);

        interaction.revalidate();
        interaction.repaint();
    }

    public void displayWithdrawBottom(String accountType){
		interaction.removeAll();
        displayWithdrawTop();

        MyPanel bottomPanel = new MyPanel(350, 690, Color.GRAY);
        
        JLabel withdrawAmountLabel = new JLabel("Withdraw amount ("+ accountType+"):");
        withdrawAmountLabel.setFont(FONT);
        bottomPanel.add(withdrawAmountLabel);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        withdrawAmt = new JTextField();
        withdrawAmt.setFont(FONT);
        withdrawAmt.setPreferredSize(new Dimension(300, 30));
        bottomPanel.add(withdrawAmt);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        confirmWithdraw = new JButton("Confirm");
        confirmWithdraw.setFont(FONT);
        confirmWithdraw.addActionListener(this);
        bottomPanel.add(confirmWithdraw);
        bottomPanel.add(new MyPanel(10, 600, Color.GRAY));

        withdrawMessage = new JLabel("");
        withdrawMessage.setFont(FONT);
        bottomPanel.add(withdrawMessage);

        interaction.add(bottomPanel);

        interaction.revalidate();
        interaction.repaint();
    }

    //adds balance to an account
    // param - entry: the user input for the amount they want to add, passed in automatically
    public void addBalance(String entry){
        double amount;
        int savingsIndex = -1;
        int chequingIndex = -1;

        try{
            amount = Double.valueOf(entry);
        }catch(Exception e){
            depositMessage.setText("Invalid input. Please enter a valid number.");
            return;
        }

        if (amount < 1){
            depositMessage.setText("Enter a value larger than 0.");
            return;
        }

        for (int i = 0; i<user.accounts.size(); i++){
            if (user.accounts.get(i).type == "1"){
                savingsIndex = i;
            }

            if (user.accounts.get(i).type == "2"){
                chequingIndex = i;
            }
        }

        if (depositAccount == "1"){
            depositMessage.setText("New balance: "+ String.valueOf(user.accounts.get(savingsIndex).deposit(user.ID, amount)));
        }
        if (depositAccount == "2"){
            depositMessage.setText("New balance: "+ String.valueOf(user.accounts.get(chequingIndex).deposit(user.ID, amount)));
        }

    }

    //withdraws from an account
    // param - entry: the user input for the amount they want to add, passed in automatically
    public void withdrawBalance(String entry){
        double amount;
        int savingsIndex = -1;
        int chequingIndex = -1;

        try{
            amount = Double.valueOf(entry);
        }catch(Exception e){
            withdrawMessage.setText("Invalid input. Please enter a valid number.");
            return;
        }

        if (amount < 1){
            withdrawMessage.setText("Enter a value larger than 0.");
            return;
        }

        if (withdrawAccount == "1" && amount > 1000){
            withdrawMessage.setFont(new Font("Arial", Font.PLAIN, 23));
            withdrawMessage.setText("Surpassed withdrawl limit ($1000), request sent to bank admin.");
            return;
        }

        if (withdrawAccount == "2" && amount > 5000){
            withdrawMessage.setFont(new Font("Arial", Font.PLAIN, 23));
            withdrawMessage.setText("Surpassed withdrawl limit ($5000), request sent to bank admin.");
            return;
        }

        for (int i = 0; i<user.accounts.size(); i++){
            if (user.accounts.get(i).type == "1"){
                savingsIndex = i;
            }

            if (user.accounts.get(i).type == "2"){
                chequingIndex = i;
            }
        }

        if (withdrawAccount == "1"){
            if (amount>user.accounts.get(savingsIndex).balance){
                withdrawMessage.setText("Cannot withdraw more than you own. ("+user.accounts.get(savingsIndex).balance+")");
            }else{
                withdrawMessage.setText("Withdraw successful. New balance: "+user.accounts.get(savingsIndex).withdraw(user.ID, amount));
            }
        }

        if (withdrawAccount == "2"){
            if (amount>user.accounts.get(chequingIndex).balance){
                withdrawMessage.setText("Cannot withdraw more than you own. ("+user.accounts.get(chequingIndex).balance+")");
            }else{
                withdrawMessage.setText("Withdraw successful. New balance: "+user.accounts.get(chequingIndex).withdraw(user.ID, amount));
            }
        }

    }

    //logs the user into their account, if the account exists
    public boolean login(){
        String userID = loginField.getText();
        if (accounts.contains(userID)){
            user = new UserAccount(true, userID, "", "");
            return true;
        }else{
            return false;
        }
    }

    //updates the programs knowlege of saved accounts
    public void getAccounts(){
        try{

            File file = new File("data/accounts.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()){
                accounts.add(fileReader.nextLine());
            }

            fileReader.close();

        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(this, "accounts.txt not found.");
            System.exit(0);
        }
    }

    //action event manager
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == login){
            if (login()){
                interaction.removeAll();
                displayHomeScreen();
            }else{
                errorLabel.setText("Account not found.");
                interaction.repaint();
            }
        }

        if(e.getSource() == signup){
            interaction.removeAll();
            interaction.repaint();
            displaySignUp();
        }

        if (e.getSource() == confirmSignUp){
            user = new UserAccount(false, "", firstEntry.getText(), lastEntry.getText());
            interaction.removeAll();
            displayHomeScreen();
        }

        if(e.getSource() == acctDetails){
            interaction.removeAll();
            displayAccountDetails();
        }

        if (e.getSource() == editName){
            interaction.removeAll();
            displayEditName();
        }

        if (e.getSource() == saveName){
            user.firstName = newFirst.getText();
            user.lastName = newLast.getText();
            interaction.removeAll();
            displayAccountDetails();
        }

        if (e.getSource() == newAcct){
            interaction.removeAll();
            displayOpenNewAccount();
        }

        if (e.getSource() == openSavings){
            interaction.removeAll();
            displayOpenedSavings();
        }

        if (e.getSource() == openChequing){
            interaction.removeAll();
            displayOpenedChequing();
        }

        if(e.getSource() == deposit){
            interaction.removeAll();
            displayDepositTop();
        }

        if(e.getSource() == depositToSavings){
            depositAccount = "1";
            displayDepositBottom("Savings");
        }

        if(e.getSource() == depositToChequing){
            depositAccount = "2";
            displayDepositBottom("Chequing");
        }

        if(e.getSource() == confirmDeposit){
            addBalance(depositAmt.getText());
        }

        if (e.getSource() == withdraw){
            interaction.removeAll();
            displayWithdrawTop();
        }

        if (e.getSource() == withdrawFromSavings){
            withdrawAccount = "1";
            displayWithdrawBottom("Savings");
        }

        if (e.getSource() == withdrawFromChequing){
            withdrawAccount = "2";
            displayWithdrawBottom("Chequing");
        }

        if (e.getSource() == confirmWithdraw){
            withdrawBalance(withdrawAmt.getText());
        }

        if (e.getSource() == logout){
            interaction.removeAll();
            Database.save(user);
            getAccounts();
            displayLogin();
        }

    }

}