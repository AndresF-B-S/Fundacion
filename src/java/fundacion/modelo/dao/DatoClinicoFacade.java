/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.dao;

import fundacion.modelo.entidades.DatoClinico;
import fundacion.modelo.entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres F.B.S
 */
@Stateless
public class DatoClinicoFacade extends AbstractFacade<DatoClinico> {

    @PersistenceContext(unitName = "FundacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatoClinicoFacade() {
        super(DatoClinico.class);
    }
    
    public Integer obtenerUltimoIdDatoClinico(){
        try {
            TypedQuery<Integer> tq = getEntityManager().createQuery("SELECT MAX(d.id) FROM DatoClinico d ", Integer.class);
            
            return tq.getSingleResult();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            return null;
        }
    
    }
    
    
}
