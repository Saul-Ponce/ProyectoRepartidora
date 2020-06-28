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
@Table(name = "categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c")
    , @NamedQuery(name = "Categorias.findByCodcategoria", query = "SELECT c FROM Categorias c WHERE c.codcategoria = :codcategoria")
    , @NamedQuery(name = "Categorias.findByNombreCategoria", query = "SELECT c FROM Categorias c WHERE c.nombreCategoria = :nombreCategoria")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcategoria")
    private String codcategoria;
    @Size(max = 25)
    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    @OneToMany(mappedBy = "codcategoria")
    private List<Comidas> comidasList;

    public Categorias() {
    }

    public Categorias(String codcategoria) {
        this.codcategoria = codcategoria;
    }

    public String getCodcategoria() {
        return codcategoria;
    }

    public void setCodcategoria(String codcategoria) {
        this.codcategoria = codcategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @XmlTransient
    public List<Comidas> getComidasList() {
        return comidasList;
    }

    public void setComidasList(List<Comidas> comidasList) {
        this.comidasList = comidasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcategoria != null ? codcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.codcategoria == null && other.codcategoria != null) || (this.codcategoria != null && !this.codcategoria.equals(other.codcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Categorias[ codcategoria=" + codcategoria + " ]";
    }
    
}
