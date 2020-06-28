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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByCodfactura", query = "SELECT f FROM Factura f WHERE f.codfactura = :codfactura")
    , @NamedQuery(name = "Factura.findByPagoextra", query = "SELECT f FROM Factura f WHERE f.pagoextra = :pagoextra")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codfactura")
    private String codfactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pagoextra")
    private Double pagoextra;
    @JoinColumn(name = "codviaje", referencedColumnName = "codviaje")
    @ManyToOne
    private Viaje codviaje;

    public Factura() {
    }

    public Factura(String codfactura) {
        this.codfactura = codfactura;
    }

    public String getCodfactura() {
        return codfactura;
    }

    public void setCodfactura(String codfactura) {
        this.codfactura = codfactura;
    }

    public Double getPagoextra() {
        return pagoextra;
    }

    public void setPagoextra(Double pagoextra) {
        this.pagoextra = pagoextra;
    }

    public Viaje getCodviaje() {
        return codviaje;
    }

    public void setCodviaje(Viaje codviaje) {
        this.codviaje = codviaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfactura != null ? codfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.codfactura == null && other.codfactura != null) || (this.codfactura != null && !this.codfactura.equals(other.codfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Factura[ codfactura=" + codfactura + " ]";
    }
    
}
