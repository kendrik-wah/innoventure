package csv_manipulator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import equipment.*;
import inventory.*;
import criteria.*;

public class CSV_Printer {
	
	public static void print_table(String testfile) throws IOException{ // Tester method //
		
		CSV_Obtainer csv_obtainer = new CSV_Obtainer();
		csv_obtainer.obtain_csv(testfile);
		ArrayList arr = csv_obtainer.return_array();
		int row = csv_obtainer.number_of_rows();
		int col = csv_obtainer.number_of_rows();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col-1; j++) {
				if (j != col-1) {
					System.out.printf("%s\t", Array.get(arr.get(i), j));
				}
				else {
					System.out.printf("%s", Array.get(arr.get(i), j));
				}
			}
			System.out.printf("\n");
		}		
	}
	
	public static String toStringRowTable(String testfile, int row_number) throws IOException{
		
		CSV_Obtainer csv_obtainer = new CSV_Obtainer();
		csv_obtainer.obtain_csv(testfile);
		ArrayList arr = csv_obtainer.return_array();
		int row_count = csv_obtainer.number_of_rows();
		int col = csv_obtainer.number_of_rows();		
		
		String row = (String) Array.get(arr.get(row_number), 0);
		
		for (int j = 1; j < col-1; j++) {
				row = row + ", " + Array.get(arr.get(row_number), j);
		}
		
		return row;
	}
	
	public static String toStringItemList(LinkedList <String[]> item_list, int row_number) {
		
		int col = item_list.get(row_number).length;
		String[] chosen_row = item_list.get(row_number);
		
		String row = (String) Array.get(chosen_row, 0);
		
		for (int j = 1; j < col; j++) {
				row = row + ", " + Array.get(chosen_row, j);
		}
		
		return row;
	}

	
	public static void print_inventory(Inventory inventory) { // Tester Method //
		
		ArrayList current_inventory = inventory.return_inventory();
		System.out.println(current_inventory);
	}
	
//	public static void print_cell(Equipment item, int row, int col) {
//		System.out.println(Array.get(arr.get(row), col));
//	}
//	
//	public static void print_line(Equipment item, int row) {
//		ArrayList table = new ArrayList();
//		System.out.println(table);
//	}
}
