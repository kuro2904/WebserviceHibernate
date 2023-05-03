package com.stu.WSLT;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stu.Model.Lop;
import com.stu.Model.Sinhvien;
import com.stu.Repository.LopRepo;
import com.stu.Repository.SinhvienRepo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	LopRepo lopRep = new LopRepo();
	SinhvienRepo svRepo = new SinhvienRepo();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws JAXBException 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getTrung() {
//		Gson s = new Gson();
//		Test2 get = new Test2("Trung","23");
//		return Response.ok(s.toJson(get),MediaType.APPLICATION_JSON).build();
//	}
	
	@GET
	@Path("/lop")
	public Response getAllLop() {
		List<Lop>dsLop = lopRep.getAll();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		for(Lop lop : dsLop) {
			System.out.println(lop);
		}
		return Response.ok(gson.toJson(dsLop),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/lop={id}")
	public Response getLop(@PathParam("id") int id) {
		Lop lop = new Lop();
		lop = lopRep.findLop(id);
		Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		if(lop != null) {
			return Response.ok(gs.toJson(lop),MediaType.APPLICATION_JSON).build();
		}else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("/sinhvien")
	public Response getAllSinhvien() {
		List<Sinhvien>dssv = svRepo.getAllSinhvien();
		Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		for(Sinhvien sv: dssv) {
			System.out.println(sv);
		}
		return Response.ok(gs.toJson(dssv),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/sinhvien={id}")
	public Response getSinhvien(@PathParam("id") int mssv) {
		Sinhvien sv = new Sinhvien();
		sv = svRepo.findSV(mssv);
		if(sv!=null) {
			Gson json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			return Response.ok(json.toJson(sv),MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("/sinhvien")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertSinhvien(String jsonString) {
		 svRepo.insertSV(jsonString);
		return Response.ok().build();
	}
	
	@POST
	@Path("/lop")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertLop(String jsonSring) {
		try {lopRep.insertLop(jsonSring);
		return Response.ok().build();
		}catch(Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to insert Lop:" + e.getMessage())
					.build();
		}
	}
	
	@DELETE
	@Path("/lop={id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLop(String jsonString) {
		lopRep.delete(jsonString);
		return null;
	}
	
}
