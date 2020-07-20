/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Empleado;
import modelo.entidades.Usuario;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.UsuarioFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean implements Serializable{
public static List<Usuario> usuarioList;
public Usuario usuarioSelecionado;
public static Empleado empleadoSelecionado;


@PostConstruct
    public void init(){
        usuarioSelecionado = new Usuario();
        empleadoSelecionado = new Empleado();
    }
    
    
    public String validarUsuario(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU"); 
        EntityManager em = emf.createEntityManager();
        Query query =em.createQuery("FROM Usuario u WHERE u.idusuario=:User and u.contrasenia=:Contra ");
        query.setParameter("User", usuarioSelecionado.getIdusuario());
        query.setParameter("Contra", usuarioSelecionado.getContrasenia());
        usuarioList = query.getResultList();
        if(!usuarioList.isEmpty()){
        return "Catalogo/comida/TbComida.xhtml?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o contraseña incorrecto",""));
            return "";
        }
    }
    
    public static Empleado empleado(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU"); 
        EntityManager em = emf.createEntityManager();
        Query query =em.createQuery("from Empleado e WHERE e.codempleado =:Codigo");
        query.setParameter("Codigo", usuarioList.get(0).getCodempleado().getCodempleado());
        empleadoSelecionado = (Empleado) query.getSingleResult();
        
        return empleadoSelecionado;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public Empleado getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(Empleado empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
    }
    
    
}
