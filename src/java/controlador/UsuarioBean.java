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
import modelo.entidades.Empleado;
import modelo.entidades.Usuario;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.UsuarioFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{
    @Inject
private UsuarioFacade usuarioFacade;
private List<Usuario> usuarioList;
private Usuario usuarioSelecionado;

@Inject
private EmpleadoFacade empleadoFacade;
private List<Empleado> empleadoList;
private Empleado empleadoSelecionado;

    public UsuarioBean() {
    }

@PostConstruct
    public void init(){
        buscarTodos();
        empleado();
        usuarioSelecionado = new Usuario();
        empleadoSelecionado = new Empleado();
        usuarioSelecionado.setIdusuario(GenerarID());
    }
    
    public String GenerarID(){
        int num=usuarioFacade.findAll().size()+1;
        if(num<10){
        return "U00"+num;
        }else if(num<100){
            return "U0"+num;
        }else{
            return "U"+num;
        }
    }
    
    public String insertarUsuario(){
        //usuarioSelecionado.setIdusuario(GenerarID());
        usuarioSelecionado.setCodempleado(empleadoSelecionado);
        usuarioFacade.create(usuarioSelecionado);
        return "TbUsuario.xhtml?faces-redirect=true";
    }
    
     public String eliminarUsuario(){
        usuarioFacade.remove(usuarioSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
        return "TbUsuario.xhtml?faces-redirect=true";
    }
     
     public String actualizarUsuario(){
        usuarioFacade.edit(usuarioSelecionado);
        usuarioSelecionado.setCodempleado(empleadoSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos almacenados exitosamente"));
        return "TbUsuario.xhtml?faces-redirect=true";
    }
    
    public List<Usuario> buscarTodos(){
        usuarioList = usuarioFacade.findAll();
        return usuarioList;
    }

    public List<Empleado> empleado(){
        empleadoList = empleadoFacade.findAll();
        return empleadoList;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
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

    public EmpleadoFacade getEmpleadoFacade() {
        return empleadoFacade;
    }

    public void setEmpleadoFacade(EmpleadoFacade empleadoFacade) {
        this.empleadoFacade = empleadoFacade;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Empleado getEmpleadoSelecionado() {
        return empleadoSelecionado;
    }

    public void setEmpleadoSelecionado(Empleado empleadoSelecionado) {
        this.empleadoSelecionado = empleadoSelecionado;
    }
    
}
