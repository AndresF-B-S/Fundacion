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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v")
    , @NamedQuery(name = "Vivienda.findById", query = "SELECT v FROM Vivienda v WHERE v.id = :id")
    , @NamedQuery(name = "Vivienda.findByDireccion", query = "SELECT v FROM Vivienda v WHERE v.direccion = :direccion")
    , @NamedQuery(name = "Vivienda.findByBarrio", query = "SELECT v FROM Vivienda v WHERE v.barrio = :barrio")
    , @NamedQuery(name = "Vivienda.findByEstadoVivienda", query = "SELECT v FROM Vivienda v WHERE v.estadoVivienda = :estadoVivienda")})
public class Vivienda implements Serializable {

    private Integer numerodormitorios;
    private Short sala;
    private Short comedor;
    private Short cocina;
    private Short banioprivado;
    private Short baniocolectivo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "barrio")
    private String barrio;
    @Basic(optional = false)
    @Column(name = "estado_vivienda")
    private int estadoVivienda;
    @JoinColumn(name = "idusuario_beneficiario", referencedColumnName = "usuarios_idusuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Beneficiario idusuarioBeneficiario;
    @JoinColumn(name = "materialvivienda_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MaterialVivienda materialviviendaId;
    @JoinColumn(name = "techos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Techo techosId;
    @JoinColumn(name = "tipovivienda_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoVivienda tipoviviendaId;
    

    public Vivienda() {
    }

    public Vivienda(Integer id) {
        this.id = id;
    }

    public Vivienda(Integer id, String direccion, String barrio, int estadoVivienda) {
        this.id = id;
        this.direccion = direccion;
        this.barrio = barrio;
        this.estadoVivienda = estadoVivienda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getEstadoVivienda() {
        return estadoVivienda;
    }

    public void setEstadoVivienda(int estadoVivienda) {
        this.estadoVivienda = estadoVivienda;
    }

    public Beneficiario getIdusuarioBeneficiario() {
        return idusuarioBeneficiario;
    }

    public void setIdusuarioBeneficiario(Beneficiario idusuarioBeneficiario) {
        this.idusuarioBeneficiario = idusuarioBeneficiario;
    }

    public MaterialVivienda getMaterialviviendaId() {
        return materialviviendaId;
    }

    public void setMaterialviviendaId(MaterialVivienda materialviviendaId) {
        this.materialviviendaId = materialviviendaId;
    }

    public Techo getTechosId() {
        return techosId;
    }

    public void setTechosId(Techo techosId) {
        this.techosId = techosId;
    }

    public TipoVivienda getTipoviviendaId() {
        return tipoviviendaId;
    }

    public void setTipoviviendaId(TipoVivienda tipoviviendaId) {
        this.tipoviviendaId = tipoviviendaId;
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
        if (!(object instanceof Vivienda)) {
            return false;
        }
        Vivienda other = (Vivienda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundacion.modelo.entidades.Vivienda[ id=" + id + " ]";
    }

    public Integer getNumerodormitorios() {
        return numerodormitorios;
    }

    public void setNumerodormitorios(Integer numerodormitorios) {
        this.numerodormitorios = numerodormitorios;
    }

    public Short getSala() {
        return sala;
    }

    public void setSala(Short sala) {
        this.sala = sala;
    }

    public Short getComedor() {
        return comedor;
    }

    public void setComedor(Short comedor) {
        this.comedor = comedor;
    }

    public Short getCocina() {
        return cocina;
    }

    public void setCocina(Short cocina) {
        this.cocina = cocina;
    }

    public Short getBanioprivado() {
        return banioprivado;
    }

    public void setBanioprivado(Short banioprivado) {
        this.banioprivado = banioprivado;
    }

    public Short getBaniocolectivo() {
        return baniocolectivo;
    }

    public void setBaniocolectivo(Short baniocolectivo) {
        this.baniocolectivo = baniocolectivo;
    }
    
}
