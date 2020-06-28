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
@Table(name = "detallepedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d")
    , @NamedQuery(name = "Detallepedido.findByCoddetalle", query = "SELECT d FROM Detallepedido d WHERE d.coddetalle = :coddetalle")
    , @NamedQuery(name = "Detallepedido.findByCantidad", query = "SELECT d FROM Detallepedido d WHERE d.cantidad = :cantidad")})
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "coddetalle")
    private String coddetalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "codcomida", referencedColumnName = "codcomida")
    @ManyToOne
    private Comidas codcomida;
    @JoinColumn(name = "codpedido", referencedColumnName = "codpedidos")
    @ManyToOne
    private Pedidos codpedido;

    public Detallepedido() {
    }

    public Detallepedido(String coddetalle) {
        this.coddetalle = coddetalle;
    }

    public String getCoddetalle() {
        return coddetalle;
    }

    public void setCoddetalle(String coddetalle) {
        this.coddetalle = coddetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Comidas getCodcomida() {
        return codcomida;
    }

    public void setCodcomida(Comidas codcomida) {
        this.codcomida = codcomida;
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
        hash += (coddetalle != null ? coddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.coddetalle == null && other.coddetalle != null) || (this.coddetalle != null && !this.coddetalle.equals(other.coddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Detallepedido[ coddetalle=" + coddetalle + " ]";
    }
    
}
