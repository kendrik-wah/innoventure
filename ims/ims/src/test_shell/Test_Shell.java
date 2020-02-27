package test_shell;
import java.io.*;
import java.util.*;

import csv_manipulator.*;
import inventory.*;
import criteria.*;
import equipment.*;


public class Test_Shell {

	public static String testfile = "java_csvsplit_import_test.csv";
	public static String testfile2 = "java_xlsx_import_test.xlsx";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		CSV_Obtainer csv_obtainer = new CSV_Obtainer();
//		CSV_Printer printer = new CSV_Printer();
//		csv_obtainer.obtain_csv(testfile);
//		ArrayList arr = csv_obtainer.return_array();
//		Inventory inventory = new Inventory();

		// Test CSV_Obtainer Methods
//		for (int i = 0; i < arr.size(); i++) {
//			System.out.println(arr.get(i));
//		}
//		System.out.println(csv_obtainer.number_of_rows()); // 7
//		System.out.println(csv_obtainer.number_of_cols()); // 6
		// End Test
		
//		Test Inventory Methods
//		inventory.initialize_inventory(arr);
//		ArrayList total_list = inventory.return_inventory();
//		ArrayList items = inventory.return_items();
////		System.out.println(total_list); // bunch of gibberish, array in array
////		System.out.println(items); // bunch of gibberish, equipment
//		System.out.println("Before: ");
//		inventory.print_inventory(); // actually shows the inventory
//		System.out.println();
//		inventory.sort_inventory(1, 1);
//		System.out.println("After: ");
//		inventory.print_inventory();
//		System.out.println("Items expiring by 24-12-2019, 22 59: ");
//		inventory.find_expired_items("24/12/2019", "22:59");
//		System.out.println();
//		inventory.find_expiring_items("24/12/2019", "22:59");
//		System.out.println();
//		inventory.find_by_input_datetime("24/12/2019", "22:59"); // nothing should print
//		System.out.println();
//		inventory.find_by_input_datetime("03/04/2019", "01:20"); // print everything out. update date and time accordingly
//		System.out.println();
//		inventory.find_by_name("brute"); // print brute out
//		System.out.println();
//		inventory.find_by_name("brutal"); // print nothing out
//		System.out.println();
//		inventory.find_by_location("A"); // print everything in A
//		System.out.println();
//		inventory.find_by_location("a"); // print nothing out
//		System.out.println();
//		Map compressed_lst = inventory.compress_by_names();
//		inventory.printMap(compressed_lst);
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		compressed_lst = inventory.compress_by_locations();
//		inventory.printMap(compressed_lst);
//		System.out.println();

//		String[] test_removal = {"5","penicillin","PECLN","A","23/12/2019","23:59"};
//		inventory.remove_item(test_removal);
//		System.out.println();
//		System.out.println("After: ");
//		inventory.print_inventory(); // one item less
//		inventory.sort_inventory(0, 0);
//		System.out.println();
//		System.out.println("After: ");
//		inventory.print_inventory();
//		inventory.remove_item(test_removal);
//		System.out.println();
//		System.out.println("After: ");
//		inventory.print_inventory(); // nothing changes

	}
}
