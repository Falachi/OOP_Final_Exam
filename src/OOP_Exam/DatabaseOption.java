package OOP_Exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatabaseOption {

    private ArrayList<ArrayList<String>> mainArray = new ArrayList(); //main array
    private ArrayList<String> tempArray0 = new ArrayList(); //temporary array to edit the main array. There are 6 unique temporary array because the database maximum column is fixed and the longest database available has 7 columns.
    private ArrayList<String> tempArray1 = new ArrayList();
    private ArrayList<String> tempArray2 = new ArrayList();
    private ArrayList<String> tempArray3 = new ArrayList();
    private ArrayList<String> tempArray4 = new ArrayList();
    private ArrayList<String> tempArray5 = new ArrayList();
    private String filePath;
    private String[] tempString; //this is to help separate the line by excluding \t.
    private Map<Integer, ArrayList> mapArray = new HashMap<>(); //just a method to make things simple by assigning keys to each temporary arrays. This is to reduce headache and confusion.
    private int count = 0; //when assigning each data into their respective temporary arrays, there need to be a method for the application to know which column it is writing to at the moment.
    private int column = 0; //the number of columns in the database.
    private int row = 0; //the number of rows in the database.

    public void DatabaseOption(){
    }

    private void mappingArray(){
        mapArray.put(0, tempArray0);
        mapArray.put(1, tempArray1);
        mapArray.put(2, tempArray2);
        mapArray.put(3, tempArray3);
        mapArray.put(4, tempArray4);
        mapArray.put(5, tempArray5);
    }

    public void databaseSetUp(String fileType) throws FileNotFoundException {
        //read and fetch the already written data to the database.
        File data = new File(fileType + ".txt");
        Scanner read = new Scanner(data);

        fileType = fileType + ".txt";

        //transferring the data from .txt file to the array.
        while(read.hasNextLine()){
            String fetchData = read.nextLine();
            tempString = fetchData.split("\t");

            //Assigning each data into its own tempArray. Because of how the data is saved in the .txt file (VALUE\tVALUE), and read.nextLine() takes the whole line, that means the application must separate that line into their respective arrays.
            for(String a : tempString){

                switch (count){
                    case 0:
                        tempArray0.add(a);
                        count++;
                        column = 1;
                        break;
                    case 1:
                        tempArray1.add(a);
                        count++;
                        column = 2;
                        break;
                    case 2:
                        tempArray2.add(a);
                        count++;
                        column = 3;
                        break;
                    case 3:
                        tempArray3.add(a);
                        count++;
                        column = 4;
                        break;
                    case 4:
                        tempArray4.add(a);
                        count++;
                        column = 5;
                        break;
                    case 5:
                        tempArray5.add(a);
                        count++;
                        column = 6;
                        break;
                    default:
                        System.out.println("ERROR");
                        break;
                }
            }
            count = 0;
        }

        row = tempArray0.size(); //this is to find the number of row in the database.
        mappingArray();

        //assigning each temporary arrays into the main array to create a 2D array.
        for(int i = 0; i < column; i++){
            mainArray.add(mapArray.get(i));
        }
    }

    public void getWholeDatabase(){
        for(int i = 0; i < row; i++){
            for(int x = 0; x < column; x++){
                System.out.print(mainArray.get(x).get(i) + "\t\t");
            }
            System.out.println("");
        }
    }

    public void updateArray(String updatedData, int editColumn, int editRow){
        mainArray.get(editColumn).set(editRow, updatedData);
    }

    public void getData(int c, int r){
        mainArray.get(c).get(r);
    }

    public void saveDatabase() throws IOException {

        File file = new File(filePath);
        file.delete();
        file.createNewFile();

        FileWriter writeFile = new FileWriter(filePath, true);

        for(int i = 0; i < row; i++){
            for(int x = 0; x < column; x++){
                if(x == column - 1){
                    writeFile.write(mainArray.get(x).get(i));
                }else{
                    writeFile.write(mainArray.get(x).get(i) + "\t");
                }

            }
            writeFile.write("\n");
        }
        writeFile.close();
    }

    public void latestTruck(){
        int highestValue = 0;
        String temporary;
        int highestRow = 0;

        for(int i = 1; i < row; i++){
            temporary = mainArray.get(3).get(i);
            temporary = temporary.substring(0, 4);

            if(highestValue < Integer.parseInt(temporary)){
                highestValue = Integer.parseInt(temporary);
                highestRow = i;
            }
        }

        System.out.println("The latest truck is truckID = " + mainArray.get(0).get(highestRow));
    }

    public void getDataRow(int r){
        for(int i = 0; i < column; i++){
            System.out.print(mainArray.get(i).get(0));
        }
        System.out.println("");
        for(int i = 0; i < column; i++){
            System.out.print(mainArray.get(i).get(r));
        }
        System.out.println("");
    }

    public void installTruck(String[] a) throws IOException {
        mappingArray();

        for(int i = 0; i < column; i++){
            mapArray.get(i).add(a[i]);
        }
        mappingArray();

        for(int i = 0; i < column; i++){
            mainArray.add(mapArray.get(i));
        }
        saveDatabase();

    }

    public void branchTruckNumber(int b){
        int a = 0;
        for(int i = 0; i < row; i++){
            if(Integer.parseInt(mainArray.get(3).get(i)) == b){
                a++;
            }
        }
        System.out.println("The total number of truck in your branch is " + a);
    }

    public void compareBranch(int b){
        ArrayList<Integer> branchID = new ArrayList<Integer>();
        ArrayList<Integer> branchTotal = new ArrayList<Integer>();
        int a = 0;

        for(int i = 0; i < row; i++){
            if(a != Integer.parseInt(mainArray.get(2).get(i))){
                branchID.add(Integer.parseInt(mainArray.get(2).get(i)));
                a = Integer.parseInt(mainArray.get(2).get(i));
            }
        }
        int c = 0;

        for(int i = 0; i < row; i++){
            c = Integer.parseInt(mainArray.get(2).get(i));
            branchTotal.set(c, branchTotal.get(c) + 1);
        }

    }

    public void sendMail(String[] a) throws IOException {
        mappingArray();

        for(int i = 0; i < column; i++){
            mapArray.get(i).add(a[i]);
        }
        mappingArray();

        for(int i = 0; i < column; i++){
            mainArray.add(mapArray.get(i));
        }
        saveDatabase();

    }
}
