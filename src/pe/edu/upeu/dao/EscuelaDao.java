package pe.edu.upeu.dao;

import pe.edu.upeu.entity.Escuela;
import java.util.List;



public interface EscuelaDao {
	public int create(Escuela u);
	public int update(Escuela u);
	public int delete(int id);
	public Escuela read(int id);
	public List<Escuela> readAll();


}
