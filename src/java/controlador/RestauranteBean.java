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
import modelo.entidades.Restaurante;
import modelo.sesion.bean.RestauranteFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name="restauranteBean")
@ViewScoped
public class RestauranteBean implements Serializable{
    @Inject
private RestauranteFacade restauranteFacade;

private List<Restaurante> restauranteList;
private Restaurante restauranteSelecionado;

    public RestauranteBean() {
    }

@PostConstruct
    public void init(){
        buscarTodos();
        restauranteSelecionado = new Restaurante();
    }
    
    public String GenerarID(){
        int num=restauranteFacade.findAll().size()+1;
        if(num<10){
        return "r00"+num;
        }else if(num<100){
            return "r0"+num;
        }else{
            return "r"+num;
        }
    }
    
    public String insertarRestaurante(){
        restauranteSelecionado.setCodrestaurante(GenerarID());
        restauranteFacade.create(restauranteSelecionado);
        return "TbRestaurante.xhtml?faces-redirect=true";
    }
    
     public String eliminarRestaurante(){
        restauranteFacade.remove(restauranteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
        return "TbRestaurante.xhtml?faces-redirect=true";
    }
     
     public String actualizarRestaurante(){
        restauranteFacade.edit(restauranteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos almacenados exitosamente"));
        return "TbRestaurante.xhtml?faces-redirect=true";
    }
    
    public List<Restaurante> buscarTodos(){
        restauranteList = restauranteFacade.findAll();
        return restauranteList;
    }

    public RestauranteFacade getRestauranteFacade() {
        return restauranteFacade;
    }

    public void setRestauranteFacade(RestauranteFacade restauranteFacade) {
        this.restauranteFacade = restauranteFacade;
    }

    public List<Restaurante> getRestauranteList() {
        return restauranteList;
    }

    public void setRestauranteList(List<Restaurante> restauranteList) {
        this.restauranteList = restauranteList;
    }

    public Restaurante getRestauranteSelecionado() {
        return restauranteSelecionado;
    }

    public void setRestauranteSelecionado(Restaurante restauranteSelecionado) {
        this.restauranteSelecionado = restauranteSelecionado;
    }

   
}
