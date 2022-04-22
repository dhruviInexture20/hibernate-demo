package dhruvi.hibernate;


import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class StudentDao {
	
	private static final Logger logger = Logger.getLogger(StudentDao.class);
	
	
	private SessionFactory sf = new Configuration().configure().buildSessionFactory();
	private Transaction tnx;
	private Session session;
	
	
	void addStudent(Student student){
		session = sf.openSession();
		tnx = session.beginTransaction();
		
		session.save(student);
		tnx.commit();
		logger.info(student.getName() +" added successfully" );
		session.close();
	}
	
	void updateStudent(Student student) {
		session = sf.openSession();
		tnx = session.beginTransaction();
		
		session.update(student);
		logger.info(student.getName() + " updated successfully");
		
		tnx.commit();
		session.close();
		
	}
	
	
	Student getStudentById(int id) {
		session = sf.openSession();
		tnx = session.beginTransaction();
		
		Student student = session.get(Student.class, id);
		
		tnx.commit();
		logger.info("getting student by id");
		session.close();
		
		return student;
	}
	
	
	@SuppressWarnings("unchecked")
	List<Student> getStudentsByName(String name) {
		session = sf.openSession();
		TypedQuery<Student> q = session.createQuery("FROM Student S WHERE S.name = :name");
		q.setParameter("name", name);
		
		List<Student> results = q.getResultList();
		
		return results;
	}
	
	void removeStudent(Student student) {
		session = sf.openSession();
		tnx = session.beginTransaction();
		
		session.remove(student);
		
		tnx.commit();
		logger.info(student.getName()+ " removed successfully");
		session.close();
		
	}
	
	
	@SuppressWarnings("unchecked")
	List<Student> getAllStudents() {
        session = sf.openSession();
        List<Student> studentsList = session.createQuery("from Student").list();
        
        logger.info(studentsList.size() + " students are available in database");
        return studentsList;
    }
	
	List<String> getSubjects(){
		session = sf.openSession();
		

//		Criteria c = session.createCriteria(Student.class);
//		c.setProjection(Projections.property("subject"));
//		List<String> subjectList = c.list();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Student> student = criteria.from(Student.class);
		criteria.select(student.get("name"));
		
		
		List<String> subjectList = session.createQuery(criteria).getResultList();
		
		return subjectList;
	}
	
}
