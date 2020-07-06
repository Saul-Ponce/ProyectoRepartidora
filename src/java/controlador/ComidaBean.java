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
import modelo.entidades.Categorias;
import modelo.entidades.Comidas;
import modelo.entidades.Restaurante;
import modelo.sesion.bean.CategoriasFacade;
import modelo.sesion.bean.ComidasFacade;
import modelo.sesion.bean.RestauranteFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name = "comidasBean")
@ViewScoped
public class ComidaBean implements Serializable {

    @Inject
    private ComidasFacade comidaFacade;
    private List<Comidas> comidasList;
    private Comidas comidaSelecionada;
    @Inject
    private CategoriasFacade categoriaFacade;
    private List<Categorias> categoriaList;
    private Categorias categoriaSeleccionada;
    @Inject
    private RestauranteFacade restauranteFacade;
    private List<Restaurante> restauranteList;
    private Restaurante restauranteSeleccionado;

    public ComidaBean() {
    }

    @PostConstruct
    public void init() {
        buscarTodas();
        categorias();
        restaurantes();
        comidaSelecionada = new Comidas();
        categoriaSeleccionada = new Categorias();
        restauranteSeleccionado = new Restaurante();
    }

    public String GenerarID() {
        int num = comidaFacade.findAll().size() + 1;
        if (num < 10) {
            return "000" + num;
        } else if (num < 100) {
            return "00" + num;
        }else if (num < 1000) {
            return "0" + num;
        } else {
            return ""+num;
        }
    }

    public String insertarComidas() {
        comidaSelecionada.setCodcomida(GenerarID());
        comidaSelecionada.setCodcategoria(categoriaSeleccionada);
        comidaSelecionada.setCodrestaurante(restauranteSeleccionado);
        comidaFacade.create(comidaSelecionada);
        return "TbComida.xhtml?faces-redirect=true";
    }

    public String eliminarComidas() {
        comidaFacade.remove(comidaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbComida.xhtml?faces-redirect=true";
    }

    public String actualizarComidas() {
        comidaSelecionada.setCodcategoria(categoriaSeleccionada);
        comidaSelecionada.setCodrestaurante(restauranteSeleccionado);
        comidaFacade.edit(comidaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbComida.xhtml?faces-redirect=true";
    }

    public List<Comidas> buscarTodas() {
        comidasList = comidaFacade.findAll();
        return comidasList;
    }

    public List<Categorias> categorias() {
        categoriaList = categoriaFacade.findAll();
        return categoriaList;
    }

    public List<Restaurante> restaurantes() {
        restauranteList = restauranteFacade.findAll();
        return restauranteList;
    }

    public ComidasFacade getComidaFacade() {
        return comidaFacade;
    }

    public void setComidaFacade(ComidasFacade comidaFacade) {
        this.comidaFacade = comidaFacade;
    }

    public List<Comidas> getComidasList() {
        return comidasList;
    }

    public void setComidasList(List<Comidas> comidasList) {
        this.comidasList = comidasList;
    }

    public Comidas getComidaSelecionada() {
        return comidaSelecionada;
    }

    public void setComidaSelecionada(Comidas comidaSelecionada) {
        this.comidaSelecionada = comidaSelecionada;
    }

    public CategoriasFacade getCategoriaFacade() {
        return categoriaFacade;
    }

    public void setCategoriaFacade(CategoriasFacade categoriaFacade) {
        this.categoriaFacade = categoriaFacade;
    }

    public List<Categorias> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categorias> categoriaList) {
        this.categoriaList = categoriaList;
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

    public Categorias getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Categorias categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public Restaurante getRestauranteSeleccionado() {
        return restauranteSeleccionado;
    }

    public void setRestauranteSeleccionado(Restaurante restauranteSeleccionado) {
        this.restauranteSeleccionado = restauranteSeleccionado;
    }

}
