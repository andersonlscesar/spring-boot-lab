package com.luv2cod.cruddemo;

import com.luv2cod.cruddemo.dao.StudentDAO;
import com.luv2cod.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createMultipleStudents(studentDAO);
		};
	}


	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("DELETING ALL STUDENTS");
		int rows = studentDAO.deleteAll();
		System.out.printf("ALL STUDENTS WERE DELETED. ROWS AFECTED: " + rows);

	}


	private void deleteStudent(StudentDAO studentDAO) {
		System.out.println("DELETING STUDENT");
		int id = 5;
		studentDAO.delete(id);;
	}

	private void updateStudent(StudentDAO studentDAO) {
		System.out.println("UPDATING STUDENT");
		Student tempStudent = studentDAO.findById(1);
		tempStudent.setLastName("Adams");
		studentDAO.update(tempStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		System.out.println("-------------- RETRIEVING STUDENTS BY LAST NAME ------------------");
		List<Student> students = studentDAO.findByLastName("Duck");

		for (Student temp: students) {
			System.out.println(temp);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		System.out.println("------------------- RETRIEVING ALL STUDENTS ------------------- ");
		List<Student> students = studentDAO.findAll();

		for (Student tempStudent: students) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("CREATING NEW STUDENT OBJECT");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");
		System.out.println("SAVING THE STUDANT");
		studentDAO.save(tempStudent);
		int theId = tempStudent.getId();
		System.out.println("SAVED STUDENT ID: " + theId);
		Student myStudent = studentDAO.findById(theId);
		System.out.println("FOUND THE STUDENT: " + myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("CREATING NEW STUDENT OBJECT");
		Student tempStudent = new Student("Bryan", "Adam", "ba@gmail.com");
		System.out.println("SAVING THE STUDENT");
		studentDAO.save(tempStudent);
		System.out.println("SAVED STUDENT. GENERATED ID: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("CREATING 3 NEW STUDENTS OBJECT");
		Student tempStudent1 = new Student("John", "Snow", "js@gmail.com");
		Student tempStudent2 = new Student("Steve", "vai", "stevevai@gmail.com");
		Student tempStudent3 = new Student("Joe", "Satriani", "joesatriani@gmail.com");
		System.out.println("SAVE THE STUDENTS ... ");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

}
