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
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Clientes;
import modelo.entidades.Detallepedido;
import modelo.entidades.Pedidos;
import modelo.sesion.bean.ClientesFacade;
import modelo.sesion.bean.DetallepedidoFacade;
import modelo.sesion.bean.PedidosFacade;



/**
 *
 * @author Dany
 */
@ManagedBean
@ViewScoped
public class PedidosPorClienteBeans implements Serializable{
     @Inject
    private ClientesFacade clienteFacade;
    private Clientes clienteselect;
    private List<Clientes>listaCliente;
    
    @Inject
    private PedidosFacade pedidoFacade;
    private Pedidos pedidoSelect;
    private List<Pedidos>listaPedidoSelect;
    
      @Inject
    private DetallepedidoFacade dtpedidoFacade;
    private Detallepedido dtpedidoSelect;
    private List<Detallepedido>listaDtPedidoSelect;

    public PedidosPorClienteBeans() {
    }
    
    @PostConstruct
    public void init(){
    clienteselect= new Clientes();
    mostrarClientes();
    }
    
    public void mostrarClientes(){
    listaCliente=clienteFacade.findAll();
    }
    
     public List<Detallepedido>mostrarPedidos(){
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em= emf.createEntityManager();
        listaDtPedidoSelect =  (List<Detallepedido>) em.createQuery("SELECT p FROM Detallepedido p WHERE p.codpedido.codcliente.codclientes=:id").setParameter("id", clienteselect.getCodclientes()).getResultList();
        em.close();
        emf.close();
    return listaDtPedidoSelect;
    }

    public ClientesFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClientesFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public Clientes getClienteselect() {
        return clienteselect;
    }

    public void setClienteselect(Clientes clienteselect) {
        this.clienteselect = clienteselect;
    }

    public List<Clientes> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Clientes> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public PedidosFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidosFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public Pedidos getPedidoSelect() {
        return pedidoSelect;
    }

    public void setPedidoSelect(Pedidos pedidoSelect) {
        this.pedidoSelect = pedidoSelect;
    }

    public List<Pedidos> getListaPedidoSelect() {
        return listaPedidoSelect;
    }

    public void setListaPedidoSelect(List<Pedidos> listaPedidoSelect) {
        this.listaPedidoSelect = listaPedidoSelect;
    }

    public DetallepedidoFacade getDtpedidoFacade() {
        return dtpedidoFacade;
    }

    public void setDtpedidoFacade(DetallepedidoFacade dtpedidoFacade) {
        this.dtpedidoFacade = dtpedidoFacade;
    }

    public Detallepedido getDtpedidoSelect() {
        return dtpedidoSelect;
    }

    public void setDtpedidoSelect(Detallepedido dtpedidoSelect) {
        this.dtpedidoSelect = dtpedidoSelect;
    }

    public List<Detallepedido> getListaDtPedidoSelect() {
        return listaDtPedidoSelect;
    }

    public void setListaDtPedidoSelect(List<Detallepedido> listaDtPedidoSelect) {
        this.listaDtPedidoSelect = listaDtPedidoSelect;
    }
    
    
    
    
    
}
