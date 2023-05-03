package com.stu.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stu.Model.Sinhvien;
import com.stu.Util.HibernateUtil;

public class SinhvienRepo {
	private SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public SinhvienRepo() {}
	public List<Sinhvien>getAllSinhvien(){
		List<Sinhvien>dssv = new ArrayList<Sinhvien>();
		Session ses = factory.openSession();
		dssv = ses.createQuery("from Sinhvien").list();
		return dssv;
	}
	
	public Sinhvien findSV(int mssv) {
		Session ses = factory.openSession();
		Sinhvien sv = new Sinhvien();
		sv =(Sinhvien) ses.get(Sinhvien.class, mssv);
		return sv;
	}
	
	public void insertSV(String jsonString) {
		Sinhvien sv = new Sinhvien();
		Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		sv = gs.fromJson(jsonString, Sinhvien.class);
		Session ses = factory.openSession();
		Transaction tran = ses.beginTransaction();
		try {
			ses.save(sv);
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
}
