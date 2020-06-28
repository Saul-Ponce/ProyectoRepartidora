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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodcargo", query = "SELECT c FROM Cargo c WHERE c.codcargo = :codcargo")
    , @NamedQuery(name = "Cargo.findByNombrecargo", query = "SELECT c FROM Cargo c WHERE c.nombrecargo = :nombrecargo")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codcargo")
    private String codcargo;
    @Size(max = 30)
    @Column(name = "nombrecargo")
    private String nombrecargo;
    @OneToMany(mappedBy = "codcargo")
    private List<Empleado> empleadoList;

    public Cargo() {
    }

    public Cargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public String getCodcargo() {
        return codcargo;
    }

    public void setCodcargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcargo != null ? codcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codcargo == null && other.codcargo != null) || (this.codcargo != null && !this.codcargo.equals(other.codcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Cargo[ codcargo=" + codcargo + " ]";
    }
    
}
