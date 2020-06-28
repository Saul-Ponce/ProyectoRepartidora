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
@Table(name = "comidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comidas.findAll", query = "SELECT c FROM Comidas c")
    , @NamedQuery(name = "Comidas.findByCodcomida", query = "SELECT c FROM Comidas c WHERE c.codcomida = :codcomida")
    , @NamedQuery(name = "Comidas.findByNombre", query = "SELECT c FROM Comidas c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Comidas.findByDescripcion", query = "SELECT c FROM Comidas c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Comidas.findByPrecio", query = "SELECT c FROM Comidas c WHERE c.precio = :precio")})
public class Comidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcomida")
    private String codcomida;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @JoinColumn(name = "codcategoria", referencedColumnName = "codcategoria")
    @ManyToOne
    private Categorias codcategoria;
    @JoinColumn(name = "codrestaurante", referencedColumnName = "codrestaurante")
    @ManyToOne
    private Restaurante codrestaurante;
    @OneToMany(mappedBy = "codcomida")
    private List<Detallepedido> detallepedidoList;

    public Comidas() {
    }

    public Comidas(String codcomida) {
        this.codcomida = codcomida;
    }

    public String getCodcomida() {
        return codcomida;
    }

    public void setCodcomida(String codcomida) {
        this.codcomida = codcomida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Categorias getCodcategoria() {
        return codcategoria;
    }

    public void setCodcategoria(Categorias codcategoria) {
        this.codcategoria = codcategoria;
    }

    public Restaurante getCodrestaurante() {
        return codrestaurante;
    }

    public void setCodrestaurante(Restaurante codrestaurante) {
        this.codrestaurante = codrestaurante;
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
        hash += (codcomida != null ? codcomida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comidas)) {
            return false;
        }
        Comidas other = (Comidas) object;
        if ((this.codcomida == null && other.codcomida != null) || (this.codcomida != null && !this.codcomida.equals(other.codcomida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Comidas[ codcomida=" + codcomida + " ]";
    }
    
}
