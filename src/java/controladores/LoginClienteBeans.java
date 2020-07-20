/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Clientes;

/**
 *
 * @author LENOVO
 */
@ManagedBean(name="logincliente")
@ViewScoped

public class LoginClienteBeans implements Serializable{
     private List<Clientes>clienteList;
   private Clientes clienteseleccionado;
   
   @PostConstruct
   public void init(){
   clienteseleccionado= new Clientes();
   }
   
   public String ValidarClientes(){
        EntityManagerFactory client = Persistence.createEntityManagerFactory("ProyectoFinalPU"); 
        EntityManager clis = client.createEntityManager();
        Query query =clis.createQuery("SELECT c.codclientes, c.contrasenia  FROM Clientes c WHERE c.codclientes=:User and c.contrasenia=:Password ");
        query.setParameter("User", clienteseleccionado.getCodclientes());
        query.setParameter("Password", clienteseleccionado.getContrasenia());
        clienteList = query.getResultList();
        if(!clienteList.isEmpty()){
        return "Principal/TemplatePrincipal.xhtml?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o contraseña incorrecto",""));
            return "";
        }
   
   }
   
   //metodos setter y getter

    public List<Clientes> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Clientes> clienteList) {
        this.clienteList = clienteList;
    }

    public Clientes getClienteseleccionado() {
        return clienteseleccionado;
    }

    public void setClienteseleccionado(Clientes clienteseleccionado) {
        this.clienteseleccionado = clienteseleccionado;
    }

}
