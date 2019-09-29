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
@Table(name = "datos_clinicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatoClinico.findAll", query = "SELECT d FROM DatoClinico d")
    , @NamedQuery(name = "DatoClinico.findById", query = "SELECT d FROM DatoClinico d WHERE d.id = :id")
    , @NamedQuery(name = "DatoClinico.findByDescripcion", query = "SELECT d FROM DatoClinico d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DatoClinico.findByNombreServicioSalud", query = "SELECT d FROM DatoClinico d WHERE d.nombreServicioSalud = :nombreServicioSalud")
    , @NamedQuery(name = "DatoClinico.findByEstado", query = "SELECT d FROM DatoClinico d WHERE d.estado = :estado")
    , @NamedQuery(name = "DatoClinico.findByTipoSangre", query = "SELECT d FROM DatoClinico d WHERE d.tipoSangre = :tipoSangre")})
public class DatoClinico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "nombre_servicio_salud")
    private String nombreServicioSalud;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "tipo_sangre")
    private String tipoSangre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosClinicosId", fetch = FetchType.EAGER)
    private List<Beneficiario> beneficiarioList;

    public DatoClinico() {
    }

    public DatoClinico(Integer id) {
        this.id = id;
    }

    public DatoClinico(Integer id, String nombreServicioSalud, String tipoSangre) {
        this.id = id;
        this.nombreServicioSalud = nombreServicioSalud;
        this.tipoSangre = tipoSangre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreServicioSalud() {
        return nombreServicioSalud;
    }

    public void setNombreServicioSalud(String nombreServicioSalud) {
        this.nombreServicioSalud = nombreServicioSalud;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    @XmlTransient
    public List<Beneficiario> getBeneficiarioList() {
        return beneficiarioList;
    }

    public void setBeneficiarioList(List<Beneficiario> beneficiarioList) {
        this.beneficiarioList = beneficiarioList;
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
        if (!(object instanceof DatoClinico)) {
            return false;
        }
        DatoClinico other = (DatoClinico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.DatoClinico[ id=" + id + " ]";
    }
    
}
