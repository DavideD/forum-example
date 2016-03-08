package hibernate.forum.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.cfg.OgmConfiguration;

public class Getstudentdetails {

	private static SessionFactory sessionFactory;

	private static void initSessionFactory() {
		HOGMDao dd = new HOGMDao();
		OgmConfiguration cfgogm = dd.hogmdao( "student" );
		cfgogm.addAnnotatedClass( Student.class );

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings( cfgogm.getProperties() ).build();
		sessionFactory = cfgogm.buildSessionFactory( registry );
	}

	private static void closeSessionFactory() {
		sessionFactory.close();
	}

	public void getStudent() {
		Session cs = null;
		try {
			cs = sessionFactory.openSession();
			Transaction tx = cs.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Student> list = cs.createQuery( "FROM Student" ).list();

			tx.commit();

			for ( Student student : list ) {
				System.out.println( "Student_Id : " + student.getId() );
				System.out.println( "Student Name : " + student.getStd_name() );
			}
		}
		finally {
			cs.close();
		}
	}

	private static void createStudent(String name) {
		Session cs = null;
		Transaction tx = null;
		try {
			cs = sessionFactory.openSession();
			tx = cs.beginTransaction();
			Student student = new Student();
			student.setStd_name( name );
			cs.persist( student );
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
		finally {
			cs.close();
		}
	}

	public static void main(String[] args) {
		initSessionFactory();
		try {
			createStudent( "John Doe" );
			createStudent( "Jane Doe" );
			new Getstudentdetails().getStudent();
		}
		finally {
			closeSessionFactory();
		}
	}
}
