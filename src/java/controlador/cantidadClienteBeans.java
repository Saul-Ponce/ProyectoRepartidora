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
import modelo.entidades.Clientes;
import modelo.entidades.Municipio;
import modelo.sesion.bean.ClientesFacade;
import modelo.sesion.bean.MunicipioFacade;





/**
 *
 * @author Dany
 */
@ManagedBean(name="clienteBeans")
@ViewScoped
public class cantidadClienteBeans implements Serializable{
 
    @Inject
    private ClientesFacade clienteFacade;
    private Clientes clienteselect;
    private List<Clientes>listaCliente;
    private List<Clientes>listaClienteServ;
//    private String muni="0619";
    
    
    @Inject
    private MunicipioFacade municipioFacade;
    private Municipio municipioselect;
    private List<Municipio>listaMunicipio;

    public cantidadClienteBeans() {
    }

    
    
    @PostConstruct
    public void init(){
    municipioselect= new Municipio();
    clienteselect= new Clientes();
    mostrarMunicipios();
//    mostrarClientes();
//    mostrarClientes(codclien);
    }
    public List<Municipio>mostrarMunicipios(){
    listaMunicipio=municipioFacade.findAll();
    return listaMunicipio;
    }
    
//    public List<Clientes>mostrarClientes(){
//    listaCliente=consultaClientes(municipioselect);
//    return listaCliente;
//    }
   
    
//    public List<Clientes>mostrarClientes(){
////    EntityManagerFactory emf= Persistence.createEntityManagerFactory("consultasPU");
////        EntityManager em= emf.createEntityManager();
////        listaCliente =  (List<Clientes>) em.createQuery("SELECT c FROM Clientes c WHERE c.municipio.codmunicipio=:id").setParameter("id", municipioselect.getCodmunicipio()).getResultList();
////        em.close();
////        emf.close();
////    return listaCliente;
////    }
    
    public List<Clientes>mostrarClientes(){
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em= emf.createEntityManager();
        listaCliente =  (List<Clientes>) em.createQuery("SELECT c FROM Clientes c WHERE c.codmunicipio.codmunicipio=:id").setParameter("id", municipioselect.getCodmunicipio()).getResultList();
        em.close();
        emf.close();
    return listaCliente;
    }
    
//    public List<Clientes>mostrarClientes(){
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("consultasPU");
//        EntityManager em= emf.createEntityManager();
//    String jpql=("SELECT c FROM Clientes c WHERE c.municipio.codmunicipio=:id");
//    Query query= em.createQuery(jpql, Clientes.class);
//    query.setParameter("id", municipioselect);
//    listaCliente= query.getResultList();
//    return listaCliente;
//    }
//    
//    public List<Clientes>mostrarClientes(){
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("consultasPU");
//        EntityManager em= emf.createEntityManager();
////        clienteselect= (Clientes) em.createNamedQuery("Clientes.findByCodclientes").setParameter("codclientes",codclien).getSingleResult();
////         listaCliente=em.createNamedQuery("Clientes.findAll").getResultList();
//          listaCliente=em.createQuery("Clientes.findByNombre").setParameter("nombre",nombre2).getSingleResult();
//         em.close();
//         return listaCliente;
//    }

    public Clientes getClienteselect() {
        return clienteselect;
    }

    public void setClienteselect(Clientes clienteselect) {
        this.clienteselect = clienteselect;
    }

    public List<Clientes> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Clientes> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Municipio getMunicipioselect() {
        return municipioselect;
    }

    public void setMunicipioselect(Municipio municipioselect) {
        this.municipioselect = municipioselect;
    }

    public List<Municipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public void setListaMunicipio(List<Municipio> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
        
        
    }

    public ClientesFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClientesFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public List<Clientes> getListaClienteServ() {
        return listaClienteServ;
    }

    public void setListaClienteServ(List<Clientes> listaClienteServ) {
        this.listaClienteServ = listaClienteServ;
    }

    public MunicipioFacade getMunicipioFacade() {
        return municipioFacade;
    }

    public void setMunicipioFacade(MunicipioFacade municipioFacade) {
        this.municipioFacade = municipioFacade;
    }
    
    
}


