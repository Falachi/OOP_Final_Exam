package OOP_Exam;

import java.util.Scanner;

public class UserSystem {

    private Scanner userInput = new Scanner(System.in);

    private int userType;
    private String name;
    private int branch;

    public void userSystem(){

    }

    public int returnUserType(){
        return userType;
    }

    public int returnBranch(){
        return branch;
    }
    public String returnName(){
        return name;
    }

    public void setUser(){

        //Entering the name of Users.
        System.out.println("Welcome. Please state your name:");
        name = userInput.nextLine();

        //Entering the type of user.
        System.out.println("Welcome " + name + ". Please select your role in the application." +
                "\n 0 \t Administrator" +
                "\n 1 \t Branch Manager" +
                "\n 2 \t Visitor" +
                "\n Please type the number corresponds to the role.");
        userType = userInput.nextInt();

        switch(userType){
            case 0:
                System.out.println("You have selected administrator role.");
                break;
            case 1:
                System.out.println("You have selected Branch Manager role.");

                //Picking the branch for the Branch Manager.
                System.out.println("Please select the branch which you manage:");
                System.out.println(" 0 \t Kuala Lumpur" +
                        "\n 1 \t Selangor" +
                        "\n 2 \t Inpoh" +
                        "\n 3 \t Penang" +
                        "\n Please type the number corresponds to your branch.");

                branch = userInput.nextInt();

                break;
            case 2:
                System.out.println("You have selected Visitor role.");
                break;
        }


    }
}
