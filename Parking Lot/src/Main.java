import Controller.ParkingController;
import Service.ParkingServiceImpl;

import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Parking Lot Application");

        ParkingController controller = new ParkingController(new ParkingServiceImpl());
//        Scanner scanner = new Scanner(System.in);
//
//        while(true) {
//            String[] input = scanner.nextLine().split(" ");
//            String response = controller.processInput(input);
//
//            if(response.equals("exit")) break;
//        }
//
//        scanner.close();


        // Reading from the file
        BufferedReader bufferedReader = null;
        String inputFile = "D:\\Coding\\Machine Coding\\Parking Lot\\src\\Input.txt";

        try {
            bufferedReader = new BufferedReader(new FileReader(new File(inputFile)));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] input = line.split(" ");
                String response = controller.processInput(input);

                if(response.equals("exit")) break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}