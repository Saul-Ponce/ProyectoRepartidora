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
@ManagedBean(name = "facturaBean")
@ViewScoped
public class facturaBean implements Serializable {

    @Inject
    private PagoFacade pagofacade;
    private Pago pagoSelected;

    @Inject
    private PedidosFacade pedidofacade;
    private Pedidos pedidoSelected;
    private List<Pago> listPago = null;
    private List<Pedidos> listPedidos = null;
    private Query query;

    @PostConstruct
    public void Init() {

        pedidoSelected = new Pedidos();
        pagoSelected = new Pago();
//        listaPedidos();
//        listafactura();
        listafactura();

    }

    public facturaBean() {
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
    public List<Pago> listafactura() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c.nombre,c.descripcion,c.precio,dp.cantidad,sum(c.precio*dp.cantidad),p.montoviaje,sum(p.monto+p.montoviaje) as totalPagar\n"
                + "                from pago p \n"
                + "                inner join pedidos pd on pd.codpedidos=p.codpedido\n"
                + "                inner join clientes cc on cc.codclientes=pd.codcliente\n"
                + "                inner join detallepedido dp on dp.codpedido=pd.codpedidos\n"
                + "                inner join comidas c on c.codcomida=dp.codcomida\n"
                + "                inner join restaurante r on r.codrestaurante=c.codrestaurante\n"
                + "                where  pd.codpedidos='" + this.pedidoSelected.getCodpedidos() + "'\n"
                + "                group by c.nombre,c.descripcion,c.precio,cc.nombre,cc.apellido,dp.cantidad,p.montoviaje";
        query = em.createNativeQuery(jpql);
        listPago = query.getResultList();
        em.close();
        emf.close();
        return listPago;

    }

    public PedidosFacade getPedidofacade() {
        return pedidofacade;
    }

    public void setPedidofacade(PedidosFacade pedidofacade) {
        this.pedidofacade = pedidofacade;
    }

    public Pedidos getPedidoSelected() {
        return pedidoSelected;
    }

    public void setPedidoSelected(Pedidos pedidoSelected) {
        this.pedidoSelected = pedidoSelected;
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
        return pagoSelected;
    }

    public void setPagoSelected(Pago pagoSelected) {
        this.pagoSelected = pagoSelected;
    }

    public List<Pago> getListPago() {
        return listPago;
    }

    public void setListPago(List<Pago> listPago) {
        this.listPago = listPago;
    }

}
