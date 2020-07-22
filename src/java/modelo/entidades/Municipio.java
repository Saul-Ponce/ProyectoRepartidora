/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saul Ponce
 */
@Entity
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")
    , @NamedQuery(name = "Municipio.findByCodmunicipio", query = "SELECT m FROM Municipio m WHERE m.codmunicipio = :codmunicipio")
    , @NamedQuery(name = "Municipio.findByNombreMunicipio", query = "SELECT m FROM Municipio m WHERE m.nombreMunicipio = :nombreMunicipio")
    , @NamedQuery(name = "Municipio.findByCostoenvio", query = "SELECT m FROM Municipio m WHERE m.costoenvio = :costoenvio")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codmunicipio")
    private String codmunicipio;
    @Size(max = 20)
    @Column(name = "nombre_municipio")
    private String nombreMunicipio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costoenvio")
    private Double costoenvio;
    @OneToMany(mappedBy = "codmunicipio")
    private List<Clientes> clientesList;

    public Municipio() {
    }

    public Municipio(String codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getCodmunicipio() {
        return codmunicipio;
    }

    public void setCodmunicipio(String codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Double getCostoenvio() {
        return costoenvio;
    }

    public void setCostoenvio(Double costoenvio) {
        this.costoenvio = costoenvio;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmunicipio != null ? codmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.codmunicipio == null && other.codmunicipio != null) || (this.codmunicipio != null && !this.codmunicipio.equals(other.codmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Municipio[ codmunicipio=" + codmunicipio + " ]";
    }
    
}
