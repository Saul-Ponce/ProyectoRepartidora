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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByCodempleado", query = "SELECT e FROM Empleado e WHERE e.codempleado = :codempleado")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleado.findByDui", query = "SELECT e FROM Empleado e WHERE e.dui = :dui")
    , @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empleado.findByNit", query = "SELECT e FROM Empleado e WHERE e.nit = :nit")
    , @NamedQuery(name = "Empleado.findByIsss", query = "SELECT e FROM Empleado e WHERE e.isss = :isss")
    , @NamedQuery(name = "Empleado.findByTipoContrato", query = "SELECT e FROM Empleado e WHERE e.tipoContrato = :tipoContrato")
    , @NamedQuery(name = "Empleado.findByFechaContrato", query = "SELECT e FROM Empleado e WHERE e.fechaContrato = :fechaContrato")
    , @NamedQuery(name = "Empleado.findByEstado", query = "SELECT e FROM Empleado e WHERE e.estado = :estado")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codempleado")
    private String codempleado;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 10)
    @Column(name = "dui")
    private String dui;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "nit")
    private String nit;
    @Size(max = 10)
    @Column(name = "isss")
    private String isss;
    @Size(max = 10)
    @Column(name = "tipo_contrato")
    private String tipoContrato;
    @Column(name = "fecha_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "codrepartidor")
    private List<Transporte> transporteList;
    @JoinColumn(name = "codcargo", referencedColumnName = "codcargo")
    @ManyToOne
    private Cargo codcargo;
    @OneToMany(mappedBy = "codempleado")
    private List<Viaje> viajeList;
    @OneToMany(mappedBy = "codempleado")
    private List<Usuario> usuarioList;

    public Empleado() {
    }

    public Empleado(String codempleado) {
        this.codempleado = codempleado;
    }

    public String getCodempleado() {
        return codempleado;
    }

    public void setCodempleado(String codempleado) {
        this.codempleado = codempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIsss() {
        return isss;
    }

    public void setIsss(String isss) {
        this.isss = isss;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Transporte> getTransporteList() {
        return transporteList;
    }

    public void setTransporteList(List<Transporte> transporteList) {
        this.transporteList = transporteList;
    }

    public Cargo getCodcargo() {
        return codcargo;
    }

    public void setCodcargo(Cargo codcargo) {
        this.codcargo = codcargo;
    }

    @XmlTransient
    public List<Viaje> getViajeList() {
        return viajeList;
    }

    public void setViajeList(List<Viaje> viajeList) {
        this.viajeList = viajeList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codempleado != null ? codempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.codempleado == null && other.codempleado != null) || (this.codempleado != null && !this.codempleado.equals(other.codempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Empleado[ codempleado=" + codempleado + " ]";
    }
    
}
