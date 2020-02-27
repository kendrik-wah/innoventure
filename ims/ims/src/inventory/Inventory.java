package inventory;

import java.util.*;
import java.lang.*;
import java.time.*;
import java.time.format.*;

import criteria.*;
import equipment.*;

public class Inventory {

	private Criterion_Key _header = new Criterion_Key();
	private ArrayList <Equipment> _inventory_list = new ArrayList <> ();
	private ArrayList <String> _locations = new ArrayList <> ();
	private int _number_of_parameters = _header.number_of_parameters();
	private int _number_of_items = _inventory_list.size();

	public void initialize_header(String[] criteria) {
		int i;
		for (i = 0; i < criteria.length; i++) {
			String criterion = new String();
			if (criteria[i].equals("Input Date")) {
				criterion = "Input Date & Time";
			}
			else if (criteria[i].equals("Expiry Date")) {
				criterion = "Expiry Date & Time";
			}
			else if (i < criteria.length - 4){
				criterion = criteria[i];
			}
			else {
				continue;
			}
			_header.add_criterion(criterion);
		}
		_number_of_parameters = _header.number_of_parameters();
	}

	public Equipment create_equipment(String[] item) {
		Equipment equipment = new Equipment();
		equipment.initialize_object(Integer.parseInt(item[0]), item);
		return equipment;
	}

	public void add_item(String[] item) { // Use this to initialize items
		Equipment equipment = create_equipment(item);
		_inventory_list.add(equipment);
		if (_locations.indexOf((String)(equipment.return_by_index(3))) < 0) {
			_locations.add((String)equipment.return_by_index(3));
		}
		_number_of_items = _number_of_items + 1;
	}

	public void initialize_inventory(ArrayList <String[]> item_list) {
		for (int i = 0; i < item_list.size(); i++) {
			if (i == 0) {
				initialize_header(item_list.get(i));
			}
			else {
				add_item(item_list.get(i));
			}
		}
	}

	public void sort_inventory(int criterion, int direction) { // 0 for ascending, 1 for descending

		Comparator <Equipment> comparator;

		if (criterion == 0) {
			comparator = (e1, e2) -> {
				if (direction == 0) {
					return ((Integer)(e1.return_by_index(criterion))).compareTo((Integer)(e2.return_by_index(criterion)));
				}
				else {
					return ((Integer)(e2.return_by_index(criterion))).compareTo((Integer)(e1.return_by_index(criterion)));
				}
			};
		}
		else if (criterion == 1) {
			comparator = (e1, e2) -> {
				int res = String.CASE_INSENSITIVE_ORDER.compare((String)(e1.return_by_index(criterion)), (String)(e2.return_by_index(criterion)));
				if (res == 0) {
					res = ((e1.return_by_index(criterion)).toString()).compareTo((e2.return_by_index(criterion)).toString());
				}
				if (direction == 0) {
					return res;
				}
				else {
					return -res;
				}
			};
		}
		else if (criterion != 4 && criterion != 5) {
			comparator = (e1, e2) -> {
				if (direction == 0) {
					return ((String)(e1.return_by_index(criterion))).compareTo((String)(e2.return_by_index(criterion)));
				}
				else {
					return ((String)(e2.return_by_index(criterion))).compareTo((String)(e1.return_by_index(criterion)));
				}
			};
		}
		else {
			comparator = (e1, e2) -> {
				String datetime_e1 = (String) e1.return_by_index(criterion);
				String datetime_e2 = (String) e2.return_by_index(criterion);
				DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
				LocalDateTime e1_details = LocalDateTime.parse(datetime_e1, date_time_formatter);
				LocalDateTime e2_details = LocalDateTime.parse(datetime_e2, date_time_formatter);
				if (direction == 0) {
					return e1_details.compareTo(e2_details);
				}
				else {
					return e2_details.compareTo(e1_details);
				}
			};
		}
		_inventory_list.sort(comparator);
	}

	// Begin printing of sizes //
	public int criteria_size() {
		return _number_of_parameters;
	}

	public int inventory_size() {
		return _number_of_items;
	}
	// End printing of sizes //

	/*
		For abstraction purposes
	 */
	public Criterion_Key return_parameters() {
		return _header;
	}
	
	public ArrayList return_items() {
		return _inventory_list;
	}

	public ArrayList return_locations() {
		return _locations;
	}

	public ArrayList return_inventory() {
		ArrayList result = new ArrayList();
		result.add(return_parameters());
		result.add(return_items());
		return result;
	}
	// End return for manipulation //

	// Begin print for display //
	public void print_parameters() {
		int total_len;
		for (int i = 0; i < criteria_size(); i++) {
			String criterion = _header.obtain_by_index(i);
			if (i == 0) {
				total_len = 20;
			}
			else {
				total_len = 25;
			}
			while (criterion.length() < total_len) {
				criterion = criterion + " ";
			}
			System.out.print(criterion);
		}
	}
	
