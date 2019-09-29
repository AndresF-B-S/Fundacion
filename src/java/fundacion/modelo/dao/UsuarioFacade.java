/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.dao;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "FundacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario iniciarSesion(int documento, String clave){
        try {
            TypedQuery<Usuario> tq = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.documento = :documento AND u.clave = :clave", Usuario.class);
            tq.setParameter("documento", documento);
            tq.setParameter("clave", clave);
            
            return tq.getSingleResult();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            return null;
        }
    
    }
    
        public Integer obtenerUltimoIdUsuario(){
        try {
            TypedQuery<Integer> tq = getEntityManager().createQuery("SELECT MAX(u.idusuario) FROM Usuario u ", Integer.class);
            
            return tq.getSingleResult();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            return null;
        }
    
    }
    
}
