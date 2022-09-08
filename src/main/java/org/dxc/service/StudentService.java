package org.dxc.service;

import java.util.List;

import org.dxc.Bean.Student;
import org.dxc.factorydesign.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService implements StudentServiceImplementation {	
	
	@SuppressWarnings("deprecation")
	public Integer insert(Student s) {
		Transaction tx = null;
		SessionFactory factory = HibernateFactory.getFactoryObject();
		Session session = factory.openSession();
		Integer sId = null;
		try {
			tx = session.beginTransaction();
			sId = (Integer) session.save(s);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return sId;
	}

	@SuppressWarnings("deprecation")
	public List<Student> getAllStudent() {
		SessionFactory factory = HibernateFactory.getFactoryObject();

		Session session = factory.openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<Student> students = session.createQuery("FROM Student").list();

			tx.commit();
			return students;

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}

		return null;
	}

	public Student getById(int id) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateFactory.getFactoryObject();

		Session session = factory.openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			tx.commit();
			return student;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}

	public void updateRecord(Student s) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateFactory.getFactoryObject();

		Session session = factory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, s.getSid());
			student.setSname(s.getSname());
			student.setSaddress(s.getSaddress());
			session.update(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteRecord(int id) {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateFactory.getFactoryObject();

		Session session = factory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			session.delete(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


}
