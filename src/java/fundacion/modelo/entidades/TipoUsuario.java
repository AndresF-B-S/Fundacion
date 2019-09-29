/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres F.B.S
 */
@Entity
@Table(name = "tipos_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
    , @NamedQuery(name = "TipoUsuario.findByIdtiposUsuarios", query = "SELECT t FROM TipoUsuario t WHERE t.idtiposUsuarios = :idtiposUsuarios")
    , @NamedQuery(name = "TipoUsuario.findByNombre", query = "SELECT t FROM TipoUsuario t WHERE t.nombre = :nombre")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipos_usuarios")
    private Integer idtiposUsuarios;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtiposUsuarios", fetch = FetchType.EAGER)
    private List<Usuario> usuarioList;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer idtiposUsuarios) {
        this.idtiposUsuarios = idtiposUsuarios;
    }

    public Integer getIdtiposUsuarios() {
        return idtiposUsuarios;
    }

    public void setIdtiposUsuarios(Integer idtiposUsuarios) {
        this.idtiposUsuarios = idtiposUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiposUsuarios != null ? idtiposUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idtiposUsuarios == null && other.idtiposUsuarios != null) || (this.idtiposUsuarios != null && !this.idtiposUsuarios.equals(other.idtiposUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.TipoUsuario[ idtiposUsuarios=" + idtiposUsuarios + " ]";
    }
    
}
