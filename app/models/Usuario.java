package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Usuario extends Model{
	@Id
	public Integer id;
	public String nome;
	public String senha;
	@OneToMany(cascade=CascadeType.ALL)
	public List<Cd> cds = new ArrayList<Cd>();

	public static final Finder<Integer,Usuario> encontra = new Finder<>(Usuario.class);

	public static Usuario findById(String id){
		Integer i = Integer.valueOf(id);
		for(Usuario u : find.all()){
			if(u.id==i)
			   return u;
		}
	return null;
	}
	public static Usuario findByName(String nome){
		for(Usuario u : Usuario.find.all()){
			if(u.nome.equals(nome))
			   return u;
		}
	return null;
	}


}
