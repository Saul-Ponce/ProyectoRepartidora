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
import modelo.entidades.Pago;
import modelo.entidades.Viaje;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.PagoFacade;
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
    
     @Inject
    private PagoFacade pagoFacade;
    private List<Pago>pagoList;
    private Pago pagoSelect;
    @PostConstruct
    public void init(){
    buscarTodosViaje();
    viajeSelect=new Viaje();
    buscarEmpleado();
    empleadoSelect=new Empleado();
    buscarPago();
    pagoSelect=new Pago();
    }

    public ViajesBeans() {
    }
    
    public String GenerarID(){
        int num=viajeFacade.findAll().size()+1;
        if(num<10){
        return "v00"+num;
        }else if(num<100){
            return "v0"+num;
        }else{
            return "v"+num;
        }
    }
    
    public String insertarViaje(){
    viajeSelect.setCodviaje(GenerarID());
    viajeSelect.setCodempleado(empleadoSelect);
    viajeSelect.setCodpago(pagoSelect);
    viajeFacade.create(viajeSelect);
    return "TblViajes.xhtml?faces-redirect=true";
    }
    public String modificarViaje(){
    viajeSelect.setCodempleado(empleadoSelect);
    viajeSelect.setCodpago(pagoSelect);
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
   public List<Pago>buscarPago(){
   pagoList=pagoFacade.findAll();
   return pagoList;
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

    public PagoFacade getPagoFacade() {
        return pagoFacade;
    }

    public void setPagoFacade(PagoFacade pagoFacade) {
        this.pagoFacade = pagoFacade;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public Pago getPagoSelect() {
        return pagoSelect;
    }

    public void setPagoSelect(Pago pagoSelect) {
        this.pagoSelect = pagoSelect;
    }
}
