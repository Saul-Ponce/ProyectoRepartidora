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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "viaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viaje.findAll", query = "SELECT v FROM Viaje v")
    , @NamedQuery(name = "Viaje.findByCodviaje", query = "SELECT v FROM Viaje v WHERE v.codviaje = :codviaje")})
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codviaje")
    private String codviaje;
    @OneToMany(mappedBy = "codviaje")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "codviaje")
    private List<Experienciaviaje> experienciaviajeList;
    @JoinColumn(name = "codempleado", referencedColumnName = "codempleado")
    @ManyToOne
    private Empleado codempleado;
    @JoinColumn(name = "codpago", referencedColumnName = "codpago")
    @ManyToOne
    private Pago codpago;
    @JoinColumn(name = "codpedido", referencedColumnName = "codpedidos")
    @ManyToOne
    private Pedidos codpedido;

    public Viaje() {
    }

    public Viaje(String codviaje) {
        this.codviaje = codviaje;
    }

    public String getCodviaje() {
        return codviaje;
    }

    public void setCodviaje(String codviaje) {
        this.codviaje = codviaje;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Experienciaviaje> getExperienciaviajeList() {
        return experienciaviajeList;
    }

    public void setExperienciaviajeList(List<Experienciaviaje> experienciaviajeList) {
        this.experienciaviajeList = experienciaviajeList;
    }

    public Empleado getCodempleado() {
        return codempleado;
    }

    public void setCodempleado(Empleado codempleado) {
        this.codempleado = codempleado;
    }

    public Pago getCodpago() {
        return codpago;
    }

    public void setCodpago(Pago codpago) {
        this.codpago = codpago;
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
        hash += (codviaje != null ? codviaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaje)) {
            return false;
        }
        Viaje other = (Viaje) object;
        if ((this.codviaje == null && other.codviaje != null) || (this.codviaje != null && !this.codviaje.equals(other.codviaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Viaje[ codviaje=" + codviaje + " ]";
    }
    
}
