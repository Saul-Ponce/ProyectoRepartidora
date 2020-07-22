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
import modelo.entidades.Detallepedido;
import modelo.sesion.bean.DetallepedidoFacade;

/**
 *
 * @author Edwin
 */
@ManagedBean(name = "detallepBean")
@ViewScoped
public class DePedidosBeanV implements Serializable {

    @Inject
    private DetallepedidoFacade detalleFacade;
    private List<Detallepedido> detalleList;
    private Detallepedido detalleSelecionado;

    public DePedidosBeanV() {
    }

    @PostConstruct
    public void init() {
        buscarTodos();
        detalleSelecionado = new Detallepedido();
        detalleSelecionado.setCoddetalle(GenerarID());
    }

    public String GenerarID() {
        int num = detalleFacade.findAll().size() + 1;
        if (num < 10) {
            return "U00" + num;
        } else if (num < 100) {
            return "U0" + num;
        } else {
            return "U" + num;
        }
    }

    public String insertarDetellepedido() {
        detalleFacade.create(detalleSelecionado);
        return "TbDetellepedido.xhtml?faces-redirect=true";
    }

    public String eliminarDetellepedido() {
        detalleFacade.remove(detalleSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbDetellepedido.xhtml?faces-redirect=true";
    }

    public String actualizarDetellepedido() {
        detalleFacade.edit(detalleSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbDetellepedido.xhtml?faces-redirect=true";
    }

    public List<Detallepedido> buscarTodos() {
        detalleList = detalleFacade.findAll();
        return detalleList;
    }

    public DetallepedidoFacade getDetellepedidoFacade() {
        return detalleFacade;
    }

    public void setDetellepedidoFacade(DetallepedidoFacade detalleFacade) {
        this.detalleFacade = detalleFacade;
    }

    public List<Detallepedido> getDetellepedidoList() {
        return detalleList;
    }

    public void setDetellepedidoList(List<Detallepedido> detalleList) {
        this.detalleList = detalleList;
    }

    public Detallepedido getDetellepedidoSelecionado() {
        return detalleSelecionado;
    }

    public void setDetellepedidoSelecionado(Detallepedido detalleSelecionado) {
        this.detalleSelecionado = detalleSelecionado;
    }

}
