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
import modelo.entidades.Cargo;
import modelo.entidades.Empleado;
import modelo.sesion.bean.CargoFacade;
import modelo.sesion.bean.EmpleadoFacade;

/**
 *
 * @author Edwin
 */
@ManagedBean(name = "empleadoBean")
@ViewScoped
public class EmpleadoBean implements Serializable {

    @Inject
    private EmpleadoFacade empleadoFacade;
    private List<Empleado> empleadoList;
    private Empleado empleadoSeleccionado;

    @Inject
    private CargoFacade cargoFacade;
    private List<Cargo> cargoList;
    private Cargo cargoSelecionado;

    public EmpleadoBean() {
    }

    @PostConstruct
    public void init() {
        buscarTodos();
        cargo();
        empleadoSeleccionado = new Empleado();
        cargoSelecionado = new Cargo();
        empleadoSeleccionado.setCodempleado(GenerarID());
    }

    public String GenerarID() {
        int num = empleadoFacade.findAll().size() + 1;
        if (num < 10) {
            return "E00" + num;
        } else if (num < 100) {
            return "E0" + num;
        } else {
            return "E" + num;
        }
    }

    public String insertarEmpleado() {
        empleadoSeleccionado.setCodcargo(cargoSelecionado);
        empleadoFacade.create(empleadoSeleccionado);
        return "TbEmpleado.xhtml?faces-redirect=true";
    }

    public String eliminarEmpleado() {
        empleadoFacade.remove(empleadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbEmpleado.xhtml?faces-redirect=true";
    }

    public String actualizarEmpleado() {
        empleadoSeleccionado.setCodcargo(cargoSelecionado);
        empleadoFacade.edit(empleadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbEmpleado.xhtml?faces-redirect=true";
    }

    public List<Empleado> buscarTodos() {
        empleadoList = empleadoFacade.findAll();
        return empleadoList;
    }

    public List<Cargo> cargo() {
        cargoList = cargoFacade.findAll();
        return cargoList;
    }

    public EmpleadoFacade getEmpleadoFacade() {
        return empleadoFacade;
    }

    public void setEmpleadoFacade(EmpleadoFacade empleadoSeleccionadoFacade) {
        this.empleadoFacade = empleadoSeleccionadoFacade;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoSeleccionadoList) {
        this.empleadoList = empleadoSeleccionadoList;
    }

    public Empleado getEmpleadoSelecionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSelecionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> CargoList) {
        this.cargoList = CargoList;
    }

    public Cargo getCargoSelecionado() {
        return cargoSelecionado;
    }

    public void setCargoSelecionado(Cargo CargoSelecionado) {
        this.cargoSelecionado = CargoSelecionado;
    }

    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }

    public void setCargoFacade(CargoFacade cargoFacade) {
        this.cargoFacade = cargoFacade;
    }

}
