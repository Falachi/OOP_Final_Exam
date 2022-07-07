package OOP_Exam;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        int a = 0;
        int choice = 0;

        //setting up the databases
        //setting up 2D ArrayList for vehicle
        DatabaseOption vehicleInfo = new DatabaseOption();
        vehicleInfo.databaseSetUp("vehicleList");
        //setting up 2D ArrayList for truck
        DatabaseOption truckInfo = new DatabaseOption();
        truckInfo.databaseSetUp("truckList");
        //setting up 2D ArrayList for branch
        DatabaseOption branchInfo = new DatabaseOption();
        branchInfo.databaseSetUp("branchList");
        //setting up 2D ArrayList for manager
        DatabaseOption managerInfo = new DatabaseOption();
        managerInfo.databaseSetUp("managerList");
        //setting up 2D ArrayList for mail
        DatabaseOption mailInfo = new DatabaseOption();
        mailInfo.databaseSetUp("mail");

        UserSystem user = new UserSystem();
        user.setUser();

        do{
            switch (user.returnUserType()){
                case 0: //if user is admin
                    choice = Interface.adminMenu();
                    if(choice == 0){
                        Interface.setManagerList(managerInfo);
                    }else if(choice == 1){
                        Interface.truckManagement(user, truckInfo);
                    }else if(choice == 2){
                        ContactSystem.checkMail(mailInfo);
                    }else{
                        System.exit(0);
                    }
                    break;
                case 1: //if user is manager
                    choice = Interface.managerMenu();
                    if(choice == 0){
                        Interface.truckManagement(user, truckInfo);
                    }else if(choice == 1){
                        ContactSystem.sendMail(mailInfo, user);
                    }else{
                        System.exit(0);
                    }
                    break;
                case 2: //if user is visitor
                    choice = Interface.visitorMenu();
                    if(choice == 0){
                        Interface.truckManagement(user, truckInfo);
                    }else if(choice == 1){
                        Interface.services();
                    }else if(choice == 2){
                        ContactSystem.sendMail(mailInfo, user);
                    }else{
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }while(a == 0);


    }
}
