/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import modelo.entidades.Pago;
import modelo.sesion.bean.PagoFacade;

/**
 *
 * @author Maritza
 */
@ManagedBean(name = "pBeans")
@ViewScoped
public class MostrarPagosBean implements Serializable{
    
     @Inject
    private PagoFacade pagoFacade;
    private Pago pagoSelect;
    private List<Pago> lstPago;

    public MostrarPagosBean() {
    }
    
    
    @PostConstruct
    public void init() {
        pagoSelect = new Pago();
       BuscarTodos();
    }
    
     public List<Pago> BuscarTodos() {
        lstPago = pagoFacade.findAll();
        return lstPago;
     }

      public String eliminarPago() {
        pagoFacade.remove(pagoSelect);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Datos eliminados"));
        return "TblPago.xhtml?faces-redirect=true";
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
     
     

    
}
