package dhruvi.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;


@Entity
@Table(name="student200")
public class Student {
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	@Id
	@SequenceGenerator(initialValue=100000, 
     name = "partner_sequence", 
     sequenceName="partner_sequence")
	@GeneratedValue(generator="partner_sequence")
	@Column(name="student_id")
	private int studentID;
	private String name;
	private String subject;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
}
