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
@Table(name = "techos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Techo.findAll", query = "SELECT t FROM Techo t")
    , @NamedQuery(name = "Techo.findById", query = "SELECT t FROM Techo t WHERE t.id = :id")
    , @NamedQuery(name = "Techo.findByNombre", query = "SELECT t FROM Techo t WHERE t.nombre = :nombre")})
public class Techo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "techosId", fetch = FetchType.EAGER)
    private List<Vivienda> viviendaList;

    public Techo() {
    }

    public Techo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Vivienda> getViviendaList() {
        return viviendaList;
    }

    public void setViviendaList(List<Vivienda> viviendaList) {
        this.viviendaList = viviendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Techo)) {
            return false;
        }
        Techo other = (Techo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.Techo[ id=" + id + " ]";
    }
    
}
