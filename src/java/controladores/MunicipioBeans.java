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
import javax.inject.Inject;
import modelo.entidades.Municipio;
import modelo.sesion.bean.MunicipioFacade;

/**
 *
 * @author LENOVO
 */
@ManagedBean(name="ct")
@ViewScoped
public class MunicipioBeans implements Serializable{
   @Inject
    MunicipioFacade municipioFacade;
    List<Municipio>muniList;
    Municipio municipioSelect;
    @PostConstruct
    public void init(){
    buscarTodosMunicipios();
    municipioSelect=new Municipio();
    }

    public MunicipioBeans() {
    }
    
    public String insertarMunicipio(){
    municipioFacade.create(municipioSelect);
    return "TblMunicipio.xhtml?faces-redirect=true";
    }
    public String modificarMunicipio(){
    municipioFacade.edit(municipioSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos actualizados"));
    return "TblMunicipio.xhtml?faces-redirect=true";
    }
    
     public String eliminarMunicipio(){
    municipioFacade.remove(municipioSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
    return "TblMunicipio.xhtml?faces-redirect=true";
    }
   public List<Municipio>buscarTodosMunicipios(){
   muniList=municipioFacade.findAll();
   return muniList;
   }
//metodos setter

    public MunicipioFacade getMunicipioFacade() {
        return municipioFacade;
    }

    public void setMunicipioFacade(MunicipioFacade municipioFacade) {
        this.municipioFacade = municipioFacade;
    }

    public List<Municipio> getMuniList() {
        return muniList;
    }

    public void setMuniList(List<Municipio> muniList) {
        this.muniList = muniList;
    }

    public Municipio getMunicipioSelect() {
        return municipioSelect;
    }

    public void setMunicipioSelect(Municipio municipioSelect) {
        this.municipioSelect = municipioSelect;
    }
  
}
