package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Cd extends Model{
	@Id
	public Integer id;
	@ManyToOne
	@Column(name = "usuario_id")
	public Usuario usuario;
	public String nome;
	public String descricao;

	public static final Finder<Integer,Cd> encontra = new Finder<>(Cd.class);

	public static Cd findById(String id){
		Integer i = Integer.valueOf(id);
		for(Cd d : find.all()){
			if(d.id==i)
			   return d;
		}
	return null;
	}
	public static Cd findByName(String nome){
		for(Cd cd : Cd.find.all()){
			if(d.nome.equals(nome))
			   return cd;
		}
	return null;
	}
}
