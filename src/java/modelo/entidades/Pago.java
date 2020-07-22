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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findByCodpago", query = "SELECT p FROM Pago p WHERE p.codpago = :codpago")
    , @NamedQuery(name = "Pago.findByMonto", query = "SELECT p FROM Pago p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pago.findByTipoPago", query = "SELECT p FROM Pago p WHERE p.tipoPago = :tipoPago")
    , @NamedQuery(name = "Pago.findByMontoviaje", query = "SELECT p FROM Pago p WHERE p.montoviaje = :montoviaje")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codpago")
    private String codpago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Size(max = 10)
    @Column(name = "tipo_pago")
    private String tipoPago;
    @Column(name = "montoviaje")
    private Double montoviaje;
    @JoinColumn(name = "codpedido", referencedColumnName = "codpedidos")
    @ManyToOne
    private Pedidos codpedido;

    public Pago() {
    }

    public Pago(String codpago) {
        this.codpago = codpago;
    }

    public String getCodpago() {
        return codpago;
    }

    public void setCodpago(String codpago) {
        this.codpago = codpago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Double getMontoviaje() {
        return montoviaje;
    }

    public void setMontoviaje(Double montoviaje) {
        this.montoviaje = montoviaje;
    }

    public Pedidos getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(Pedidos codpedido) {
        this.codpedido = codpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpago != null ? codpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.codpago == null && other.codpago != null) || (this.codpago != null && !this.codpago.equals(other.codpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Pago[ codpago=" + codpago + " ]";
    }
    
}
