/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saul Ponce
 */
@Entity
@Table(name = "transporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporte.findAll", query = "SELECT t FROM Transporte t")
    , @NamedQuery(name = "Transporte.findByCodtransporte", query = "SELECT t FROM Transporte t WHERE t.codtransporte = :codtransporte")
    , @NamedQuery(name = "Transporte.findByPlaca", query = "SELECT t FROM Transporte t WHERE t.placa = :placa")
    , @NamedQuery(name = "Transporte.findByModelo", query = "SELECT t FROM Transporte t WHERE t.modelo = :modelo")
    , @NamedQuery(name = "Transporte.findByTipo", query = "SELECT t FROM Transporte t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Transporte.findByEstado", query = "SELECT t FROM Transporte t WHERE t.estado = :estado")})
public class Transporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtransporte")
    private String codtransporte;
    @Size(max = 7)
    @Column(name = "placa")
    private String placa;
    @Size(max = 15)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 15)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "codrepartidor", referencedColumnName = "codempleado")
    @ManyToOne
    private Empleado codrepartidor;

    public Transporte() {
    }

    public Transporte(String codtransporte) {
        this.codtransporte = codtransporte;
    }

    public String getCodtransporte() {
        return codtransporte;
    }

    public void setCodtransporte(String codtransporte) {
        this.codtransporte = codtransporte;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getCodrepartidor() {
        return codrepartidor;
    }

    public void setCodrepartidor(Empleado codrepartidor) {
        this.codrepartidor = codrepartidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtransporte != null ? codtransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporte)) {
            return false;
        }
        Transporte other = (Transporte) object;
        if ((this.codtransporte == null && other.codtransporte != null) || (this.codtransporte != null && !this.codtransporte.equals(other.codtransporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Transporte[ codtransporte=" + codtransporte + " ]";
    }
    
}
