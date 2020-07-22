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
import modelo.entidades.Comidas;
import modelo.entidades.Detallepedido;
import modelo.entidades.Pago;
import modelo.entidades.Pedidos;
import modelo.sesion.bean.ComidasFacade;
import modelo.sesion.bean.DetallepedidoFacade;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.PedidosFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name= "m")
@ViewScoped
public class MenuVendidoBeans implements Serializable{
    @Inject
    private ComidasFacade comidafacade;
    private Comidas comidaSelect;

    @Inject
    private DetallepedidoFacade dpfacade;
    private Detallepedido dpSelect;

    @Inject
    private PagoFacade pagofacade;
    private Pago pagoSelect;

    @Inject
    private PedidosFacade pedidfacade;
    private Pedidos pedidSelect;

    private List<Comidas> listcomida = null;
    private List<Pago> listpagos = null;
    private List<Detallepedido> listdp = null;
    private List<Pedidos> listPedid = null;
    private Query query;
    
      @PostConstruct
    public void Init() {
       comidaSelect = new Comidas();
        dpSelect = new Detallepedido();
        pedidSelect = new Pedidos();
        pagoSelect = new Pago();
        listacomida();
        listaPedido();
        listaDetalleP();
        ListaPagos();
        listaMenuVendidoFiltradas();
    }

    public MenuVendidoBeans() {
    }
    
    public List<Comidas> listacomida() {
        listcomida = comidafacade.findAll();
        return listcomida;
    }

    public List<Detallepedido> listaDetalleP() {
        listdp = dpfacade.findAll();
        return listdp;
    }

    public List<Pago> ListaPagos() {
        listpagos = pagofacade.findAll();
        return listpagos;
    }

    public List<Pedidos> listaPedido() {
        listPedid = pedidfacade.findAll();
        return listPedid;
    }
    
    public List<Detallepedido> listaMenuVendidoFiltradas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "select c.codcomida, c.nombre, sum(dp.cantidad) from Comidas c \n" +
"inner join  Detallepedido dp on dp.codcomida=c.codcomida\n" +
"inner join Pedidos p on p.codpedidos=dp.codpedido\n" +
"inner join Pago pg on pg.codpedido=p.codpedidos\n" +
"group by c.codcomida, c.nombre\n" +
"order by sum(dp.cantidad)";
        query = em.createNativeQuery(jpql);
        listdp = query.getResultList();
        em.close();
        emf.close();
        return listdp;
    }

    public ComidasFacade getComidafacade() {
        return comidafacade;
    }

    public void setComidafacade(ComidasFacade comidafacade) {
        this.comidafacade = comidafacade;
    }

    public Comidas getComidaSelect() {
        return comidaSelect;
    }

    public void setComidaSelect(Comidas comidaSelect) {
        this.comidaSelect = comidaSelect;
    }

    public DetallepedidoFacade getDpfacade() {
        return dpfacade;
    }

    public void setDpfacade(DetallepedidoFacade dpfacade) {
        this.dpfacade = dpfacade;
    }

    public Detallepedido getDpSelect() {
        return dpSelect;
    }

    public void setDpSelect(Detallepedido dpSelect) {
        this.dpSelect = dpSelect;
    }

    public PagoFacade getPagofacade() {
        return pagofacade;
    }

    public void setPagofacade(PagoFacade pagofacade) {
        this.pagofacade = pagofacade;
    }

    public Pago getPagoSelect() {
        return pagoSelect;
    }

    public void setPagoSelect(Pago pagoSelect) {
        this.pagoSelect = pagoSelect;
    }

    public PedidosFacade getPedidfacade() {
        return pedidfacade;
    }

    public void setPedidfacade(PedidosFacade pedidfacade) {
        this.pedidfacade = pedidfacade;
    }

    public Pedidos getPedidSelect() {
        return pedidSelect;
    }

    public void setPedidSelect(Pedidos pedidSelect) {
        this.pedidSelect = pedidSelect;
    }

    public List<Comidas> getListcomida() {
        return listcomida;
    }

    public void setListcomida(List<Comidas> listcomida) {
        this.listcomida = listcomida;
    }

    public List<Pago> getListpagos() {
        return listpagos;
    }

    public void setListpagos(List<Pago> listpagos) {
        this.listpagos = listpagos;
    }

    public List<Detallepedido> getListdp() {
        return listdp;
    }

    public void setListdp(List<Detallepedido> listdp) {
        this.listdp = listdp;
    }

    public List<Pedidos> getListPedid() {
        return listPedid;
    }

    public void setListPedid(List<Pedidos> listPedid) {
        this.listPedid = listPedid;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    
}
