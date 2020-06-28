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
@Table(name = "tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t")
    , @NamedQuery(name = "Tarjeta.findByCodtarjeta", query = "SELECT t FROM Tarjeta t WHERE t.codtarjeta = :codtarjeta")
    , @NamedQuery(name = "Tarjeta.findByNumero", query = "SELECT t FROM Tarjeta t WHERE t.numero = :numero")
    , @NamedQuery(name = "Tarjeta.findByPin", query = "SELECT t FROM Tarjeta t WHERE t.pin = :pin")
    , @NamedQuery(name = "Tarjeta.findByFechaValida", query = "SELECT t FROM Tarjeta t WHERE t.fechaValida = :fechaValida")})
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codtarjeta")
    private String codtarjeta;
    @Size(max = 29)
    @Column(name = "numero")
    private String numero;
    @Size(max = 3)
    @Column(name = "pin")
    private String pin;
    @Size(max = 5)
    @Column(name = "fecha_valida")
    private String fechaValida;
    @OneToMany(mappedBy = "codtarjeta")
    private List<Pago> pagoList;

    public Tarjeta() {
    }

    public Tarjeta(String codtarjeta) {
        this.codtarjeta = codtarjeta;
    }

    public String getCodtarjeta() {
        return codtarjeta;
    }

    public void setCodtarjeta(String codtarjeta) {
        this.codtarjeta = codtarjeta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFechaValida() {
        return fechaValida;
    }

    public void setFechaValida(String fechaValida) {
        this.fechaValida = fechaValida;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtarjeta != null ? codtarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.codtarjeta == null && other.codtarjeta != null) || (this.codtarjeta != null && !this.codtarjeta.equals(other.codtarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Tarjeta[ codtarjeta=" + codtarjeta + " ]";
    }
    
}
