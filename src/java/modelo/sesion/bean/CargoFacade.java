/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.sesion.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.entidades.Cargo;

/**
 *
 * @author Saul Ponce
 */
@Stateless
public class CargoFacade extends AbstractFacade<Cargo> {

    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }
    
}