	public void print_items() {
		int total_len;
		for (int i = 0; i < inventory_size(); i++) {
			ArrayList equip = _inventory_list.get(i).return_properties();
			for (int j = 0; j < equip.size(); j++) {
				String criterion;
				if (j == 0) {
					criterion = equip.get(j).toString();
				}
				else {
					criterion = (String)equip.get(j);
				}
				if (j == 0 || criterion.equals("Location")) {
					total_len = 20;
				}
				else {
					total_len = 25;
				}
				while (criterion.length() < total_len) {
					criterion = criterion + " ";
				}
				System.out.print(criterion);
			}
			System.out.println();
		}
	}

	public void print_inventory() {
		print_parameters();
		System.out.println();
		print_items();
	}
	// End print for display //

	// Remove item //
	public void remove_item(String[] e) {
		Equipment equipment = new Equipment();
		equipment.initialize_object(Integer.parseInt(e[0]), e);
		for (int i = 0; i < inventory_size(); i++) {
			if (equipment.return_properties().equals(_inventory_list.get(i).return_properties())) {
				_inventory_list.remove(i);
				_number_of_items--;
				break;
			}
		}
	}
	// End item removal //

	public ArrayList find_name_of_items() {
		ArrayList<String> items = new ArrayList<>();

		for (Equipment item : _inventory_list) {
			if (items.contains((String) (item.return_by_index(1))) == false) {
				items.add((String) item.return_by_index(1));
			}
		}
		return items;
	}
	/*
		find_expired_items(date, time) allows the user to find all items that will expire before or during that date
		(i.e. the items have expired).
		Sorting by expiry date occurs because logically, the user should be finding the items based on the ones that
		expire the earliest. This gives the indication how backdated the inventories currently are so that the user
		can determine the immediate urgency. A print sentence of course, denotes the best outcome where nothing has expired
		relative to the given date time.
	 */
	public ArrayList find_expired_items(String date, String time) {
		String datetime_in = date + " " + time;
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
		LocalDateTime expire_in = LocalDateTime.parse(datetime_in, date_time_formatter);
		String expiry_in = expire_in.format(date_time_formatter);

		ArrayList <Equipment> expired = new ArrayList <> ();

		for (int i = 0; i < inventory_size(); i++) {
			String datetime = ((String)_inventory_list.get(i).return_by_index(5));
			LocalDateTime expiry_eq = LocalDateTime.parse(datetime, date_time_formatter);
			if (expiry_eq.compareTo(expire_in) <= 0) {
				expired.add(_inventory_list.get(i));
			}
		}

		Comparator <Equipment> comparator = (e1, e2) -> {
			String datetime_e1 = (String) e1.return_by_index(5);
			String datetime_e2 = (String) e2.return_by_index(5);
			LocalDateTime e1_details = LocalDateTime.parse(datetime_e1, date_time_formatter);
			LocalDateTime e2_details = LocalDateTime.parse(datetime_e2, date_time_formatter);
			return e1_details.compareTo(e2_details);
		};

		expired.sort(comparator);

		if (expired.size() == 0) {
			System.out.println("NOTHING HAS EXPIRED AS OF " + expiry_in);
		}
		else {
			for (int i = 0; i < expired.size(); i++) {
				System.out.println(expired.get(i).return_properties());
			}
		}
		return expired;
	}
	// End Find Expired Items

	/*
		Find expiring items. This is used to inform the user about the items that will expire, if given a particular date.
		Similarly, this denotes the sense of urgency of the updates required because items are sorted in latest expiring
		items. As such, the items that will expire the earliest will be detected in first glance.
	*/
	public ArrayList find_expiring_items(String date, String time) {
		String datetime = date + " " + time;
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
		LocalDateTime expire_on = LocalDateTime.parse(datetime, date_time_formatter);
		String expire = expire_on.format(date_time_formatter);

		ArrayList <Equipment> expiring = new ArrayList <> ();

		for (int i = 0; i < inventory_size(); i++) {
			String datetime_eq = ((String)_inventory_list.get(i).return_by_index(5));
			LocalDateTime expiry_eq = LocalDateTime.parse(datetime_eq, date_time_formatter);
			if (expiry_eq.compareTo(expire_on) > 0) {
				expiring.add(_inventory_list.get(i));
			}
		}

		Comparator <Equipment> comparator = (e1, e2) -> {
			String datetime_e1 = (String) e1.return_by_index(5);
			String datetime_e2 = (String) e2.return_by_index(5);
			LocalDateTime e1_details = LocalDateTime.parse(datetime_e1, date_time_formatter);
			LocalDateTime e2_details = LocalDateTime.parse(datetime_e2, date_time_formatter);
			return e1_details.compareTo(e2_details);
		};

		expiring.sort(comparator);

		if (expiring.size() == 0) {
			System.out.println("NOTHING HAS EXPIRED AS OF " + datetime);
		}
		else {
			for (int i = 0; i < expiring.size(); i++) {
				System.out.println(expiring.get(i).return_properties());
			}
		}
		return expiring;
	}
	// End Find Expiring Items //

