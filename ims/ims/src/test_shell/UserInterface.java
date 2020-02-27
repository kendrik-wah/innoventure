package test_shell;

import java.io.*;
import java.util.*;
import csv_manipulator.*;
import inventory.*;
import criteria.*;
import equipment.*;

class UserInterface {

    protected static boolean run_interface = true;
    protected static String run_file;
    protected static int command;
    protected static CSV_Obtainer csv_obtainer;
    protected static ArrayList csv_obtainer_output;
    protected static Criterion_Key parameters;
    protected static Inventory inventory;
    protected static ArrayList total_inventory;
    protected static ArrayList inventory_items;
    protected static Scanner file_input = new Scanner(System.in);
    protected static Scanner user_command = new Scanner(System.in);
    protected static Scanner input_data = new Scanner(System.in);
    protected static int number_of_parameters;
    protected static int number_of_items;

    public static void display_commands() {

        System.out.println("Enter 1 to add an item into the inventory list.");
        System.out.println("Enter 2 to remove an item from the inventory list.");
        System.out.println("Enter 3 to display the entire inventory.");
        System.out.println("Enter 4 to sort the inventory list.");
        System.out.println("Enter 5 to find any expired and expiring items relative to a date.");
        System.out.println("Enter 6 to find any items input on a particular date.");
        System.out.println("Enter 7 to find an item by its name.");
        System.out.println("Enter 8 to find items existing within a given location.");
        System.out.println("Enter 9 to find all items separated by name.");
        System.out.println("Enter 0 to find all items separated by location.");
        System.out.println("Enter 11 to display all available commands.");
        System.out.println("Enter 12 to leave the program.");
    }

    public static void main(String[] args) throws IOException{

        System.out.println("Welcome to Team HardHats' Inventory Management System Prototype!");

        // Scan filename to parse //
        System.out.println("Enter filename to analyze: ");
        run_file = file_input.next();

        CSV_Obtainer csv_obtainer = new CSV_Obtainer();

        // Obtain and abstract the information within the file to manipulate //
		csv_obtainer.obtain_csv(run_file);
		csv_obtainer_output = csv_obtainer.return_array();
		inventory = new Inventory();
		inventory.initialize_inventory(csv_obtainer_output);
		parameters = inventory.return_parameters();
		number_of_parameters = inventory.criteria_size();
		number_of_items = inventory.inventory_size();
		total_inventory = inventory.return_inventory();
		inventory_items = inventory.return_items();

        // Display commands //
        display_commands();

        while (run_interface == true) {

            command = user_command.nextInt();

            if (command == 1) {
                int counter = 0;
                String[] item = new String[number_of_parameters + 2];
                ArrayList <String> paras = parameters.return_criteria();
                for (String para: paras) {
                    String insider;
                    System.out.println("Enter the " + para + " of the item");
                    if (para.equals("Input Date & Time")) {
                        System.out.println("Enter in the following format: dd/MM/uuuu");
                        System.out.println("For example: 01/10/1999");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                        System.out.println("Enter in the following format: HH:mm");
                        System.out.println("For example: 01:05");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    }
                    else if (para.equals("Expiry Date & Time")) {
                        System.out.println("Enter in the following format: dd/MM/uuuu");
                        System.out.println("For example: 01/10/1999");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                        System.out.println("Enter in the following format: HH:mm");
                        System.out.println("For example: 01:05");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    }
                    else {
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    }
                }
                inventory.add_item(item);
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 2) {
                int counter = 0;
                String[] item = new String[number_of_parameters + 2];
                ArrayList <String> paras = parameters.return_criteria();
                for (String para: paras) {
                    String insider;
                    System.out.println("Enter the " + para + " of the item");
                    if (para.equals("Input Date & Time")) {
                        System.out.println("Enter in the following format: dd/MM/uuuu");
                        System.out.println("For example: 01/10/1999");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                        System.out.println("Enter in the following format: HH:mm");
                        System.out.println("For example: 01:05");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    } else if (para.equals("Expiry Date & Time")) {
                        System.out.println("Enter in the following format: dd/MM/uuuu");
                        System.out.println("For example: 01/10/1999");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                        System.out.println("Enter in the following format: HH:mm");
                        System.out.println("For example: 01:05");
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    } else {
                        insider = input_data.next();
                        item[counter] = insider;
                        counter++;
                    }
                }
                inventory.remove_item(item);
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 3) {
                inventory.print_inventory();
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 4) {

                int search_criterion;
                String dir;
                int direction;

                ArrayList <String> paras = parameters.return_criteria();
                System.out.println("Input the criterion you wish to sort by as shown below: ");
                for (int i = 0; i < paras.size(); i++) {
                    if (i == 4) {
                        System.out.println("Input Date and Time: " + i);
                    }
                    else if (i == 5) {
                        System.out.println("Expiry Date and Time: " + i);
                    }
                    else {
                        System.out.println(paras.get(i) + ": " + i);
                    }
                }
                search_criterion = input_data.nextInt();
                System.out.println("Enter 0 for ASCENDING ORDER or 1 for DESCENDING ORDER: ");
                direction = input_data.nextInt();

                inventory.sort_inventory(search_criterion, direction);
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 5) {
                String date, time;
                System.out.println("Enter in the following format: dd/MM/uuuu");
                System.out.println("For example: 01/10/1999");
                date = input_data.next();
                System.out.println("Enter in the following format: HH:mm");
                System.out.println("For example: 01:05");
                time = input_data.next();

                System.out.println("Expired Items: ");
                inventory.find_expired_items(date, time);
                System.out.println();
                System.out.println("Expiring Items: ");
                inventory.find_expiring_items(date, time);
                System.out.println();
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 6) {
                String date, time;
                System.out.println("Enter in the following format: dd/MM/uuuu");
                System.out.println("For example: 01/10/1999");
                date = input_data.next();
                System.out.println("Enter in the following format: HH:mm");
                System.out.println("For example: 01:05");
                time = input_data.next();

                System.out.println("Items inputted on " + date + " " + time + ": ");
                inventory.find_by_input_datetime(date, time);
                System.out.println();
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 7) {
                String name;
                System.out.print("Enter name of item: ");
                name = input_data.next();
                inventory.find_by_name(name);
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 8) {
                String location;
                ArrayList <String> locations = inventory.return_locations();
                System.out.println("The following locations are currently in use: ");
                for (String s: locations) {
                    System.out.println(s);
                }

                System.out.println();
                System.out.print("Enter the location you wish to search: ");
                location = input_data.next();
                inventory.find_by_location(location);
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 9) {
                inventory.printMap(inventory.compress_by_names(), "name");
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 10) {
                inventory.printMap(inventory.compress_by_locations(), "location");
                System.out.println("Enter 11 to display all available commands.");
            }
            else if (command == 12) {
                run_interface = false;
                System.out.println("Thank you for using our system!");
            }
            else {
                display_commands();
            }
        }
    }


}
