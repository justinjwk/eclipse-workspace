package edu.jhu.web.mod4.business;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String status;
    private String[] courses;
    private double courseFee;
    private double hotel;
    private double parking;
    private double total;

    public User() {
        name = "";
        email = "";
        status = "";
        courses = null;
        courseFee = 0.00;
        hotel = 0.00;
        parking = 0.00;
        total = 0.00;
    }

	public User(String name, String email, String status, String[] courses) {
		super();
		this.name = name;
		this.email = email;
		this.status = status;
		this.courses = courses;
		if (status.equals("JHU Employee")) {
			courseFee = 850.00;
		}
		else if (status.equals("JHU Student")) {
			courseFee = 1000.00;
		}
		else if (status.equals("Speaker")) {
			courseFee = 0.00;
		}
		else {
			courseFee = 1350.00;
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
		this.status = status;
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

	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}

	public double getHotel() {
		return hotel;
	}

	public void setHotel(double hotel) {
		this.hotel = hotel;
	}

	public double getParking() {
		return parking;
	}

	public void setParking(double parking) {
		this.parking = parking;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void addTotal(double in) {
		total = total + in;
	}
	


}