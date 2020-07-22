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
import modelo.entidades.Cargo;
import modelo.entidades.Empleado;
import modelo.entidades.Experienciaviaje;
import modelo.entidades.Viaje;
import modelo.sesion.bean.CargoFacade;
import modelo.sesion.bean.EmpleadoFacade;
import modelo.sesion.bean.ExperienciaviajeFacade;
import modelo.sesion.bean.ViajeFacade;

/**
 *
 * @author Saul Ponce
 */
@ManagedBean(name="conExpeviaje")
@ViewScoped
public class ConsultExperienciaViajeBeans implements Serializable{
    @Inject
    private EmpleadoFacade conEmpExpViajefacade;
    private Empleado conEmpExpViajeSelect;
    private List<Empleado> conEmpExpViajeList;

    @Inject
    private ViajeFacade viajeExpViajefacade;
    private Viaje viajeExpViajeSelect;
    private List<Viaje> viajeExpViajeList = null;

    @Inject
    private ExperienciaviajeFacade conExpViajefacade;
    private Experienciaviaje conExpViajeSelect;
    private List<Experienciaviaje> conExpViajeList = null;

    @Inject
    private CargoFacade cargoExpViajefacade;
    private Cargo cargoExpViajeSelect;
    private List<Cargo> cargoExpViajeList = null;
    
    private Query query;

    public ConsultExperienciaViajeBeans() {
    }
     @PostConstruct
    public void Init() {
       conEmpExpViajeSelect = new Empleado();
        viajeExpViajeSelect = new Viaje();
        conExpViajeSelect = new Experienciaviaje();
        cargoExpViajeSelect = new Cargo();
        listaempleadito();
        listaViajecito();
        listaexperienciaviajecito();
        Listacargos();
        listaExperienciaViajesFiltradas();
    }

    public List<Empleado> listaempleadito() {
        conEmpExpViajeList = conEmpExpViajefacade.findAll();
        return conEmpExpViajeList;
    }
    public List<Viaje> listaViajecito() {
        viajeExpViajeList = viajeExpViajefacade.findAll();
        return viajeExpViajeList;
    }
    public List<Experienciaviaje> listaexperienciaviajecito() {
        conExpViajeList = conExpViajefacade.findAll();
        return conExpViajeList;
    }
    public List<Cargo> Listacargos() {
        cargoExpViajeList = cargoExpViajefacade.findAll();
        return cargoExpViajeList;
    }
    public List<Viaje> listaExperienciaViajesFiltradas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalPU");
        EntityManager em = emf.createEntityManager();
        String jpql = "select v.codviaje,e.codempleado,(e.nombre||' '||e.apellido)as nombre, exp.experiencia,exp.tiempo_entrega \n" +
"from Viaje v inner join Empleado e on v.codempleado=e.codempleado inner join Experienciaviaje exp on exp.codviaje=v.codviaje inner join Cargo c on e.codcargo=c.codcargo\n" +
"where c.codcargo='R002'";
        query = em.createNativeQuery(jpql);
        viajeExpViajeList = query.getResultList();
        em.close();
        emf.close();
        return viajeExpViajeList;
    }

    public EmpleadoFacade getConEmpExpViajefacade() {
        return conEmpExpViajefacade;
    }

    public void setConEmpExpViajefacade(EmpleadoFacade conEmpExpViajefacade) {
        this.conEmpExpViajefacade = conEmpExpViajefacade;
    }

    public Empleado getConEmpExpViajeSelect() {
        return conEmpExpViajeSelect;
    }

    public void setConEmpExpViajeSelect(Empleado conEmpExpViajeSelect) {
        this.conEmpExpViajeSelect = conEmpExpViajeSelect;
    }

    public List<Empleado> getConEmpExpViajeList() {
        return conEmpExpViajeList;
    }

    public void setConEmpExpViajeList(List<Empleado> conEmpExpViajeList) {
        this.conEmpExpViajeList = conEmpExpViajeList;
    }

    public ViajeFacade getViajeExpViajefacade() {
        return viajeExpViajefacade;
    }

    public void setViajeExpViajefacade(ViajeFacade viajeExpViajefacade) {
        this.viajeExpViajefacade = viajeExpViajefacade;
    }

    public Viaje getViajeExpViajeSelect() {
        return viajeExpViajeSelect;
    }

    public void setViajeExpViajeSelect(Viaje viajeExpViajeSelect) {
        this.viajeExpViajeSelect = viajeExpViajeSelect;
    }

    public List<Viaje> getViajeExpViajeList() {
        return viajeExpViajeList;
    }

    public void setViajeExpViajeList(List<Viaje> viajeExpViajeList) {
        this.viajeExpViajeList = viajeExpViajeList;
    }

    public ExperienciaviajeFacade getConExpViajefacade() {
        return conExpViajefacade;
    }

    public void setConExpViajefacade(ExperienciaviajeFacade conExpViajefacade) {
        this.conExpViajefacade = conExpViajefacade;
    }

    public Experienciaviaje getConExpViajeSelect() {
        return conExpViajeSelect;
    }

    public void setConExpViajeSelect(Experienciaviaje conExpViajeSelect) {
        this.conExpViajeSelect = conExpViajeSelect;
    }

    public List<Experienciaviaje> getConExpViajeList() {
        return conExpViajeList;
    }

    public void setConExpViajeList(List<Experienciaviaje> conExpViajeList) {
        this.conExpViajeList = conExpViajeList;
    }

    public CargoFacade getCargoExpViajefacade() {
        return cargoExpViajefacade;
    }

    public void setCargoExpViajefacade(CargoFacade cargoExpViajefacade) {
        this.cargoExpViajefacade = cargoExpViajefacade;
    }

    public Cargo getCargoExpViajeSelect() {
        return cargoExpViajeSelect;
    }

    public void setCargoExpViajeSelect(Cargo cargoExpViajeSelect) {
        this.cargoExpViajeSelect = cargoExpViajeSelect;
    }

    public List<Cargo> getCargoExpViajeList() {
        return cargoExpViajeList;
    }

    public void setCargoExpViajeList(List<Cargo> cargoExpViajeList) {
        this.cargoExpViajeList = cargoExpViajeList;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
    
    
    
}
