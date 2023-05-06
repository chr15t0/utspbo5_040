package UTS;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Main {
    String name;
    int accountnum;
    float balance;
    String regdate;
    Scanner sc = new Scanner(System.in);

    //clearscreen
    public static void cls(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    }

    //Array list to store accounts
    static ArrayList <Main> accounts = new ArrayList<Main>();

    //void for user to return to home menu
    void returnhome(){
        System.out.println("\nPress Enter to return to home menu...");
        sc.nextLine();
    }

    //constructor for creating new account 
    public void createacc(String name, int accountnum,float balance, String regdate){
        Main newacc = new Main();
        newacc.name=name;
        newacc.accountnum=accountnum;
        newacc.balance=balance;
        newacc.regdate=regdate; 
        accounts.add(newacc);
    }

    
    public static void main(String[] args){
        cls();
        float amount;
        int i,user_accnumb, recipient_accnumb;;
        Scanner sc = new Scanner(System.in);
        Main newdata = new Main();

        while(true){
        System.out.println("===============================");
        System.out.println("|         IKLC BANK           |");
        System.out.println("===============================");
        System.out.println("| 1. Register Account         |");
        System.out.println("| 2. Transfer                 |");
        System.out.println("| 3. Deposit                  |");
        System.out.println("| 4. Check Your Balance       |");
        System.out.println("| 5. Quit                     |");
        System.out.println("===============================");
        System.out.print("Please enter your option : ");
        int option = sc.nextInt();

        if(option==1){
            cls();
            //enter name 
            System.out.print("Please enter your full name : ");
            String name = sc.next();  
            
            //to generate random account number & check it's uniqueness
            Random random=new Random();
            int accountnum;
            accountnum=random.nextInt (99999)+100000;
            for (i=0;i<accounts.size();i++){
                //check uniqueness of account number 
                if(accountnum==accounts.get(i).accountnum){
                    accountnum=random.nextInt (99999)+100000;
                }
            }
            System.out.println("Your account number is : "+accountnum);

            //enter first deposit
            System.out.print("insert you first deposit (in thousands): Rp.");
            Float balance = sc.nextFloat();

            //generate current date 
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String regdate = SDF.format(dt);
            System.out.println("Registration date : "+regdate);

            //store newly registered account to array list 
            newdata.createacc(name, accountnum, balance, regdate);
            System.out.println("===============================");
            System.out.println("|   REGISTRATION SUCCESSFUL   |");
            System.out.println("===============================");

            //return to home menu
            newdata.returnhome();

        }
        else if(option==2){
            cls();
            //enter user account number
            System.out.print("Please enter your account number : ");
            user_accnumb=sc.nextInt();
            
            //enter recipient account number
            System.out.print("Please enter the recipient's account number : ");
            recipient_accnumb=sc.nextInt();

            //enter the amount of money that is going to be transferred
            System.out.print("Please enter the amount you want to transfer : RP.");
            amount=sc.nextFloat();

            for(i=0;i<accounts.size();i++){
                //to check if the user account number is valid
                if (user_accnumb==accounts.get(i).accountnum){
                    //to check if the user's balance is insufficient
                    if (accounts.get(i).balance<amount){
                        System.out.println();
                        System.out.println("Your balance is insufficient!");
                        System.out.println("Your current balance is : Rp."+accounts.get(i).balance);
                        System.out.print("Please re-enter the right amount of money to transfer : Rp.");
                        amount=sc.nextFloat();
                    }
                    //calculate new balance to the user's account
                    accounts.get(i).balance-=amount;
                    System.out.println();
                    System.out.println("Your remaining balance is Rp."+accounts.get(i).balance);
                }
                //to check if the recipient account number is valid
                if (recipient_accnumb==accounts.get(i).accountnum){
                    //calculate new balance to the recipient's account
                    accounts.get(i).balance+=amount;
                    System.out.println("You have successfully transferred Rp."+amount+" to "+accounts.get(i).name);
                }
            }
            System.out.println();
            System.out.println("===============================");
            System.out.println("|   TRANSACTION SUCCESSFUL    |");
            System.out.println("===============================");

            //return to home menu
            newdata.returnhome();
        }
        else if(option==3){
            cls();
            //enter user account number
            System.out.print("Please enter your account number : ");
            user_accnumb=sc.nextInt();

            //enter the amount of money that is going to be deposited
            System.out.print("Please enter the amount of money you want to deposit : RP.");
            amount=sc.nextFloat();

            for(i=0;i<accounts.size();i++){
                //to check if the user account number is valid
                if (user_accnumb==accounts.get(i).accountnum){
                    //calculate new balance to the user's account
                    accounts.get(i).balance+=amount;
                    System.out.println();
                    System.out.println("===================================================");
                    System.out.println(" Hello, "+accounts.get(i).name+"!");
                    System.out.println(" Your current balance  is Rp."+accounts.get(i).balance);
                    System.out.println("===================================================");
                }
            }
            System.out.println();
            System.out.println("===============================");
            System.out.println("|   TRANSACTION SUCCESSFUL    |");
            System.out.println("===============================");

            //return to home menu
            newdata.returnhome();
        }
        else if(option==4){
            cls();
            //enter user account number
            System.out.print("Please enter your account number : ");
            user_accnumb=sc.nextInt();

            for(i=0;i<accounts.size();i++){
                //to check if the user account number is valid
                if (user_accnumb==accounts.get(i).accountnum){
                    System.out.println();
                    System.out.println("===================================================");
                    System.out.println(" Hello, "+accounts.get(i).name+"!");
                    System.out.println(" Your current balance is Rp."+accounts.get(i).balance);
                    System.out.println("===================================================");
                }
            }

            //return to home menu
            newdata.returnhome();
        }
        else if(option==5){
            cls();
            System.out.println("====================================================");
            System.out.println("|  Thank you for using our bank, have a nice day!  |");
            System.out.println("====================================================");

            //return to home menu
            System.exit(0);
        }
        else{
            //return to home menu
            newdata.returnhome();
        }
        cls();
        }
    }
}
