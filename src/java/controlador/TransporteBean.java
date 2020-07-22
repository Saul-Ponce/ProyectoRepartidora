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
import modelo.entidades.Transporte;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.TransporteFacade;

/**
 *
 * @author Edwin
 */
@ManagedBean(name = "transporteBean")
@ViewScoped
public class TransporteBean implements Serializable {

    @Inject
    private TransporteFacade transporteFacade;
    private List<Transporte> transporteList;
    private Transporte transporteSelecionado;

    @Inject
    private EmpleadoFacade empleadoFacade;
    private List<Empleado> empleadoList;
    private Empleado empleadoSelecionado;

    public TransporteBean() {
    }

    @PostConstruct
    public void init() {
        buscarTodos();
        empleado();
        transporteSelecionado = new Transporte();
        empleadoSelecionado = new Empleado();
        transporteSelecionado.setCodtransporte(GenerarID());
    }

    public String GenerarID() {
        int num = transporteFacade.findAll().size() + 1;
        if (num < 10) {
            return "U00" + num;
        } else if (num < 100) {
            return "U0" + num;
        } else {
            return "U" + num;
        }
    }

    public String insertarTransporte() {
        transporteSelecionado.setCodrepartidor(empleadoSelecionado);
        transporteFacade.create(transporteSelecionado);
        return "TbTransporte.xhtml?faces-redirect=true";
    }

    public String eliminarTransporte() {
        transporteFacade.remove(transporteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbTransporte.xhtml?faces-redirect=true";
    }

    public String actualizarTransporte() {
        transporteSelecionado.setCodrepartidor(empleadoSelecionado);
        transporteFacade.edit(transporteSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbTransporte.xhtml?faces-redirect=true";
    }

    public List<Transporte> buscarTodos() {
        transporteList = transporteFacade.findAll();
        return transporteList;
    }

    public List<Empleado> empleado() {
        empleadoList = empleadoFacade.findAll();
        return empleadoList;
    }

    public TransporteFacade getTransporteFacade() {
        return transporteFacade;
    }

    public void setTransporteFacade(TransporteFacade transporteFacade) {
        this.transporteFacade = transporteFacade;
    }

    public List<Transporte> getTransporteList() {
        return transporteList;
    }

    public void setTransporteList(List<Transporte> transporteList) {
        this.transporteList = transporteList;
    }

    public Transporte getTransporteSelecionado() {
        return transporteSelecionado;
    }

    public void setTransporteSelecionado(Transporte transporteSelecionado) {
        this.transporteSelecionado = transporteSelecionado;
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
