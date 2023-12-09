package com.prepared.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sql.ConnectionPoolDataSource;

import com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource;
import com.prepared.bean.Person;
import com.prepared.service.InsertOperation;
import com.prepared.service.SelectOperation;

public class ControllerApp {

	private static Scanner scanner = null;

	public static void main(String[] args) throws IOException {
		Person student = new Person();
		scanner = new Scanner(System.in);
		System.out.print("Please enter student First Name : ");
		String firstName = scanner.next();
		student.setPerson_name(firstName);
		File file = new File("C:\\Users\\srinivas\\Downloads\\dhoni.jpg");
		FileInputStream fis = new FileInputStream(file);
		student.setPerson_image(fis);

		System.out.println("Please enter choice of your operation" + "\n" + "1. INSERT Operation\n"
				+ "2. SELECT Operation\n" + "3. DELETE Operation\n" + "4. UPDATE Operation");

		int expression = scanner.nextInt();
		switch (expression) {
		case 1:
			InsertOperation.insertOperation(student);
			break;

		case 2:
			System.out.print("Please enter student ID : ");
			String person_name = scanner.next();
			student.setPerson_name(person_name);
			SelectOperation.selectOperation(student.getPerson_name());
			break;

		default:
			break;
		}

	}

}
