package controlador;





import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.entidades.Pago;
import modelo.entidades.Pedidos;
import modelo.entidades.Tarjeta;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.PedidosFacade;
import modelo.sesion.bean.TarjetaFacade;




/**
 *
 * @author Maritza
 */
@ManagedBean(name = "pagoBeans")
@ViewScoped
public class PagoPedidoBeans extends PedidosBeans implements Serializable {

    @Inject
    private PagoFacade pagoFacade;
    private Pago pagoSelect;
    private List<Pago> lstPago;

    @Inject
    private TarjetaFacade tarjetafacade;
    private Tarjeta tarjetaSelect;
    private List<Tarjeta> lstTarjeta;

    @Inject
    private PedidosFacade pedidofacade;
    private Pedidos pedidoSelectE;
    private List<Pedidos> lstPedidos;
    private List<Pedidos> lstP;

    private Query query;
    static String auxiliarpago;
    public String auxiliarTarjeta;
    public double mon;
    public double costo;
    public double montotal;
    public double envio;
    String cli="1";

    public PagoPedidoBeans() {
    }

    @PostConstruct
    public void init() {

//        BuscarPedido();
//        BuscarTodos();
//        BuscarTarjeta();
        
        pedidoSelectE = new Pedidos();
        auxiliarpago = auxilar;
        pedidoSelectE.setCodpedidos(auxiliarpago);
//        mon=25;
        mon=totalPedido;
        envio=obtenerCostoEnvio();
        tarjetaSelect = new Tarjeta();
        pagoSelect = new Pago();
//        pagoSelect.setCodpago(GenerarCodigo());
        auxiliarTarjeta = GenerarCodigo();
        
        
//        BuscarPedido();

    }

    public String GenerarCodigo() {
        int cod = pagoFacade.findAll().size() + 1;
        if (cod < 10) {
            return "P00" + cod;
        } else if (cod < 100) {
            return "P0" + cod;
        } else if (cod < 1000) {
            return "P" + cod;
        } else {
            return "" + cod;
        }
    }
     

   
    public String insertarPago() {
//        setAuxnum(1);
//        pagoSelect.setCodtarjeta(String.valueOf(GenerarCodigoTarjeta())); 
        pagoSelect.setCodpago(auxiliarTarjeta);
        pagoSelect.setCodpedido(pedidoSelectE);
//          pagoSelect.setCodtarjeta(tarjetaSelect);
        pagoSelect.setMonto(mon);
        pagoSelect.setMontoviaje(costo);
        pagoFacade.create(pagoSelect);
        insertarTarjeta();
        return "FormPedidos.xhtml?faces-redirect=true";
    }
    
     public String GenerarCodigoTarjeta(){
        int cod=tarjetafacade.findAll().size()+1;
        if(cod<10){
            return "T00" + cod;          
        } else if (cod<100){
            return "T0" + cod;           
        } else if (cod<1000){
            return "T" + cod;
        } else {
            return ""+cod;
        }
    }
   
    
    public String insertarTarjeta(){
        pagoSelect.setCodpago(auxiliarTarjeta);
        tarjetaSelect.setCodtarjeta(GenerarCodigoTarjeta());
        tarjetaSelect.setCodpago(pagoSelect);
        tarjetafacade.create(tarjetaSelect);
        return "FrmPago.xhtml?faces-redirect=true";
    }

    public String eliminarPago() {
        pagoSelect.setCodpago(auxiliarTarjeta);
        pagoFacade.remove(pagoSelect);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TblPago.xhtml?faces-redirect=true";
    }

    public String actualizarPago() {
//        pagoSelect.setCodtarjeta(tarjetaSelect);
        pagoSelect.setCodpago(auxiliarTarjeta);
        pagoFacade.edit(pagoSelect);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos actualizados"));
        return "TblPago.xhtml?faces-redirect=true";
    }

    public List<Pago> BuscarTodos() {
        lstPago = pagoFacade.findAll();
        return lstPago;
    }

    public List<Tarjeta> BuscarTarjeta() {
        lstTarjeta = tarjetafacade.findAll();
        return lstTarjeta;
    }

    public List<Pedidos> BuscarPedido() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        lstPedidos= (List<Pedidos>)em.createQuery("SELECT d FROM Detallepedido d WHERE d.codpedido.codpedidos=:id").setParameter("id", pedidoSelectE.getCodpedidos()).getResultList();
        em.close();
        emf.close();
        return lstPedidos;
    }
    
    public double obtenerCostoEnvio(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
         costo=(double) em.createQuery("SELECT c.codmunicipio.costoenvio FROM Clientes c WHERE c.codclientes=:id").setParameter("id", cli).getSingleResult();
        montotal=mon+costo;
        em.close();
        emf.close();
        return costo;
    }
    
    

    public PagoFacade getPagoFacade() {
        return pagoFacade;
    }

    public void setPagoFacade(PagoFacade pagoFacade) {
        this.pagoFacade = pagoFacade;
    }

    public Pago getPagoSelect() {
        return pagoSelect;
    }

    public void setPagoSelect(Pago pagoSelect) {
        this.pagoSelect = pagoSelect;
    }

    public List<Pago> getLstPago() {
        return lstPago;
    }

    public void setLstPago(List<Pago> lstPago) {
        this.lstPago = lstPago;
    }

    public TarjetaFacade getTarjetafacade() {
        return tarjetafacade;
    }

    public void setTarjetafacade(TarjetaFacade tarjetafacade) {
        this.tarjetafacade = tarjetafacade;
    }

    public Tarjeta getTarjetaSelect() {
        return tarjetaSelect;
    }

    public void setTarjetaSelect(Tarjeta tarjetaSelect) {
        this.tarjetaSelect = tarjetaSelect;
    }

    public List<Tarjeta> getLstTarjeta() {
        return lstTarjeta;
    }

    public void setLstTarjeta(List<Tarjeta> lstTarjeta) {
        this.lstTarjeta = lstTarjeta;
    }

    public PedidosFacade getPedidofacade() {
        return pedidofacade;
    }

    public void setPedidofacade(PedidosFacade pedidofacade) {
        this.pedidofacade = pedidofacade;
    }

    public Pedidos getPedidoSelectE() {
        return pedidoSelectE;
    }

    public void setPedidoSelectE(Pedidos pedidoSelectE) {
        this.pedidoSelectE = pedidoSelectE;
    }

    public List<Pedidos> getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(List<Pedidos> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public static String getAuxiliarpago() {
        return auxiliarpago;
    }

    public static void setAuxiliarpago(String auxiliarpago) {
        PagoPedidoBeans.auxiliarpago = auxiliarpago;
    }

    public double getMon() {
        return mon;
    }

    public void setMon(double mon) {
        this.mon = mon;
    }

    

    public List<Pedidos> getLstP() {
        return lstP;
    }

    public void setLstP(List<Pedidos> lstP) {
        this.lstP = lstP;
    }

    public double getEnvio() {
        return envio;
    }

    public void setEnvio(double envio) {
        this.envio = envio;
    }

    public double getMontotal() {
        return montotal;
    }

    public void setMontotal(double montotal) {
        this.montotal = montotal;
    }

    public String getAuxiliarTarjeta() {
        return auxiliarTarjeta;
    }

    public void setAuxiliarTarjeta(String auxiliarTarjeta) {
        this.auxiliarTarjeta = auxiliarTarjeta;
    }
    
    
    
    

}