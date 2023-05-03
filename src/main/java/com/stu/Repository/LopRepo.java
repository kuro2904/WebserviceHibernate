package com.stu.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stu.Model.Lop;
import com.stu.Model.Sinhvien;
import com.stu.Util.HibernateUtil;

public class LopRepo {
	public LopRepo() {}
	private SessionFactory factory = HibernateUtil.getSessionFactory();
	public List<Lop>getAll(){
		List<Lop>dsLop = new ArrayList<Lop>();
		Session ses = factory.openSession();
		String sql = "from Lop";
		dsLop = ses.createQuery(sql).list();
		ses.close();
		return dsLop;
	}
	public Lop findLop(int id) {
		Lop lop = new Lop();
		Session ses = factory.openSession();
		lop = (Lop)ses.get(Lop.class, id);
		ses.close();
		return lop;
	}
	public void insertLop(String jsonSring) {
		Lop lop = new Lop();
		Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		lop = gs.fromJson(jsonSring, Lop.class);
		Session ses = factory.openSession();
		Transaction tran = ses.beginTransaction();
		try {
			ses.save(lop);
			tran.commit();
			System.out.println("Add successful");
		}catch(Exception e) {
			if(tran != null) {
				tran.rollback();
			}
		}finally {
			ses.close();
		}
	}
	public void delete(String jsonString) {
		
		
	}
}
