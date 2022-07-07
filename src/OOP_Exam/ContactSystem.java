package OOP_Exam;

import java.io.IOException;
import java.util.Scanner;

public class ContactSystem {
    private static Scanner userInput = new Scanner(System.in);

    public static void checkMail(DatabaseOption mail){
        mail.getWholeDatabase();
    }

    public static void sendMail(DatabaseOption mail, UserSystem user) throws IOException {
        System.out.println("Please write your message:");
        String message = userInput.nextLine();

        String[] a = {user.returnName(), message};

        mail.sendMail(a);
    }
}
