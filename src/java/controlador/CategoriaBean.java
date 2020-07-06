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
import modelo.sesion.bean.CategoriasFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name="categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable{
    @Inject
private CategoriasFacade categoriaFacade;

private List<Categorias> categoriaList;
private Categorias categoriaSelecionada;

    public CategoriaBean() {
    }

@PostConstruct
    public void init(){
        buscarTodas();
        categoriaSelecionada = new Categorias();
    }
    
    public String GenerarID(String nombre){
        String ini=nombre.charAt(0)+"";
        int num=categoriaFacade.findAll().size()+1;
        if(num<10){
        return ini+"00"+num;
        }else if(num<100){
            return ini+"0"+num;
        }else{
            return ini+num;
        }
    }
    
    public String insertarCategorias(){
        categoriaSelecionada.setCodcategoria(GenerarID(categoriaSelecionada.getNombreCategoria()));
        categoriaFacade.create(categoriaSelecionada);
        return "TbCategoria.xhtml?faces-redirect=true";
    }
    
     public String eliminarCategorias(){
        categoriaFacade.remove(categoriaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
        return "TbCategoria.xhtml?faces-redirect=true";
    }
     
     public String actualizarCategorias(){
        categoriaFacade.edit(categoriaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos almacenados exitosamente"));
        return "TbCategoria.xhtml?faces-redirect=true";
    }
    
    public List<Categorias> buscarTodas(){
        categoriaList = categoriaFacade.findAll();
        return categoriaList;
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

    public Categorias getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categorias categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }
    
    
}
