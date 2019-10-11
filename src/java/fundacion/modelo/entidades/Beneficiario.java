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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficiario.findAll", query = "SELECT b FROM Beneficiario b")
    , @NamedQuery(name = "Beneficiario.findByUsuariosIdusuario", query = "SELECT b FROM Beneficiario b WHERE b.usuariosIdusuario = :usuariosIdusuario")})
public class Beneficiario implements Serializable {

    @JoinColumn(name = "tutor_idTutor", referencedColumnName = "idTutor")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tutor tutoridTutor;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usuarios_idusuario")
    private Integer usuariosIdusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuarioBeneficiario", fetch = FetchType.EAGER)
    private List<Vivienda> viviendaList;
    @JoinColumn(name = "datos_clinicos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DatoClinico datosClinicosId;
    @JoinColumn(name = "idnivel_educativo", referencedColumnName = "idnivel_educativo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private NivelEducativo idnivelEducativo;
    @JoinColumn(name = "usuarios_idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Beneficiario() {
    }

    public Beneficiario(Integer usuariosIdusuario) {
        this.usuariosIdusuario = usuariosIdusuario;
    }

    public Integer getUsuariosIdusuario() {
        return usuariosIdusuario;
    }

    public void setUsuariosIdusuario(Integer usuariosIdusuario) {
        this.usuariosIdusuario = usuariosIdusuario;
    }

    @XmlTransient
    public List<Vivienda> getViviendaList() {
        return viviendaList;
    }

    public void setViviendaList(List<Vivienda> viviendaList) {
        this.viviendaList = viviendaList;
    }

    public DatoClinico getDatosClinicosId() {
        return datosClinicosId;
    }

    public void setDatosClinicosId(DatoClinico datosClinicosId) {
        this.datosClinicosId = datosClinicosId;
    }

    public NivelEducativo getIdnivelEducativo() {
        return idnivelEducativo;
    }

    public void setIdnivelEducativo(NivelEducativo idnivelEducativo) {
        this.idnivelEducativo = idnivelEducativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariosIdusuario != null ? usuariosIdusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiario)) {
            return false;
        }
        Beneficiario other = (Beneficiario) object;
        if ((this.usuariosIdusuario == null && other.usuariosIdusuario != null) || (this.usuariosIdusuario != null && !this.usuariosIdusuario.equals(other.usuariosIdusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.Beneficiario[ usuariosIdusuario=" + usuariosIdusuario + " ]";
    }

    public Tutor getTutoridTutor() {
        return tutoridTutor;
    }

    public void setTutoridTutor(Tutor tutoridTutor) {
        this.tutoridTutor = tutoridTutor;
    }
    
}
