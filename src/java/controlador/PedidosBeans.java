/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;





import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Clientes;
import modelo.entidades.Comidas;
import modelo.entidades.Detallepedido;
import modelo.entidades.Pago;
import modelo.entidades.Pedidos;
import modelo.entidades.Restaurante;
import modelo.sesion.bean.ClientesFacade;
import modelo.sesion.bean.ComidasFacade;
import modelo.sesion.bean.DetallepedidoFacade;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.PedidosFacade;
import modelo.sesion.bean.RestauranteFacade;


import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Dany
 */
@ManagedBean
@ViewScoped
public class PedidosBeans implements Serializable {

    @Inject
    private PedidosFacade pedidosFacade;
    private Pedidos pedidoSelect;
    private List<Pedidos> listaPedidos;
    
    @Inject
    private ClientesFacade clientesFacade;
    private Clientes clientesSelect;
    private List<Clientes> listaCliente;

    @Inject
    private DetallepedidoFacade dtpedidoFacade;
    private Detallepedido dtpedidoSelect;
    private  List<Detallepedido> listaDtPedido;
    private List<Detallepedido> listaDtPedidoAux;

    @Inject
    private ComidasFacade comidafacade;
    private Comidas comidaSelect;
    private Comidas comidaSelectPrueba;
    private List<Comidas> listaComidas;
    private List<Comidas> listaComidaSelect;

    @Inject
    private RestauranteFacade resfacade;
    private Restaurante resSelect;
    private List<Restaurante> listaRestaurante;
    
    @Inject
    private PagoFacade pagofacade;
    private Pago pagoSelect;
    private List<Pago>listaPago;
    
    //variables auxiliares
    static int num = 0;
    private int auxnum=0;
    public static double totalPedido;
    public static String auxilar;
    public String codigoauxiliar;
    private String hora;
    private String fecha;
    public double monto;

    //constructor vacio
    public PedidosBeans() {
       
    }

    @PostConstruct
    public void init() {
        pagoSelect= new Pago();
//        comprobandoPago();
//        num=0;
        comidaSelect = new Comidas();
        resSelect = new Restaurante();
        dtpedidoSelect = new Detallepedido();
        clientesSelect = new Clientes();
        pedidoSelect = new Pedidos();
        
        selectCliente();
        mostrarDetallesPedido();
        hora=obtenerHora();
        fecha=obtenerFecha();
        mostrarListaRestaurante();
        obtenerMontoFinal();
//        mostrarPagos();
//         comprobandoPago();
        
    }

    public List<Comidas> mostrarComidas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        listaComidas = (List<Comidas>) em.createQuery("SELECT c FROM Comidas c WHERE c.codrestaurante.codrestaurante=:id").setParameter("id", resSelect.getCodrestaurante()).getResultList();
        em.close();
        emf.close();
        return listaComidas;
    }

     public List<Detallepedido> mostrarDetallesPedido() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        listaDtPedido= (List<Detallepedido>)em.createQuery("SELECT d FROM Detallepedido d WHERE d.codpedido.codpedidos=:id").setParameter("id", auxilar).getResultList();
        em.close();
        emf.close();
        return listaDtPedido;
    }
     