	/*
		Find items by input date. Usefulness lies in accountability checks when there may be difficulties in verifying
		because there may be situations where the necessity of finding an item inserted within a date itself.
		Just call multiple dates when investigating multiple dates.
	*/
	public ArrayList find_by_input_datetime(String date, String time) {
		String datetime = date + " " + time;
		DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
		LocalDateTime expire_on = LocalDateTime.parse(datetime, date_time_formatter);
		String expire = expire_on.format(date_time_formatter);

		ArrayList <Equipment> entries = new ArrayList <> ();

		for (int i = 0; i < inventory_size(); i++) {
			if (((String)_inventory_list.get(i).return_by_index(4)).equals(datetime) == true) {
				entries.add(_inventory_list.get(i));
			}
		}

		if (entries.size() == 0) {
			System.out.println("NOTHING HAS BEEN INPUTTED AS OF " + datetime);
		}
		else {
			for (int i = 0; i < entries.size(); i++) {
				System.out.println(entries.get(i).return_properties());
			}
		}
		return entries;
	}
	// End Find By Input Date Expiring Items //

	/*
		Find items by name. Usefulness lies in avoiding having the user to run through an entire list of things to
		search for a set of items with the same name but different batch numbers and various other parameters.
		Once the names are obtained, sorting by indices can then be done. This allows user to see the multiple instances
		of the same item, sorted to obtain the outcomes he/ she wishes to have.
	*/
	public ArrayList find_by_name(String name) {
		ArrayList <Equipment> entries = new ArrayList <> ();

		for (int i = 0; i < inventory_size(); i++) {
			if (((String)_inventory_list.get(i).return_by_index(1)).equals(name) == true) {
				entries.add(_inventory_list.get(i));
			}
		}

		if (entries.size() == 0) {
			System.out.println("THERE IS NOTHING IN THIS INVENTORY WITH THE NAME: " + name);
		}
		else {
			for (int i = 0; i < entries.size(); i++) {
				System.out.println(entries.get(i).return_properties());
			}
		}
		return entries;
	}

	/*
		Find items by location. Usefulness lies in finding the existence of items within a particular storage space and
		thereof, its own existence. This allows immediate acquisition of results if an individual was investigating a GIVEN
		storage space.
	*/
	public ArrayList find_by_location(String sloc) {
		ArrayList <Equipment> entries = new ArrayList <> ();

		for (int i = 0; i < inventory_size(); i++) {
			if (((String)_inventory_list.get(i).return_by_index(3)).equals(sloc)) {
				entries.add(_inventory_list.get(i));
			}
		}

		if (entries.size() == 0) {
			System.out.println("THERE IS NOTHING IN THIS INVENTORY INSIDE: " + sloc);
		}
		else {
			for (int i = 0; i < entries.size(); i++) {
				System.out.println(entries.get(i).return_properties());
			}
		}
		return entries;
	}

	public Map compress_by_names() {
		ArrayList <String> names = find_name_of_items();
		Map <String, ArrayList<Equipment>> compressed_lst = new TreeMap <> ();

		for (Equipment equipment: _inventory_list) {
			if (names.contains((String)equipment.return_by_index(1)) == true) {
				if (compressed_lst.containsKey((String)equipment.return_by_index(1)) == false) {
					ArrayList <Equipment> items = new ArrayList <> ();
					items.add(equipment);
					compressed_lst.put((String)equipment.return_by_index(1), items);
				}
				else {
					compressed_lst.get(equipment.return_by_index(1)).add(equipment);
				}
			}
		}
		return compressed_lst;
	}

	public Map compress_by_locations() {
		Map <String, ArrayList<Equipment>> compressed_lst = new TreeMap <> ();
		for (Equipment equipment: _inventory_list) {
			if (_locations.contains((String) equipment.return_by_index(3)) == true) {
				if (compressed_lst.containsKey((String) equipment.return_by_index(3)) == false) {
					ArrayList<Equipment> items = new ArrayList<>();
					items.add(equipment);
					compressed_lst.put((String) equipment.return_by_index(3), items);
				}
				else {
					compressed_lst.get(equipment.return_by_index(3)).add(equipment);
				}
			}
		}
		return compressed_lst;
	}

	public void printMap(Map <String, ArrayList <Equipment>> compressed_lst, String criterion) {

		Set <String> lst_keys = compressed_lst.keySet();
		int total_len;
		for (String key: lst_keys) {
			System.out.println("======================================");
			System.out.println(criterion + ": " + key);
			System.out.println("======================================");
			print_parameters();
			System.out.println();
			for (Equipment equip: compressed_lst.get(key)) {
				ArrayList prop = equip.return_properties();
				for (int j = 0; j < prop.size(); j++) {
					String p;
					if (j == 0) {
						p = prop.get(j).toString();
					}
					else {
						p = (String)prop.get(j);
					}
					if (j == 0) {
						total_len = 20;
					}
					else {
						total_len = 25;
					}
					while (p.length() < total_len) {
						p = p + " ";
					}
					System.out.print(p);
				}
				System.out.println();
			}
		}
	}


}