/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.entidades.Empleado;

/**
 *
 * @author Arevalo
 */
@ManagedBean(name ="rowGroupViewEmpleado")
@ViewScoped
public class RowGroupViewEmpleado implements Serializable{
    
    private List<Empleado> empleado;
    
    
    //agrega para la consulta FilterDatosPozos
    private List<Empleado> empleadofiltrada;
    
    //agregado para la consulta DataTableSearchPozos
    private Empleado empleadoSeleccionado;
    
    @ManagedProperty("#{empleadoBean}")
    private EmpleadoBean nomina;
    
    @PostConstruct
    public void init(){
        //agregado para la consulta DataTableSearchPozos
        empleadoSeleccionado = new Empleado();
        empleado = nomina.getEmpleadoList();
    }

    public List<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleadofiltrada() {
        return empleadofiltrada;
    }

    public void setEmpleadofiltrada(List<Empleado> empleadofiltrada) {
        this.empleadofiltrada = empleadofiltrada;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public EmpleadoBean getNomina() {
        return nomina;
    }

    public void setNomina(EmpleadoBean nomina) {
        this.nomina = nomina;
    }

   
    
}
