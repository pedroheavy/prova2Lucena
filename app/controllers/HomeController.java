package controllers;

import play.mvc.*;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
	@Inject
	FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
	Form usuarioForm = formFactory.form(Usuario.class);
        return ok(views.html.index.render(usuarioForm));
    }
    public Result createUsuario(){
	Form usuarioForm = formFactory.form(Usuario.class);
	return ok(views.html.createUsuario.render(usuarioForm));

    }
    public Result saveUsuario(){
	Form<Usuario> usuarioForm = formFactory.form(Usuario.class).bindFromRequest();
	Usuario usuario = usuarioForm.get();
	usuario.nome = usuarioForm.field("nome").valueOr("");
	usuario.senha = usuarioForm.field("senha").valueOr("");
	usuario.save();
	session("usuario",usuario.id.toString());
	return redirect(routes.HomeController.index());
    }


    public Result login(){
	Form<Usuario> login = formFactory.form(Usuario.class).bindFromRequest();
        String nome = login.field("nome").valueOr("");
  	Usuario user = Usuario.findByName(nome);
	if(user == null)
		return ok("usuario nao encontrado");
	if(!user.senha.equals(login.field("senha").valueOr("")))
		return ok("senha incorreta");
	session().clear();
	session("usuario",user.id.toString());
        return  ok(views.html.listarCds.render(user.Cd.econtra.all()));
    }



    public Result createCd(){
	Form cdForm = formFactory.form(Cd.class);
	return ok(views.html.createCd.render(cdForm));

    }
    public Result saveCd(){
	Form<Cd> cdForm = formFactory.form(Cd.class).bindFromRequest();
	Cd cd = cdForm.get();
	cd.save();
        Usuario u = Usuario.findById(session().get("usuario"));
	u.cds.add(cd);
	u.save();
	return redirect(routes.HomeController.index());
    }

}
