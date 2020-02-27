package csv_manipulator;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CSV_Obtainer {
	
	private ArrayList <String[]> _csv_array = new ArrayList <> ();
	private int[] _dimension_arr = new int[2]; // {rows, columns}
	
	public void obtain_csv(String filename) throws IOException{
		File test_file = new File(filename);
		int counter = 0; // To iterate across the lines of the file;
		String data;
		String[] row;
		try {
			Scanner inputStream = new Scanner(test_file); // Obtaining the file
			while (inputStream.hasNextLine()) { // Parse a line at a time
				data = inputStream.nextLine(); // Obtain line
				row = data.split(",");
				_dimension_arr[1] = row.length;
				_csv_array.add(row);

				counter = counter + 1;
			}
			_dimension_arr[0] = counter; // Obtain number of rows of the array. Using .length or .length() will give number of characters. Not desirable
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}

	public void obtain_xlsx(String filename) throws IOException {
		File test_file = new File(filename);
	}
	
	public ArrayList return_array() {
		return _csv_array;
	}
	
	public int[] show_dimensions() {
		return _dimension_arr;
	}
	
	public int number_of_rows() {
		return show_dimensions()[0];
	}
	
	public int number_of_cols() {
		return show_dimensions()[1];
	}

}
