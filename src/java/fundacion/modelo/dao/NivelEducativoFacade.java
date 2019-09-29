/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.dao;

import fundacion.modelo.entidades.NivelEducativo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres F.B.S
 */
@Stateless
public class NivelEducativoFacade extends AbstractFacade<NivelEducativo> {

    @PersistenceContext(unitName = "FundacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NivelEducativoFacade() {
        super(NivelEducativo.class);
    }
    
}
