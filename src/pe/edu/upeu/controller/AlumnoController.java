package pe.edu.upeu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.upeu.dao.AlumnoDao;
import pe.edu.upeu.dao.EscuelaDao;
import pe.edu.upeu.daoimp.AlumnoDaoImp;
import pe.edu.upeu.daoimp.EscuelaDaoImp;
import pe.edu.upeu.entity.Alumno;
import pe.edu.upeu.entity.Escuela;

/**
 * Servlet implementation class AlumnoController
 */
@WebServlet("/ac")
public class AlumnoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AlumnoDao pd1 = new AlumnoDaoImp();
	private EscuelaDao cd = new EscuelaDaoImp();
	private Gson g = new Gson();
	int ida, idesc; 
	String correo,  telefono , apellnombres;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	int op = Integer.parseInt(request.getParameter("opc"));
	switch(op) {
	case 1: listar escuela
			out.println(g.toJson(cd.readAll()));
			break;
	case 2: Listar alumnos
			out.println(g.toJson(pd1.readAll()));
		    break;
	case 3: registrar alumnos
			pd1.create(new Alumno(0,
			Integer.parseInt(request.getParameter("escuela")),request.getParameter("apellnombres"),
			(request.getParameter("correo")),(request.getParameter("telefono"))));
	       out.println(g.toJson("Registro guardado correctamente"));
		         break;
	case 4: Buscar alumno 
			out.println(g.toJson(pd1.read(Integer.parseInt(request.getParameter("id")))));
    break; 
	case 5: Eliminar alumno
		int x = pd1.delete(Integer.parseInt(request.getParameter("ida")));
			out.println(g.toJson(x));
    break;
	case 6: Modificar alumno
			ida = Integer.parseInt(request.getParameter("ida"));
			idesc = Integer.parseInt(request.getParameter("idesc"));
			apellnombres = request.getParameter("apellnombres");
			correo = request.getParameter("correo");				
			telefono = request.getParameter("telefono");				
			out.println(g.toJson(pd1.update(new Alumno(ida, idesc, apellnombres, correo, telefono))));
    break;
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
