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
import modelo.entidades.Clientes;
import modelo.entidades.Pedidos;
import modelo.sesion.bean.ClientesFacade;
import modelo.sesion.bean.PedidosFacade;

/**
 *
 * @author Edwin
 */
@ManagedBean(name = "pedidosvBean")
@ViewScoped
public class PedidosBeanV implements Serializable {

    @Inject
    private PedidosFacade peidosvFacade;
    private List<Pedidos> peidosvList;
    private Pedidos peidosvSelecionado;

    @Inject
    private ClientesFacade clientesFacade;
    private List<Clientes> clientesList;
    private Clientes clientesSelecionado;

    public PedidosBeanV() {
    }

    @PostConstruct
    public void init() {
        buscarTodos();
        clientes();
        peidosvSelecionado = new Pedidos();
        clientesSelecionado = new Clientes();
        peidosvSelecionado.setCodpedidos(GenerarID());
    }

    public String GenerarID() {
        int num = peidosvFacade.findAll().size() + 1;
        if (num < 10) {
            return "U00" + num;
        } else if (num < 100) {
            return "U0" + num;
        } else {
            return "U" + num;
        }
    }

    public String insertarPedidos() {
        peidosvSelecionado.setCodcliente(clientesSelecionado);
        peidosvFacade.create(peidosvSelecionado);
        return "TbPedidos.xhtml?faces-redirect=true";
    }

    public String eliminarPedidos() {
        peidosvFacade.remove(peidosvSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TbPedidos.xhtml?faces-redirect=true";
    }

    public String actualizarPedidos() {
        peidosvSelecionado.setCodcliente(clientesSelecionado);
        peidosvFacade.edit(peidosvSelecionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos almacenados exitosamente"));
        return "TbPedidos.xhtml?faces-redirect=true";
    }

    public List<Pedidos> buscarTodos() {
        peidosvList = peidosvFacade.findAll();
        return peidosvList;
    }

    public List<Clientes> clientes() {
        clientesList = clientesFacade.findAll();
        return clientesList;
    }

    public PedidosFacade getPedidosFacade() {
        return peidosvFacade;
    }

    public void setPedidosFacade(PedidosFacade peidosvFacade) {
        this.peidosvFacade = peidosvFacade;
    }

    public List<Pedidos> getPedidosList() {
        return peidosvList;
    }

    public void setPedidosList(List<Pedidos> peidosvList) {
        this.peidosvList = peidosvList;
    }

    public Pedidos getPedidosSelecionado() {
        return peidosvSelecionado;
    }

    public void setPedidosSelecionado(Pedidos peidosvSelecionado) {
        this.peidosvSelecionado = peidosvSelecionado;
    }

    public ClientesFacade getClientesFacade() {
        return clientesFacade;
    }

    public void setClientesFacade(ClientesFacade clientesFacade) {
        this.clientesFacade = clientesFacade;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public Clientes getClientesSelecionado() {
        return clientesSelecionado;
    }

    public void setClientesSelecionado(Clientes clientesSelecionado) {
        this.clientesSelecionado = clientesSelecionado;
    }

}
