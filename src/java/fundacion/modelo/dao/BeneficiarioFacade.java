/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.dao;

import fundacion.modelo.entidades.Beneficiario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class BeneficiarioFacade extends AbstractFacade<Beneficiario> {

    @PersistenceContext(unitName = "FundacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BeneficiarioFacade() {
        super(Beneficiario.class);
    }
    
}
