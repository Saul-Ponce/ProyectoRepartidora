/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
import modelo.entidades.Empleado;
import modelo.entidades.Pago;
import modelo.entidades.Pedidos;
import modelo.entidades.Viaje;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.PedidosFacade;
import modelo.sesion.bean.ViajeFacade;

/**
 *
 * @author PC
 */
@ManagedBean(name = "montoBean")
@ViewScoped
public class MontoViajesBean implements Serializable {

    @Inject
    private ViajeFacade viajefacade;
    private Viaje viajeSelected;

    @Inject
    private EmpleadoFacade empleadofacade;
    private Empleado empleadoSelected;

    @Inject
    private PagoFacade pagofacade;
    private Pago pagoSelected;

    @Inject
    private PedidosFacade pedidofacade;
    private Pedidos pedidoSelected;

    private List<Viaje> listViaje = null;
    private List<Pago> listPago = null;
    private List<Empleado> listEmpleado = null;
    private List<Pedidos> listPedidos;
    private Query query;
    private int dia;

    @PostConstruct
    public void Init() {
//        listaPedidos();
        viajeSelected = new Viaje();
        empleadoSelected = new Empleado();
        pedidoSelected = new Pedidos();
        pagoSelected = new Pago();
        listaEmpleados();

        listaViajes();
        listaPagos();
        listaMontoFiltradas();
//        listaFechasFiltradas();

    }

    public MontoViajesBean() {
    }

    public List<Viaje> listaViajes() {
        listViaje = viajefacade.findAll();
        return listViaje;
    }

    public List<Empleado> listaEmpleados() {
        listEmpleado = empleadofacade.findAll();
        return listEmpleado;
    }

    public List<Pago> listaPagos() {
        listPago = pagofacade.findAll();
        return listPago;
    }

    public List<Pedidos> listaPedidos() {
        listPedidos = pedidofacade.findAll();
        return listPedidos;
    }

//    public List<Pedidos> listaFechasFiltradas() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
//        EntityManager em = emf.createEntityManager();
//        String jpql = "select p.codpedidos,p.fechapedido from pedidos p\n"
//                + "inner join pago pg on pg.codpedido=p.codpedidos\n"
//                + "inner join viaje v on v.codpago=pg.codpago\n"
//                + "group by p.codpedidos,p.fechapedido";
//        query = em.createNativeQuery(jpql);
//        listViaje = query.getResultList();
//        em.close();
//        emf.close();
//        return listPedidos;
//    } 

    public List<Viaje> listaMontoFiltradas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT v.codviaje,pd.codpedidos,sum(p.monto+p.montoviaje) as total, p.tipo_pago,pd.fechapedido,e.nombre,e.apellido\n"
                + "                from  viaje v \n"
                + "                inner join empleado e on e.codempleado=v.codempleado \n"
                + "                inner join pago p on p.codpago=v.codpago\n"
                + "                inner join pedidos pd on pd.codpedidos=p.codpedido  "
                + "group by v.codviaje,e.nombre,e.apellido,pd.fechapedido,pd.codpedidos,p.tipo_pago";
        query = em.createNativeQuery(jpql);
        listViaje = query.getResultList();
        em.close();
        emf.close();
        return listViaje;
    }

    public ViajeFacade getViajefacade() {
        return viajefacade;
    }

    public void setViajefacade(ViajeFacade viajefacade) {
        this.viajefacade = viajefacade;
    }

    public Viaje getViajeSelected() {
        return viajeSelected;
    }

    public void setViajeSelected(Viaje viajeSelected) {
        this.viajeSelected = viajeSelected;
    }

    public EmpleadoFacade getEmpleadofacade() {
        return empleadofacade;
    }

    public void setEmpleadofacade(EmpleadoFacade empleadofacade) {
        this.empleadofacade = empleadofacade;
    }

    public Empleado getEmpleadoSelected() {
        return empleadoSelected;
    }

    public void setEmpleadoSelected(Empleado empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
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

    public List<Viaje> getListViaje() {
        return listViaje;
    }

    public void setListViaje(List<Viaje> listViaje) {
        this.listViaje = listViaje;
    }

    public List<Empleado> getListEmpleado() {
        return listEmpleado;
    }

    public void setListEmpleado(List<Empleado> listEmpleado) {
        this.listEmpleado = listEmpleado;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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
