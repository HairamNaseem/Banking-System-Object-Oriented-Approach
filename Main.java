package com.company;
import java.io.*;
import java.nio.CharBuffer;
import java.util.Scanner;

interface Bank {

    void info() throws IOException;

    public static void main(String[] args) throws IOException {
        File f = new File("Bank.Information.txt");
        f.createNewFile();
        Loan l1 = new Loan();
        l1.Display();
    }
}
class Accounts implements Bank {
    String admin = "Fast", Password = "Bank111";
    float interest;
    private int c;
    private int pin;
    private int amount;
    private String fname;
    private String lname;
    private String address;
    private String cnic;

    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public int getC() {
        return c;
    }
    public void setC(int c) {
        this.c = c;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCnic() {
        return cnic;
    }
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    Scanner in = new Scanner(System.in);

    @Override
    public void info() throws IOException {
        System.out.println("Enter your First name: ");
        setFname(in.next());
        System.out.println("Enter your Last name: ");
        setLname(in.next());
        System.out.println("Enter your CNIC: ");
        setCnic(in.next());
        if(cnic.length()>13||cnic.length()<13){
            System.out.println("invalid");
            System.exit(0);
        }
        try{
            System.out.println("Enter your Address: ");
            setAddress(in.next());
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("please do not put space between address");
        }
    }

    public void create_account() throws IOException {
        System.out.println("Account Creation\n1. Saving Account\n2. Current Account");
        c = in.nextInt();
        if (c == 1) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
            System.out.println("How many account do you want to create: ");
            int a = in.nextInt();
            for (int i = 0; i < a; i++) {
                info();
                System.out.println("Set a 4 digit PIN: ");
                pin = in.nextInt();
                if (pin > 9999 || pin < 1000) {
                    System.out.println("invalid pin");
                    System.exit(0);
                }
                account_num();
                System.out.println("Enter the initial amount you want to have in your account: ");
                amount = in.nextInt();
                System.out.println("Check the details you entered");
                System.out.printf("Congratulations Account Opened Successfully with your %d amount.", amount);
                System.out.println();
                interest = (int)(amount * 5 * 1) / 100;
                System.out.printf("Addition of Rs %.1f every year in account.", interest);
                System.out.println();
                bw.write("Fullname: " + fname+" "+lname);
                bw.write("\n");
                bw.write("PIN: "+pin+"\n");
                bw.write("CNIC: " + cnic+"\n");
                bw.write("Address: " + address);
                bw.write("\n");
                bw.write("Current balance: " + amount);
                bw.close();

            }
        } else if (c == 2) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
            System.out.println("How many accounts do you want to create: ");
            int a = in.nextInt();
            for (int i = 0; i < a; i++) {
                info();
                System.out.println("Set a 4 digit PIN: ");
                pin = in.nextInt();
                if (pin > 9999 || pin < 1000) {
                    System.out.println("invalid pin");
                    System.exit(0);
                }
                account_num();
                System.out.println("Enter the initial amount you want to have in your account: ");
                amount = in.nextInt();
                System.out.println("Check the details you entered");
                System.out.printf("Congratulations Account Opened Successfully with your %d amount.", amount);
                System.out.println();
                bw.write("Fullname: " + fname+" "+lname);
                bw.write("\n");
                bw.write("CNIC: " + cnic+"\n");
                bw.write("Address: " + address);
                bw.write("\n");
                bw.write("Current Balance: " + amount);
                bw.close();

            }
        }
    }

    void account_num() {
        double min = 10000000;
        double max = 99999999;
        int acc_num = (int) (Math.random() * ((max - min) + 1));
        System.out.println("8 digit Account Number Assigned: " + acc_num);

    }

    void credit() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Bank.Information.txt"));
        String Buffer;
        while ((Buffer = br.readLine())!=null){
            System.out.println("/Your Account Information/");
            System.out.println(Buffer);
        }
        br.close();
        System.out.println("Enter your First name: ");
        fname = in.next();
        System.out.println("Enter your last name: ");
        lname = in.next();
        System.out.println("Enter your amount as you can see in your account: ");
        amount = in.nextInt();
        System.out.println("current balance: "+amount);
        System.out.println("Enter amount for credit: ");
        double credit_amount=in.nextDouble();
        amount =(int)  (amount + credit_amount);
        System.out.println("balance after credit: "+amount);
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt"));
        bw.write("Fullname: " + fname+" "+lname);
        bw.write("\n");
        bw.write("Current Balance: " + amount);
        bw.close();

    }

    void debit() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Bank.Information.txt"));
        String Buffer;
        while ((Buffer = br.readLine())!=null){
            System.out.println(Buffer);
        }
        br.close();
        System.out.println("/Your Account Information/");
        System.out.println("Enter your First name: ");
        fname = in.next();
        System.out.println("Enter your last name: ");
        lname = in.next();
        System.out.println("Enter your amount as you can see in your account: ");
        amount = in.nextInt();
        System.out.println("Name: "+fname+" "+lname);
        System.out.println("current balance: "+amount);
        System.out.println("Enter amount for credit: ");
        double debit_amount=in.nextDouble();
        if(debit_amount>amount){
            System.out.println("entered amount greater than current balance");
            System.exit(0);
        }
        amount = (int) (amount - debit_amount);
        System.out.println("balance after debit: "+amount);
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt"));
        bw.write("Fullname: " + fname+" "+lname);
        bw.write("\n");
        bw.write("After Debit Amount in Account: " + amount);
        bw.close();
    }

    public void bank_manager() throws IOException {
        System.out.println("Enter Admin ID:");
        admin = in.next();
        System.out.println("Enter Password: ");
        Password = in.next();
        if (admin.equals("Fast") && Password.equals("Bank111")) {
            BufferedReader br = new BufferedReader(new FileReader("Bank.Information.txt"));
            String buffer;
            while ((buffer = br.readLine()) != null) {
                System.out.println(buffer);
            }
            br.close();
        }
        else{
            System.out.println("Invalid ID or Password");
            System.exit(0);
        }
    }
}

