package criteria;

import java.util.*;

public class Criterion_Key {

	private ArrayList <String> _criteria = new ArrayList <> ();
	
	public ArrayList return_criteria() {
		return _criteria;
	}

	public void print_criterion_key() {
		System.out.println(return_criteria());
	}

	public void add_criterion(String criterion) { // Add criterion.
		if (_criteria.contains(criterion)) {
			System.out.println(criterion + " is already accounted for!");
		}
		else {
			_criteria.add(criterion);
		}
	}

	public void remove_criterion(String criterion) { // Remove criterion
		if (_criteria.contains(criterion)) {
			_criteria.remove(criterion);
			System.out.println(criterion + " has been removed from the key.");
		}
	}
	
	public void swap_criteria(String src, String dst) { // Swap by substrings
		int src_pos = _criteria.indexOf(src);
		int dst_pos = _criteria.indexOf(dst);

		if (src_pos == -1 && dst_pos == -1) {
			System.out.println("Both " + src + " and " + dst + " do not exist within the key!");
		}
		else if (src_pos == -1) {
			System.out.println(src + " does not exist within the key!");
		}
		else if (dst_pos == -1) {
			System.out.println(dst + " does not exist within the key!");
		}
		else {
			int src_len = src.length();
			int dst_len = dst.length();
			_criteria.set(src_pos, _criteria.get(dst_pos) + _criteria.get(src_pos));
			_criteria.set(dst_pos, _criteria.get(src_pos).substring(dst_len, src_len + dst_len));
			_criteria.set(src_pos, _criteria.get(src_pos).substring(0, dst_len));
		}
	}
	
	public String obtain_by_index(int index) { // Gets the criterion object out (just return string directly)
		if (index >= _criteria.size() || index < 0) {
			System.out.println("Out of index!");
			return null;
		}
			return _criteria.get(index);
	}
	
	public Object obtain_by_name(String criterion) { // To obtain the index number of a criterion by knowing what the String name is
		
		int index = _criteria.indexOf(criterion);
		
		if (index == -1) {
			System.out.println("Criterion does not exist!");
			return null;
		}
		else {
			return _criteria.get(index);
		}
	}

	public int number_of_parameters() {
		return _criteria.size();
	}

}