//      public List<Pago> mostrarPagos() {
//        
//        return listaPago;
//    }
      
      public void comprobandoPago(){
//          mostrarPagos();
           String pedi = new String();
                      String pedi2 = new String();

          listaPago=pagofacade.findAll();
          for (int i = 0; i < listaPago.size(); i++) {
               pedi=listaPago.get(i).getCodpedido().getCodpedidos();
              if((pedi.equals(auxilar))){
              num=0;
              }
          }
          
      }
     
     
     
    public List<Restaurante> mostrarListaRestaurante() {
        listaRestaurante = resfacade.findAll();
        return listaRestaurante;
    }

    public int numAleatorios() {
        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = aleatorio.nextInt(9000);
        aleatorio.setSeed(System.currentTimeMillis());
//aleatorio.setSeed(aleatorio.getLong());
        return intAletorio;
    }
    
    public String codigoPedido(){
        String codi;
    int codigo=pedidosFacade.findAll().size() +1 ;
    if (codigo<10){
    codi="000".concat(String.valueOf(codigo));
    }else if(codigo>=10 && codigo<=99){
    codi="00".concat(String.valueOf(codigo));
    }else if(codigo>=100 && codigo<=999){
        codi="0".concat(String.valueOf(codigo));
    }else{
    codi=String.valueOf(codigo);
    }
    return codi;
    }
    public String codigoDTPedido(){
        String codi;
    int codigo=dtpedidoFacade.findAll().size() +1 ;
    if (codigo<10){
    codi="000".concat(String.valueOf(codigo));
    }else if(codigo>=10 && codigo<=99){
    codi="00".concat(String.valueOf(codigo));
    }else if(codigo>=100 && codigo<=999){
        codi="0".concat(String.valueOf(codigo));
    }else{
    codi=String.valueOf(codigo);
    }
    return codi;
    }

    public String insertarPedido() {
       
       
      
        java.util.Date d = new java.util.Date();  
        java.sql.Date date2 = new java.sql.Date(d.getTime());
//        Date sql = new Date(fecha);
        num = 1;
        pedidoSelect.setCodpedidos(codigoPedido());
        auxilar = pedidoSelect.getCodpedidos();
//        clientesSelect.setCodclientes("1");
        pedidoSelect.setFechapedido(date2);
        pedidoSelect.setHorapedido(hora);
        pedidoSelect.setCodcliente(clientesSelect);
        pedidosFacade.create(pedidoSelect);
//        selectCliente();
//        obtenerUltimo();
        
        return "";
    }
    
    public List<Clientes> selectCliente(){
      listaCliente=clientesFacade.findAll();
        return listaCliente;
      }
      
      public Clientes obtenerUltimo(){
          selectCliente();
          for (int i = 0; i <clientesFacade.findAll().size(); i++) {
              clientesSelect=listaCliente.get(i);
          }
          return clientesSelect;
      }

//    public List<Detallepedido> insertarDetalle() {
//        if (num != 1) {
//            insertarPedido();
//        }
//        pedidoSelect.setCodpedidos(auxilar);
//        dtpedidoSelect.setCoddetalle(String.valueOf(numAleatorios()));
//        dtpedidoSelect.setCodpedido(pedidoSelect);
////        dtpedidoSelect.setCodcomida(comidaSelect);
//        dtpedidoSelect.setCodcomida(comidaSelectPrueba);
////    listaDtPedidoAux.add(dtpedidoSelect);
//        dtpedidoFacade.create(dtpedidoSelect);
////        mostrarDetallesPedido();
////        listaDtPedidoAux.clear();
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
//        EntityManager em = emf.createEntityManager();
//        listaDtPedido = em.createQuery("SELECT dt FROM Detallepedido dt WHERE dt.codpedido.codpedidos=:id").setParameter("id", pedidoSelect.getCodpedidos()).getResultList();
//        em.close();
//        emf.close();
////        listaDtPedido.addAll(listaDtPedidoAux);
//        return listaDtPedido;
////         return "FormPedidos.xhtml?faces-redirect=true";
//    }

    public String insertarDetalle() {
        comprobandoPago();
        if(num!= 1){
        insertarPedido();
        }
        pedidoSelect.setCodpedidos(auxilar);
        dtpedidoSelect.setCoddetalle(codigoDTPedido());
        dtpedidoSelect.setCodpedido(pedidoSelect);
        dtpedidoSelect.setCodcomida(comidaSelectPrueba);
        dtpedidoFacade.create(dtpedidoSelect);

        return "MostrarDetallePedido.xhtml?faces-redirect=true";
    }
    
    public String eliminarDetalle(){
    dtpedidoFacade.remove(dtpedidoSelect);
    return "MostrarDetallePedido.xhtml?faces-redirect=true";
    }
    
    public String eliminarTodosDetalles(){
    for(int i=0; i<listaDtPedido.size(); i++){
    Detallepedido dtpedido= new Detallepedido();
    dtpedido=listaDtPedido.get(i);
    dtpedidoFacade.remove(dtpedido);
    }
     return  "FormPedidos.xhtml?faces-redirect=true";
    }
    
    public String eliminarPedido(){
        pedidoSelect.setCodpedidos(auxilar);
    pedidosFacade.remove(pedidoSelect);
    return "FormPedidos.xhtml?faces-redirect=true";
    }
