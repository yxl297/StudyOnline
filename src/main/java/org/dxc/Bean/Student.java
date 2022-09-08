package org.dxc.Bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;
	
	@Column(name="sname")
	private String sname;
	
	@Column(name="saddress")
	private String saddress;
	
	public Student() {}
	public Student(String studentName, String studentAddress) {
		this.sname = studentName;
		this.saddress = studentAddress;
	}
	
	public int getSid() {
		return sid;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	
	
}
