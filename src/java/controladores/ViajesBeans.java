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
import modelo.entidades.Empleado;
import modelo.entidades.Viaje;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.ViajeFacade;

/**
 *
 * @author LENOVO
 */
@ManagedBean(name="vb")
@ViewScoped
public class ViajesBeans implements Serializable{
    @Inject
    private ViajeFacade viajeFacade;
    private List<Viaje>viajeList;
    private Viaje viajeSelect;
    
    @Inject
    private EmpleadoFacade empleadoFacade;
    private List<Empleado>empleadoList;
    private Empleado empleadoSelect;
    
    @PostConstruct
    public void init(){
    buscarTodosViaje();
    viajeSelect=new Viaje();
    buscarEmpleado();
    empleadoSelect=new Empleado();
    }

    public ViajesBeans() {
    }
    public String insertarViaje(){
    viajeSelect.setCodempleado(empleadoSelect);
    viajeFacade.create(viajeSelect);
    return "TblViajes.xhtml?faces-redirect=true";
    }
    public String modificarViaje(){
    viajeSelect.setCodempleado(empleadoSelect);
    viajeFacade.edit(viajeSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos actualizados"));
    return "TblViajes.xhtml?faces-redirect=true";
    }
    public String eliminarViaje(){
    viajeFacade.remove(viajeSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
    return "TblViajes.xhtml?faces-redirect=true";
    }
   public List<Viaje>buscarTodosViaje(){
   viajeList=viajeFacade.findAll();
   return viajeList;
   }
   public List<Empleado>buscarEmpleado(){
   empleadoList=empleadoFacade.findAll();
   return empleadoList;
   }
//metodos setter

    public ViajeFacade getViajeFacade() {
        return viajeFacade;
    }

    public void setViajeFacade(ViajeFacade viajeFacade) {
        this.viajeFacade = viajeFacade;
    }

    public List<Viaje> getViajeList() {
        return viajeList;
    }

    public void setViajeList(List<Viaje> viajeList) {
        this.viajeList = viajeList;
    }

    public Viaje getViajeSelect() {
        return viajeSelect;
    }

    public void setViajeSelect(Viaje viajeSelect) {
        this.viajeSelect = viajeSelect;
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

    public Empleado getEmpleadoSelect() {
        return empleadoSelect;
    }

    public void setEmpleadoSelect(Empleado empleadoSelect) {
        this.empleadoSelect = empleadoSelect;
    }
 
}
