package equipment;

import java.lang.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Equipment{
	
	private ArrayList _properties = new ArrayList <> ();
	
	public void initialize_object(int index, String[] equipment) {

		for (int i = 0; i < equipment.length; i++) {
			if (i == 0) {
				_properties.add(index);
			}
			else if (i < equipment.length - 4) {
				String intermediate = equipment[i];
				_properties.add(intermediate);
			}
			else if (i == (equipment.length - 4) || (i == equipment.length - 2)){

				String date = equipment[i];
				String d[] = date.split("/");
				String formatted_date = new String();
				String time = equipment[i+1];
				String t[] = time.split(":");
				String formatted_time = new String();

				for (int j = 0; j < d.length; j++) {
					if (d[j].length() == 1) {
						d[j] = "0" + d[j];
					}
					formatted_date += (d[j]);
					if (j != d.length - 1) {
						formatted_date += ("/");
					}
				}

				for (int j = 0; j < t.length; j++) {
					if (t[j].length() == 1) {
						t[j] = "0" + t[j];
					}
					formatted_time += (t[j]);
					if (j != t.length - 1) {
						formatted_time += (":");
					}
				}

				DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
				LocalDateTime date_time_combi = LocalDateTime.parse(formatted_date + ' ' + formatted_time, date_time_formatter);

				String dt = date_time_combi.format(date_time_formatter);
				_properties.add(dt);
			}
		}
	}


	public Object return_by_index(int i) {
		return _properties.get(i);
	}
	
	public ArrayList return_properties() {
		return _properties;
	}
	
	public void add_property(String property) {
		_properties.add(property);
	}
	
	public void remove_property(String property) {
		_properties.remove(property);
	}

	public ArrayList filter_by_index(int i) {
		ArrayList a = new ArrayList <> ();
		for (int j = 0; j < _properties.size(); j++) {
			if (j != i) {
				a.add(_properties.get(i));
			}
		}
		return a;
	}
}
