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
import javax.persistence.Query;
import modelo.entidades.Empleado;
import modelo.entidades.Pedidos;
import modelo.entidades.Viaje;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.PedidosFacade;
import modelo.sesion.bean.ViajeFacade;


/**
 *
 * @author PC
 */
@ManagedBean(name = "vBean")
@ViewScoped
public class ViajesBean implements Serializable {

    @Inject
    private ViajeFacade viajefacade;
    private Viaje viajeSelected;

    @Inject
    private EmpleadoFacade empleadofacade;
    private Empleado empleadoSelected;

    @Inject
    private PedidosFacade pedidofacade;
    private Pedidos pedidoSelected;

    private List<Viaje> listViaje = null;

    private List<Empleado> listEmpleado = null;
    private List<Pedidos> listPedidos = null;
    private Query query;
    private int mes;

    @PostConstruct
    public void Init() {
        viajeSelected = new Viaje();
        empleadoSelected = new Empleado();
        pedidoSelected = new Pedidos();

        listaEmpleados();
        listaPedidos();
        listaViajes();
        listaViajesFiltradas();

    }

    public ViajesBean() {
    }

    public List<Viaje> listaViajes() {
        listViaje = viajefacade.findAll();
        return listViaje;
    }

    public List<Empleado> listaEmpleados() {
        listEmpleado = empleadofacade.findAll();
        return listEmpleado;
    }

    public List<Pedidos> listaPedidos() {
        listPedidos = pedidofacade.findAll();
        return listPedidos;
    }

    public List<Viaje> listaViajesFiltradas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT v.codviaje,pd.codpedidos, e.nombre,e.apellido,pd.fechapedido\n"
                + "from pedidos pd \n"
                + "inner join pago p on p.codpedido=pd.codpedidos\n"
                + "inner join viaje v on v.codpago=p.codpago\n"
                + "inner join empleado e on e.codempleado=v.codempleado   where Extract(month from pd.fechapedido)=" + mes + " \n"
                + " group by v.codviaje,e.nombre,e.apellido,pd.fechapedido,pd.codpedidos";
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

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

}
