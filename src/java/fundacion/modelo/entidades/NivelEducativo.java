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
@Table(name = "nivel_educativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEducativo.findAll", query = "SELECT n FROM NivelEducativo n")
    , @NamedQuery(name = "NivelEducativo.findByIdnivelEducativo", query = "SELECT n FROM NivelEducativo n WHERE n.idnivelEducativo = :idnivelEducativo")
    , @NamedQuery(name = "NivelEducativo.findByNivelEducativo", query = "SELECT n FROM NivelEducativo n WHERE n.nivelEducativo = :nivelEducativo")})
public class NivelEducativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnivel_educativo")
    private Integer idnivelEducativo;
    @Column(name = "nivel_educativo")
    private String nivelEducativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnivelEducativo", fetch = FetchType.EAGER)
    private List<Beneficiario> beneficiarioList;

    public NivelEducativo() {
    }

    public NivelEducativo(Integer idnivelEducativo) {
        this.idnivelEducativo = idnivelEducativo;
    }

    public Integer getIdnivelEducativo() {
        return idnivelEducativo;
    }

    public void setIdnivelEducativo(Integer idnivelEducativo) {
        this.idnivelEducativo = idnivelEducativo;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
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
        hash += (idnivelEducativo != null ? idnivelEducativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEducativo)) {
            return false;
        }
        NivelEducativo other = (NivelEducativo) object;
        if ((this.idnivelEducativo == null && other.idnivelEducativo != null) || (this.idnivelEducativo != null && !this.idnivelEducativo.equals(other.idnivelEducativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.NivelEducativo[ idnivelEducativo=" + idnivelEducativo + " ]";
    }
    
}