class Loan extends Accounts{
    double income;
    int loanType;

    @Override
    public void info() throws IOException {
        super.info();
    }

    void loan_types() throws IOException{
        System.out.printf("\tLOAN INQUIRY TABLE\n\nIncome:\t\teligibility for loan type:\n\n");
        System.out.printf("5000-49999\tcar loan\n50000-99999\tcar loan/house loan\n>100000\t\tall kinds of loan\n\n");
        System.out.printf("TYPES OF LOAN:\n 1.house loan\n 2.car loan\n 3.bussiness loan\n\n");
        System.out.println("enter monthly income: ");
        income = in.nextDouble();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
        if(income<=50000&&income>=5000)
        {
            System.out.println("Eligible for car loan only");
            loan_1();
        }
        else if(income>50000&&income<=100000)
        {
            System.out.printf("\nEligible for car loan and house loan only\n");
            System.out.println("enter required loan type: ");
            loanType = in.nextInt();
            if(loanType==1)
            {
                loan_1();
            }
            else if(loanType==2)
            {
                loan_2();
            }
        }
        else if(income>100000)
        {
            System.out.printf("\nEligible for all kinds of loan\n");
            System.out.println("enter required loan type: ");
            loanType = in.nextInt();
            if(loanType==1)
            {
                loan_1();
            }
            else if(loanType==2)
            {
                loan_2();
            }
            else if(loanType==3)
            {
                loan_3();
            }
        }
        else
        {
            System.out.printf("\nNot eligible for any loan");
            System.exit(0);
        }
        bw.close();
    }
    void loan_1() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
        System.out.printf("\nCONDITIONS:\n interest: 5 percent\n penalty if loan not repayed within time\n");
        info();
        System.out.printf("\nEnter required amount of loan: ");
        double loanAmount = in.nextDouble();
        System.out.printf("Enter loan repayment time in years: ");
        int loanRepayTime = in.nextInt();
        interest = (float) ((loanAmount * 5 * loanRepayTime)/100);
        double repay=interest+loanAmount;
        System.out.println("\nloan to be repayed = "+ repay);
        bw.write("Car loan taken by: "+getFname()+" "+getLname()+"\n");
        bw.write("CNIC:"+getCnic()+"\n");
        bw.write("Address: "+getAddress()+"\n");
        bw.write("Amount of loan: "+loanAmount+"\n");
        bw.write("loan repayment time: "+loanRepayTime+"\n");
        bw.write("Loan to be Repayed: "+repay+"\n");
        bw.close();
    }
    void loan_2() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
        System.out.printf("\nCONDITIONS:\n interest: 3 percent\n penalty if loan not repayed within time\n");
        info();
        System.out.printf("\nEnter required amount of loan: ");
        double loanAmount = in.nextDouble();
        System.out.printf("Enter loan repayment time in years: ");
        int loanRepayTime = in.nextInt();
        interest = (float) ((loanAmount * 3 * loanRepayTime)/100);
        double repay=interest+loanAmount;
        System.out.println("\nloan to be repayed = "+ repay);
        bw.write("House loan taken by: "+getFname()+" "+getLname()+"\n");
        bw.write("CNIC:"+getCnic()+"\n");
        bw.write("Address: "+getAddress()+"\n");
        bw.write("Amount of loan: "+loanAmount+"\n");
        bw.write("loan repayment time: "+loanRepayTime+"\n");
        bw.write("Loan to be Repayed: "+repay+"\n");
        bw.close();
    }
    void loan_3() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("Bank.Information.txt",true));
        System.out.printf("\nCONDITIONS:\n interest: 7 percent\n penalty if loan not repayed within time\n");
        info();
        System.out.printf("\nEnter required amount of loan: ");
        double loanAmount = in.nextDouble();
        System.out.printf("Enter loan repayment time in years: ");
        int loanRepayTime = in.nextInt();
        interest = (float) ((loanAmount * 7 * loanRepayTime)/100);
        double repay=interest+loanAmount;
        System.out.println("\nloan to be repayed = "+ repay);
        bw.write("Business loan taken by: "+getFname()+" "+getLname()+"\n");
        bw.write("CNIC:"+getCnic()+"\n");
        bw.write("Address: "+getAddress()+"\n");
        bw.write("Amount of loan: "+loanAmount+"\n");
        bw.write("loan repayment time: "+loanRepayTime+"\n");
        bw.write("Loan to be Repayed: "+repay+"\n");
        bw.close();
    }



    void Display() throws IOException {
        Scanner in = new Scanner(System.in);
        int choice1;
        System.out.println("--------------------------FAST BANK-------------------------");
        System.out.println("1.CREATE ACCOUNT\t\t2.SHOW ALL CUSTOMERS INFORMATION\n");
        System.out.println("3.APPLY FOR LOAN\t\t4.DEBIT AMOUNT\n");
        System.out.println("5.CREDIT AMOUNT\t\t\t6.EXIT");
        System.out.println("\nEnter choice");
        choice1=in.nextInt();
        switch(choice1){
            case 1:
                create_account();
                choice_2();
                break;
            case 2:
                bank_manager();
                choice_2();
                break;
            case 3:
                loan_types();
                choice_2();
                break;
            case 4:
                debit();
                choice_2();
                break;
            case 5:
                credit();
                choice_2();
                break;
            case 6:
                System.out.println("THANK YOU FOR VISITING");
                System.exit(0);
                break;
            default:
                System.out.println("INVALID ENTRY");
                break;
        }
    }

    void choice_2() throws IOException{
        System.out.println("do you want to perform any other action?");
        char choice2 = in.next().charAt(0);
        if(choice2=='Y'||choice2=='y'){
            Display();
        }
        else{
            System.out.println("THANK YOU FOR VISITING");
            System.exit(0);
        }
    }

}