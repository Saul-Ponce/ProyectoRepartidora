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
import modelo.entidades.Clientes;
import modelo.entidades.Municipio;
import modelo.sesion.bean.ClientesFacade;
import modelo.sesion.bean.MunicipioFacade;

/**
 *
 * @author LENOVO
 */
@ManagedBean(name="clibean")
@ViewScoped
public class ClienteBeans implements Serializable{
    @Inject
    private ClientesFacade clientesFacade;
    private List<Clientes>cliList;
    private Clientes clienteSelect;
@Inject     
    private MunicipioFacade municipioFacade;
    private List<Municipio>muniList;
    private Municipio municipioSelect;
    
    @PostConstruct
    public void init(){
    buscarTodosClientes();
    clienteSelect=new Clientes();
    buscarMunicipios();
    municipioSelect=new Municipio();
    }

    public ClienteBeans() {
    }
    public List<Municipio> buscarMunicipios() {
        muniList = municipioFacade.findAll();
        return muniList;
    }
    
    public String insertarClientes(){
    clienteSelect.setCodmunicipio(municipioSelect);
    clientesFacade.create(clienteSelect);
    return "TblClientes.xhtml?faces-redirect=true";
    }
    public String modificarClientes(){
    clienteSelect.setCodmunicipio(municipioSelect);
    clientesFacade.edit(clienteSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos actualizados"));
    return "TblClientes.xhtml?faces-redirect=true";
    }
    
     public String eliminarClientes(){
    clientesFacade.remove(clienteSelect);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"informacion","Datos eliminados"));
    return "TblClientes.xhtml?faces-redirect=true";
    }
   public List<Clientes>buscarTodosClientes(){
   cliList=clientesFacade.findAll();
   return cliList;
   }
//metodos setter

    public ClientesFacade getClientesFacade() {
        return clientesFacade;
    }

    public void setClientesFacade(ClientesFacade clientesFacade) {
        this.clientesFacade = clientesFacade;
    }

    public List<Clientes> getCliList() {
        return cliList;
    }

    public void setCliList(List<Clientes> cliList) {
        this.cliList = cliList;
    }

    public Clientes getClienteSelect() {
        return clienteSelect;
    }

    public void setClienteSelect(Clientes clienteSelect) {
        this.clienteSelect = clienteSelect;
    }

    public MunicipioFacade getMunicipioFacade() {
        return municipioFacade;
    }

    public void setMunicipioFacade(MunicipioFacade municipioFacade) {
        this.municipioFacade = municipioFacade;
    }

    public List<Municipio> getMuniList() {
        return muniList;
    }

    public void setMuniList(List<Municipio> muniList) {
        this.muniList = muniList;
    }

    public Municipio getMunicipioSelect() {
        return municipioSelect;
    }

    public void setMunicipioSelect(Municipio municipioSelect) {
        this.municipioSelect = municipioSelect;
    }

}


