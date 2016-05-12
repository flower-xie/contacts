package model;

import java.util.Date;

public class People {
	private int id;
	private String name;
	private String sex;
	private String number;
	private Date birthday;
	private String email;
	private String habit;

	public People() {//无参数的构造函数

	}

	public People(int id,String name,String sex,String number,Date birthday,String email,String habit) {
		this.email = email;
		this.sex=sex;
		this.birthday = birthday;
		this.habit = habit;
		this.name = name;
		this.number = number;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}