//    @Override
//    @Transactional
//    public String eliminarTodosDetalles(){
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
//    EntityManager em = emf.createEntityManager();
//    
////    em.joinTransaction();/
//    
//    Query query=em.createNativeQuery("DELETE FROM Detallepedido");
////    em.joinTransaction();
////    em.joinTransaction();
////    Query query=em.createQuery("DELETE FROM Detallepedido d WHERE d.codpedido.codpedidos=:id").setParameter("id", auxilar);
//    
//    query.executeUpdate();
////    em.getTransaction().commit();
//        em.close();
//        emf.close();
//        return  "FormPedidos.xhtml?faces-redirect=true";
//    }
    
    public String actualizarDetalle(){
    dtpedidoFacade.edit(dtpedidoSelect);
    return "MostrarDetallePedido.xhtml?faces-redirect=true";
    }
    
    
    
    public void onRowSelect(SelectEvent<Comidas> event) {
        FacesMessage msg = new FacesMessage("Comida Selected", event.getObject().getCodcomida());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        comidaSelectPrueba = event.getObject();
//        comidaSelectPrueba.setCodcomida(event.getObject().getCodcomida());
    }
//    

    public void onRowUnselect(UnselectEvent<Comidas> event) {
        FacesMessage msg = new FacesMessage("Car Unselected", (String) event.getObject().getCodcomida());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     public static String obtenerFecha(){
    Date fecha = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/YYYY");
      return dt.format(fecha);
    }
    
     public static String obtenerHora(){
    Date fecha = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        return dt.format(fecha);
    }
     
//     public void obtenerTotal(int cantidad, double precio){
//     total=cantidad*precio;
//     }
     
     public void obtenerMontoFinal(){
         monto=0;
         double mult=0;
         for (int i = 0; i < listaDtPedido.size(); i++) {
            mult= listaDtPedido.get(i).getCantidad()*listaDtPedido.get(i).getCodcomida().getPrecio();
            monto=monto+mult;
         }
         totalPedido=monto;
     }
    

    /// GETTER Y SETTER
    public PedidosFacade getPedidosFacade() {
        return pedidosFacade;
    }

    public void setPedidosFacade(PedidosFacade pedidosFacade) {
        this.pedidosFacade = pedidosFacade;
    }

    public Pedidos getPedidoSelect() {
        return pedidoSelect;
    }

    public void setPedidoSelect(Pedidos pedidoSelect) {
        this.pedidoSelect = pedidoSelect;
    }

    public List<Pedidos> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public ClientesFacade getClientesFacade() {
        return clientesFacade;
    }

    public void setClientesFacade(ClientesFacade clientesFacade) {
        this.clientesFacade = clientesFacade;
    }

    public Clientes getClientesSelect() {
        return clientesSelect;
    }

    public void setClientesSelect(Clientes clientesSelect) {
        this.clientesSelect = clientesSelect;
    }

    public List<Clientes> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Clientes> listaCliente) {
        this.listaCliente = listaCliente;
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

    public List<Detallepedido> getListaDtPedido() {
        return listaDtPedido;
    }

    public void setListaDtPedido(List<Detallepedido> listaDtPedido) {
        this.listaDtPedido = listaDtPedido;
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

    public Comidas getComidaSelectPrueba() {
        return comidaSelectPrueba;
    }

    public void setComidaSelectPrueba(Comidas comidaSelectPrueba) {
        this.comidaSelectPrueba = comidaSelectPrueba;
    }

    public List<Comidas> getListaComidas() {
        return listaComidas;
    }

    public void setListaComidas(List<Comidas> listaComidas) {
        this.listaComidas = listaComidas;
    }

    public List<Comidas> getListaComidaSelect() {
        return listaComidaSelect;
    }

    public void setListaComidaSelect(List<Comidas> listaComidaSelect) {
        this.listaComidaSelect = listaComidaSelect;
    }

    public RestauranteFacade getResfacade() {
        return resfacade;
    }

    public void setResfacade(RestauranteFacade resfacade) {
        this.resfacade = resfacade;
    }

    public Restaurante getResSelect() {
        return resSelect;
    }

    public void setResSelect(Restaurante resSelect) {
        this.resSelect = resSelect;
    }

    public List<Restaurante> getListaRestaurante() {
        return listaRestaurante;
    }

    public void setListaRestaurante(List<Restaurante> listaRestaurante) {
        this.listaRestaurante = listaRestaurante;
    }

    public List<Detallepedido> getListaDtPedidoAux() {
        return listaDtPedidoAux;
    }

    public void setListaDtPedidoAux(List<Detallepedido> listaDtPedidoAux) {
        this.listaDtPedidoAux = listaDtPedidoAux;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAuxnum() {
        return auxnum;
    }

    public void setAuxnum(int auxnum) {
        this.auxnum = auxnum;
    }

   

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    

   

   
    
    
    

    
   
    
   

    
}
