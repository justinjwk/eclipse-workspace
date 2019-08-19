package edu.jhu.web.mod9.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

    private String name;
    private String email;
    private String status;
    private String[] courses;
    private static final String[] allCourses = {"A1 - Web Services", 
    		"A2 - J2EE Design Patterns", "A3 - Service Oriented Architectures", 
    		"A4 - Enterprise Service Bus", "A5 - Secure Messaging", 
    		"A6 - Web Service Securit"};
    private static final String[] allStatus = {"JHU Employee", 
    		"JHU Student", "Speaker", "Other"};
    private double courseFee;
    private double hotel;
    private boolean hotelChecked = false;
    private boolean parkingChecked = false;
    private double parking;
    private double total;

    public User() {
        name = "";
        email = "";
        status = null;
        courses = null;
        courseFee = 0.00;
        hotel = 0.00;
        hotelChecked = false;
        parking = 0.00;
        parkingChecked = false;
        total = 0.00;
    }

	public User(String name, String email, String status, 
			String[] courses, double hotel, double parking) {
		super();
		this.name = name;
		this.email = email;
		this.setStatus(status);
		this.courses = courses;
		this.setHotel(hotel);
		this.setParking(parking);
	}
	
	// When Hotel is selected, set hotelChecked variable to True
	public void setHotel(double hotel) {
		this.hotel = hotel;
		if (hotel > 0.0) {
			hotelChecked = true;
		}
		else {
			hotelChecked = false;
		}
	}
	
	// When Parking and Hotel both are selected, the parking is set to $0.00
	// When Parking is selected and Hotel is not selected, the Parking fee is $10.00
	public void setParking(double parking) {
		// When parking is selected
		if (parking > 0.0) {
			parkingChecked = true;
			// and hotel is selected as well, the parking fee is $0.00
			if (hotelChecked) {
				this.parking = 0.0;
			}
			// and hotel is not selected, the parking fee is $10.00
			else {
				this.parking = parking;
			}
		}
		// When parking is not selected
		else {
			parkingChecked = false;
			this.parking = parking;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.setCourseFee(status);
		this.status = status;
	}

	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] courses) {
		this.courses = courses;
	}

	public double getCourseFee() {
		return courseFee;
	}
	
	public void setCourseFee(String status) {
		if (status.equals("JHU Employee")) {
			this.courseFee = 850.00;
		}
		else if (status.equals("JHU Student")) {
			this.courseFee = 1000.00;
		}
		else if (status.equals("Speaker")) {
			this.courseFee = 0.00;
		}
		else {
			this.courseFee = 1350.00;
		}
	}

	public double getHotel() {
		return hotel;
	}

	public double getParking() {
		return parking;
	}

	public double getTotal() {
		return total;
	}
	
	public void calculateTotal() {
		int numOfCourse = courses.length;
		
		if (hotel > 0.0) {
			
		}
		total = (numOfCourse * courseFee) + hotel +parking;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void addTotal(double in) {
		total = total + in;
	}
	
	public void substractTotal(double in) {
		total = total - in;
	}
	
	public void removeCourse(String course) {
		List<String> list = new ArrayList<String>(Arrays.asList(this.courses));
		list.remove(course);
		this.courses = list.toArray(new String[0]);
	}

	public String[] getAllCourses() {
		return allCourses;
	}
	
	public String[] getAllStatus() {
		return allStatus;
	}

	public boolean isHotelChecked() {
		return hotelChecked;
	}

	public void setHotelChecked(boolean hotelChecked) {
		this.hotelChecked = hotelChecked;
	}

	public boolean isParkingChecked() {
		return parkingChecked;
	}

	public void setParkingChecked(boolean parkingChecked) {
		this.parkingChecked = parkingChecked;
	}
}
