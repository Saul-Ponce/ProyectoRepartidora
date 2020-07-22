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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Pago;
import modelo.entidades.Pedidos;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.PedidosFacade;

/**
 *
 * @author PC
 */
@ManagedBean(name = "infBean")
@ViewScoped
public class InformeBean implements Serializable {

    @Inject
    private PagoFacade pagofacade;
    private Pago pagoSelectedi;

    @Inject
    private PedidosFacade pedidofacade;
    private Pedidos pedidoSelectedi;
    private List<Pago> listPagos = null;
    private List<Pedidos> listPedidos = null;
    private Query query;

    @PostConstruct
    public void Init() {

        pedidoSelectedi = new Pedidos();
        pagoSelectedi = new Pago();
//        listaPedidos();
//        listafactura();
        listaInforme();

    }

    public InformeBean() {
    }

//    public List<Pedidos> listaPedidos() {
//        listPedidos = pedidofacade.findAll();
//        return listPedidos;
//    }
//
//    public List<Pago> listaPago() {
//        listPago = pagofacade.findAll();
//        return listPago;
//    }
    public List<Pago> listaInforme() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p.codpedidos,p.fechapedido,p.horapedido,e.nombre,e.apellido,t.placa,t.tipo,t.modelo\n" +
"                from pedidos p \n" +
"                inner join pago pg on pg.codpedido=p.codpedidos\n" +
"                inner join viaje v on v.codpago=pg.codpago\n" +
"                inner join empleado e on e.codempleado=v.codempleado\n" +
"                inner join transporte t on t.codrepartidor=e.codempleado\n" +
"                where  p.codpedidos= '" + this.pedidoSelectedi.getCodpedidos() + "'\n" +
"                group by p.codpedidos,p.fechapedido,p.horapedido,e.nombre,e.apellido,t.placa,t.tipo,t.modelo";
        query = em.createNativeQuery(jpql);
        listPagos = query.getResultList();
        em.close();
        emf.close();
        return listPagos;

    }

    public PedidosFacade getPedidofacade() {
        return pedidofacade;
    }

    public void setPedidofacade(PedidosFacade pedidofacade) {
        this.pedidofacade = pedidofacade;
    }

    public Pedidos getPedidoSelectedi() {
        return pedidoSelectedi;
    }

    public void setPedidoSelectedi(Pedidos pedidoSelectedi) {
        this.pedidoSelectedi = pedidoSelectedi;
    }

    public List<Pedidos> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<Pedidos> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public PagoFacade getPagofacade() {
        return pagofacade;
    }

    public void setPagofacade(PagoFacade pagofacade) {
        this.pagofacade = pagofacade;
    }

    public Pago getPagoSelected() {
        return pagoSelectedi;
    }

    public void setPagoSelected(Pago pagoSelected) {
        this.pagoSelectedi = pagoSelected;
    }

    public List<Pago> getListPagos() {
        return listPagos;
    }

    public void setListPagos(List<Pago> listPagos) {
        this.listPagos = listPagos;
    }

}
