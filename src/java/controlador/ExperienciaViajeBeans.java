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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.Experienciaviaje;
import modelo.entidades.Pago;
import modelo.entidades.Viaje;
import modelo.sesion.bean.ExperienciaviajeFacade;
import modelo.sesion.bean.PagoFacade;
import modelo.sesion.bean.ViajeFacade;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;




@ManagedBean
@ViewScoped
public class ExperienciaViajeBeans implements Serializable{
    @Inject
    private ExperienciaviajeFacade expViajeFacade;
    private Experienciaviaje expViajeSelect;
    private List<Experienciaviaje>listaexpViajeSelect;
    
    @Inject
    private ViajeFacade viajeFacade;
    private Viaje viajeSelect;
    private Viaje viajeSelectPrueba;
    private List<Viaje>listaViajeSelect;
    
    @Inject
    private PagoFacade pagoFacade;
    private Pago pagoselect;
    private Pago pagoselectprueba;
    
    private List<Pago>listaPagoSelect;

    public ExperienciaViajeBeans() {
    }
    
    @PostConstruct
    public void init(){
        mostrarlistaViaje();
    expViajeSelect= new Experienciaviaje();
    viajeSelect= new Viaje();
    viajeSelectPrueba= new Viaje();
//    pagoselect=new Pago();
//    mostrarlistaPago();
    
//    viajeSelect.setCodviaje("1");
    mostrarlistaExperiencia();
    }
    
    public List<Viaje>mostrarlistaViaje(){
    listaViajeSelect=viajeFacade.findAll();
    return listaViajeSelect;
    }
    
    public List<Pago>mostrarlistaPago(){
    listaPagoSelect=pagoFacade.findAll();
    return listaPagoSelect;
    }
    
    public List<Experienciaviaje>mostrarlistaExperiencia(){
    listaexpViajeSelect=expViajeFacade.findAll();
    return listaexpViajeSelect;
    }
    
    public String insertarExperiencia(){
//        viajeSelect.setCodviaje(pagoselectprueba.getCodviaje());
        expViajeSelect.setCodviaje(viajeSelectPrueba);
    expViajeFacade.create(expViajeSelect);
    return "MostrarExperienciaViajeEmpleado.xhtml?faces-redirect=true";
    }
    
    public String eliminarExperiencia(){
//        expViajeSelect.setCodviaje(viajeSelect);
    expViajeFacade.remove(expViajeSelect);
    return "MostrarExperienciaViajeEmpleado.xhtml?faces-redirect=true";
    }
    public String actualizarExperiencia(){
//        expViajeSelect.setCodviaje(viajeSelect);
    expViajeFacade.edit(expViajeSelect);
    return "MostrarExperienciaViajeEmpleado.xhtml?faces-redirect=true";
    }
    
    
    
     public List<Experienciaviaje> mostrarExperienciaViaje() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        listaexpViajeSelect= (List<Experienciaviaje>)em.createQuery("SELECT d FROM Experienciaviaje d WHERE d.codviaje.codviaje=:id").setParameter("id", viajeSelect.getCodviaje()).getResultList();
        em.close();
        emf.close();
        return listaexpViajeSelect;
    }
     
      public void onRowSelect(SelectEvent<Pago> event) {
        FacesMessage msg = new FacesMessage("Comida Selected", event.getObject().getCodpago());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        pagoselectprueba = event.getObject();
//        comidaSelectPrueba.setCodcomida(event.getObject().getCodcomida());
    }
//    

    public void onRowUnselect(UnselectEvent<Pago> event) {
        FacesMessage msg = new FacesMessage("Car Unselected", (String) event.getObject().getCodpago());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ExperienciaviajeFacade getExpViajeFacade() {
        return expViajeFacade;
    }

    public void setExpViajeFacade(ExperienciaviajeFacade expViajeFacade) {
        this.expViajeFacade = expViajeFacade;
    }

    public Experienciaviaje getExpViajeSelect() {
        return expViajeSelect;
    }

    public void setExpViajeSelect(Experienciaviaje expViajeSelect) {
        this.expViajeSelect = expViajeSelect;
    }

    public List<Experienciaviaje> getListaexpViajeSelect() {
        return listaexpViajeSelect;
    }

    public void setListaexpViajeSelect(List<Experienciaviaje> listaexpViajeSelect) {
        this.listaexpViajeSelect = listaexpViajeSelect;
    }

    public ViajeFacade getViajeFacade() {
        return viajeFacade;
    }

    public void setViajeFacade(ViajeFacade viajeFacade) {
        this.viajeFacade = viajeFacade;
    }

    public Viaje getViajeSelect() {
        return viajeSelect;
    }

    public void setViajeSelect(Viaje viajeSelect) {
        this.viajeSelect = viajeSelect;
    }

    public List<Viaje> getListaViajeSelect() {
        return listaViajeSelect;
    }

    public void setListaViajeSelect(List<Viaje> listaViajeSelect) {
        this.listaViajeSelect = listaViajeSelect;
    }

    public Viaje getViajeSelectPrueba() {
        return viajeSelectPrueba;
    }

    public void setViajeSelectPrueba(Viaje viajeSelectPrueba) {
        this.viajeSelectPrueba = viajeSelectPrueba;
    }

    public Pago getPagoselect() {
        return pagoselect;
    }

    public void setPagoselect(Pago pagoselect) {
        this.pagoselect = pagoselect;
    }

    public PagoFacade getPagoFacade() {
        return pagoFacade;
    }

    public void setPagoFacade(PagoFacade pagoFacade) {
        this.pagoFacade = pagoFacade;
    }

    public List<Pago> getListaPagoSelect() {
        return listaPagoSelect;
    }

    public void setListaPagoSelect(List<Pago> listaPagoSelect) {
        this.listaPagoSelect = listaPagoSelect;
    }

    public Pago getPagoselectprueba() {
        return pagoselectprueba;
    }

    public void setPagoselectprueba(Pago pagoselectprueba) {
        this.pagoselectprueba = pagoselectprueba;
    }
    
    
    
    
}
