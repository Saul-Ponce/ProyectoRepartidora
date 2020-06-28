/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.sesion.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidades.Viaje;

/**
 *
 * @author Saul Ponce
 */
@Stateless
public class ViajeFacade extends AbstractFacade<Viaje> {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViajeFacade() {
        super(Viaje.class);
    }
    
}
