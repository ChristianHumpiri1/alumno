package pe.edu.upeu.test;

import com.google.gson.Gson;

import pe.edu.upeu.dao.AlumnoDao;
import pe.edu.upeu.dao.EscuelaDao;
import pe.edu.upeu.daoimp.AlumnoDaoImp;
import pe.edu.upeu.utils.Conexion;


public class Test {
	private static AlumnoDao pd = new AlumnoDaoImp();
	private static Gson g = new Gson();
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	 listarP();
		}
	  static void listarP() {
		  System.out.println(g.toJson(pd.readAll()));
	  }

}
