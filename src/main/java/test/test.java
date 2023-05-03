package test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stu.Model.Lop;
import com.stu.Util.HibernateUtil;

public class test {
	public static void main(String[] args) {
		SessionFactory fatory = HibernateUtil.getSessionFactory();
		List<Lop> ds = new ArrayList<Lop>();
		Session ses = fatory.openSession();
		ds = ses.createQuery("from Lop").list();
		for(Lop lop: ds) {
			System.out.println(lop);
		}
		ses.close();
	}
}
