package dhruvi.hibernate;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{
	
	private static final Logger logger = Logger.getLogger(App.class);
	public static void main( String[] args )
    {
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();
//    	Session session = sf.openSession();
//    	Transaction tnx = session.beginTransaction();
		
		
//    	Employee emp = new Employee();
//    	emp.setEmpID(3);
//    	emp.setFname("Jaydeep");
//    	emp.setLname("Patel");
//    	
    	
//    	Student student = new Student();
//    	student.setStudentID(2);
//    	student.setName("Dhruvi");
//    	student.setSubject("java");
//    	
    	
//    	session.save(emp);
//    	session.save(student);
//    	
//    	tnx.commit();
    			
		BasicConfigurator.configure();
		ApplicationContext context = new ClassPathXmlApplicationContext("student.config.xml");
		Student s1 = context.getBean("Dhruvi", Student.class);
		Student s2 = context.getBean("Bhoomi", Student.class);
		Student s3 = context.getBean("Yash", Student.class);
		Student s4 = context.getBean("Brijesh", Student.class);
		
		// add Student
		StudentDao stDao = context.getBean("studentDao", StudentDao.class);
		stDao.addStudent(s1);
		stDao.addStudent(s2);
		stDao.addStudent(s3);
		stDao.addStudent(s4);
		
		logger.info("added students");
		
		// update student
		s2.setName("Bhoomika");
		stDao.updateStudent(s2);
		
		logger.info("s2 updated");
		
		
		// delete user
		stDao.removeStudent(s2);
		logger.info(s2 + " removed successfully");
		
		
		// get user by id
//		Student student = stDao.getStudentById(13);
//		logger.info(student.getStudentID());
//		logger.info(student.getName());
//		logger.info(student.getSubject());
//		
		
		// getting all students
		List<Student> students = stDao.getAllStudents();
		logger.info(students.stream().map(st -> st.getName()).collect(Collectors.toList()));
		
		
		// get Students by name
		// using hql
		List<Student> stList = stDao.getStudentsByName("Yash");
		logger.info("id of students whose name is Yash");
		logger.info(stList.stream().map(st -> st.getStudentID()).collect(Collectors.toList()));
		
		// get subjects of all students
		// hcql projection
		List<String> subjects = stDao.getSubjects();
		logger.info("Subjects = " + subjects);
		
    }
}
