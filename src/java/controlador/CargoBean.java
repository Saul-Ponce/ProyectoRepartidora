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
import modelo.sesion.bean.CargoFacade;

/**
 *
 * @author Edwin
 */
@ManagedBean(name = "cargoBean")
@ViewScoped
public class CargoBean implements Serializable {

    @Inject
    private CargoFacade cargoFacade;
    private List<Cargo> cargoList;
    private Cargo cargoSelecionada;

    public CargoBean() {
    }

    @PostConstruct
    public void init() {
        buscarTodas();
        cargoSelecionada = new Cargo();
    }

    public String GenerarID(String nombre) {
        String ini = nombre.charAt(0) + "";
        int num = cargoFacade.findAll().size() + 1;
        if (num < 10) {
            return ini + "00" + num;
        } else if (num < 100) {
            return ini + "0" + num;
        } else {
            return ini + num;
        }
    }

    public String insertarCargo() {
        cargoSelecionada.setCodcargo(GenerarID(cargoSelecionada.getNombrecargo()));
        cargoFacade.create(cargoSelecionada);
        return "TbCargo.xhtml?faces-redirect=true";
    }

    public String eliminarCargo() {
        cargoFacade.remove(cargoSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbCargo.xhtml?faces-redirect=true";
    }

    public String actualizarCargo() {
        cargoFacade.edit(cargoSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbCargo.xhtml?faces-redirect=true";
    }

    public List<Cargo> buscarTodas() {
        cargoList = cargoFacade.findAll();
        return cargoList;
    }

    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }

    public void setCargoFacade(CargoFacade cargoFacade) {
        this.cargoFacade = cargoFacade;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public Cargo getCargoSelecionada() {
        return cargoSelecionada;
    }

    public void setCargoSelecionada(Cargo cargoSelecionada) {
        this.cargoSelecionada = cargoSelecionada;
    }

}
