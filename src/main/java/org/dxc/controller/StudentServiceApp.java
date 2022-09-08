package org.dxc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.dxc.Bean.Student;
import org.dxc.factorydesign.ServiceFactory;
import org.dxc.service.StudentService;

public class StudentServiceApp {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to student services");
		System.out.println();
		while(true) {
			System.out.println("Enter the corresponding number for the following actions: ");
			System.out.println("(1) insert StudentRecord");
			System.out.println("(2) get Student Details");
			System.out.println("(3) list Student Details");
			System.out.println("(4) delete Record");
			System.out.println("(5) update Record");
			System.out.println("(6) Exit App");
	
			int selection = scan.nextInt();
			int sid = 0;
			String sname;
			String saddress;
			Student std = new Student();
			StudentService service = ServiceFactory.getServiceObject();
	
			switch (selection) {
			case 1:
				System.out.println("Student name: ");
				sname = scan.next();
				System.out.println("Student address: ");
				saddress = scan.next();
				std.setSname(sname);
				std.setSaddress(saddress);
	
				System.out.println((service.insert(std) != 0) ? "Student Details Uploaded" : "Insertion Failed");
	
				scan.close();
				break;
			case 2:
				System.out.println("Student id: ");
				sid = scan.nextInt();
				std.setSid(sid);
	
				std = service.getById(sid);
				System.out.println("Student Name: " + std.getSname());
				System.out.println("Address: " + std.getSaddress());
				System.out.println("===================================");
				System.out.println("");
				break;
			case 3:
				List<Student> students = service.getAllStudent();
				for (Iterator iterator = students.iterator(); iterator.hasNext();) {
					Student student = (Student) iterator.next();
					System.out.println("Student Id: " + student.getSid());
					System.out.println("Student Name: " + student.getSname());
					System.out.println("Address: " + student.getSaddress());
					System.out.println("===================================");
					System.out.println("");
				}
				break;
			case 4:
				System.out.println("Student id to delete ");
				sid = scan.nextInt();
				std.setSid(sid);
	
				service.deleteRecord(sid);
				System.out.println("record deleted");
				System.out.println("");
				break;
			case 5:
				System.out.println("Student ID to update: ");
				sid = scan.nextInt();
				std = service.getById(sid);
				BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("Student Name[old name:" + std.getSname() + "]:");
				sname = b.readLine();
				if (sname.equals(null) || sname.equals("")) {
					sname = std.getSname();
				}
				
				System.out.print("Student Address[old address:" + std.getSaddress() + "]:");
				saddress = b.readLine();
				if (saddress.equals(null) || saddress.equals("")) {
					saddress = std.getSaddress();
				}
				
				std = new Student();
				std.setSid(sid);
				std.setSname(sname);
				std.setSaddress(saddress);
	
				service.updateRecord(std);
				System.out.println("Record updated");
				System.out.println("");
				break;
			case 6:
				System.out.println("Goodbye");
				System.exit(0);
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}

}
