package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Student;

public class StudentSorter implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		return s1.getPrezime().compareToIgnoreCase(s2.getPrezime()) != 0
				? s1.getPrezime().compareToIgnoreCase(s2.getPrezime())
				: s1.getIme().compareToIgnoreCase(s2.getIme()); 
	}

}
