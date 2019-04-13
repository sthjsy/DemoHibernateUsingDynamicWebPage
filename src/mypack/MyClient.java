package mypack;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MyClient extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		Integer eno=Integer.parseInt(req.getParameter("eno"));
		String ename=req.getParameter("ename");
		Integer esal=Integer.parseInt(req.getParameter("esal"));
		
		Emp emp=new Emp();
		emp.setEno(eno);
		emp.setEname(ename);
		emp.setEsal(esal);
		
		Configuration cfg=new Configuration();
		cfg.configure("DemoExample.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		//s.save(emp);
		//s.update(emp);
		//s.delete(emp);
		Object o=s.load(Emp.class, eno);
		Emp e=(Emp)o;
		out.println("Emp No. "+e.getEno()+"<br>"+"Emp Name "+e.getEname()+"<br>"+"Emp Salary "+e.getEsal()+"<br>");
		t.commit();
		
		s.close();
		sf.close();
		
		out.println("Save..........");
	}

}
