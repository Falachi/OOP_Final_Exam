package OOP_Exam;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class Interface {

    private static Scanner userInput = new Scanner(System.in);
    static boolean a = false;

    public static int adminMenu() {
        int choice = 3;
        System.out.println("Welcome Admin. Please pick one of these options:" +
                "\n 0 \t Edit Manager List" +
                "\n 1 \t Truck Management" +
                "\n 2 \t View Email" +
                "\n 3 \t Quit");
        choice = userInput.nextInt();
        return choice;
    }

    public static int managerMenu(){
        int choice = 3;
        System.out.println("Welcome Manager. Please pick one of these options:" +
                "\n 0 \t Truck Management" +
                "\n 1 \t Contact Admin" +
                "\n 2 \t Quit");
        choice = userInput.nextInt();
        return choice;
    }

    public static int visitorMenu(){
        int choice = 3;
        System.out.println("Welcome Visitor. Please pick one of these options:" +
                "\n 0 \t Truck Viewing" +
                "\n 1 \t View Services" +
                "\n 2 \t Contact Admin" +
                "\n 3 \t Quit");
        choice = userInput.nextInt();
        return choice;
    }

    public static void truckManagement(UserSystem user, DatabaseOption truckData) throws IOException {
        int userType = user.returnUserType();
        int column;
        int row;
        int choice = -1;

        switch (userType){
            case 0:
                System.out.println("Please select an option:");
                System.out.println("0 \t View recently installed truck");
                System.out.println("1 \t Edit a truck status");
                System.out.println("2 \t Install new truck");
                System.out.println("3 \t Search for a truck");

                choice = userInput.nextInt();
                userInput.nextLine();
                break;
            case 1:
                System.out.println("Please select an option:");
                System.out.println("0 \t View recently installed truck");
                System.out.println("1 \t Edit a truck status");
                System.out.println("2 \t Trucks in Branch");
                System.out.println("3 \t Compare to other Branches");

                choice = userInput.nextInt();
                userInput.nextLine();

                if(choice == 2 || choice == 3){
                    choice += 2;
                }

                break;

            default:
                System.out.println("ERROR");
        }

            switch (choice){
                case 0:
                    truckData.latestTruck();
                    break;
                case 1:
                    truckData.getWholeDatabase();
                    System.out.println("\n Please input the column to be edited:");
                    int newColumn = userInput.nextInt() - 1;
                    userInput.nextLine();
                    //Inputting the row which needs to be edited.
                    System.out.println("Please input the row to be edited:");
                    int newRow = userInput.nextInt();
                    userInput.nextLine();
                    //Inputting the new data.
                    System.out.println("Please put the new data:");
                    String newData = userInput.nextLine();
                    //Updating the database.
                    truckData.updateArray(newData, newColumn, newRow);
                    break;
                case 2:
                    System.out.println("Please input the following details of the new truck." +
                            "\nTruck ID:");
                    String truckID = userInput.nextLine();
                    System.out.println("Truck Availability:");
                    String truckAvailability = userInput.nextLine();
                    System.out.println("Branch Number:");
                    String branchNumber = userInput.nextLine();
                    System.out.println("Date Installed:");
                    String dateInstalled = userInput.nextLine();

                    String[] newTruck = {truckID, truckAvailability, branchNumber, dateInstalled};
                    truckData.installTruck(newTruck);
                    break;
                case 3:
                    System.out.println("Enter truck ID:");
                    int truckSearch = userInput.nextInt();
                    userInput.nextLine();

                    truckData.getDataRow(truckSearch);
                    break;
                case 4:
                    truckData.branchTruckNumber(user.returnBranch());
                    break;
                case 5:

            }
    }

    public static void setManagerList(DatabaseOption managerData){
        String newData;
        managerData.getWholeDatabase();
        //Inputting the column which needs to be edited.
        System.out.println("\n Please input the column to be edited:");
        int column = userInput.nextInt() - 1;
        userInput.nextLine();
        //Inputting the row which needs to be edited.
        System.out.println("Please input the row to be edited:");
        int row = userInput.nextInt();
        userInput.nextLine();
        //Inputting the new data.
        System.out.println("Please put the new data:");
        newData = userInput.nextLine();
        //Updating the database.
        managerData.updateArray(newData, column, row);
    }

    public static void services(){
        System.out.println("Here are a list of services available:" +
                "\n Towing" +
                "\n Delivery" +
                "\n Breakdown");
    }
}
