/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saul Ponce
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")
    , @NamedQuery(name = "Pedidos.findByCodpedidos", query = "SELECT p FROM Pedidos p WHERE p.codpedidos = :codpedidos")
    , @NamedQuery(name = "Pedidos.findByFechapedido", query = "SELECT p FROM Pedidos p WHERE p.fechapedido = :fechapedido")
    , @NamedQuery(name = "Pedidos.findByHorapedido", query = "SELECT p FROM Pedidos p WHERE p.horapedido = :horapedido")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codpedidos")
    private String codpedidos;
    @Column(name = "fechapedido")
    @Temporal(TemporalType.DATE)
    private Date fechapedido;
    @Size(max = 8)
    @Column(name = "horapedido")
    private String horapedido;
    @JoinColumn(name = "codcliente", referencedColumnName = "codclientes")
    @ManyToOne
    private Clientes codcliente;
    @OneToMany(mappedBy = "codpedido")
    private List<Pago> pagoList;
    @OneToMany(mappedBy = "codpedido")
    private List<Detallepedido> detallepedidoList;

    public Pedidos() {
    }

    public Pedidos(String codpedidos) {
        this.codpedidos = codpedidos;
    }

    public String getCodpedidos() {
        return codpedidos;
    }

    public void setCodpedidos(String codpedidos) {
        this.codpedidos = codpedidos;
    }

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public String getHorapedido() {
        return horapedido;
    }

    public void setHorapedido(String horapedido) {
        this.horapedido = horapedido;
    }

    public Clientes getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Clientes codcliente) {
        this.codcliente = codcliente;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @XmlTransient
    public List<Detallepedido> getDetallepedidoList() {
        return detallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> detallepedidoList) {
        this.detallepedidoList = detallepedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpedidos != null ? codpedidos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.codpedidos == null && other.codpedidos != null) || (this.codpedidos != null && !this.codpedidos.equals(other.codpedidos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Pedidos[ codpedidos=" + codpedidos + " ]";
    }
    
}
