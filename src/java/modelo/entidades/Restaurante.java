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
import javax.persistence.Lob;
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
@Table(name = "restaurante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurante.findAll", query = "SELECT r FROM Restaurante r")
    , @NamedQuery(name = "Restaurante.findByCodrestaurante", query = "SELECT r FROM Restaurante r WHERE r.codrestaurante = :codrestaurante")
    , @NamedQuery(name = "Restaurante.findByNombreRestaurante", query = "SELECT r FROM Restaurante r WHERE r.nombreRestaurante = :nombreRestaurante")
    , @NamedQuery(name = "Restaurante.findByHorariosAtencion", query = "SELECT r FROM Restaurante r WHERE r.horariosAtencion = :horariosAtencion")})
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codrestaurante")
    private String codrestaurante;
    @Size(max = 25)
    @Column(name = "nombre_restaurante")
    private String nombreRestaurante;
    @Size(max = 20)
    @Column(name = "horarios_atencion")
    private String horariosAtencion;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @OneToMany(mappedBy = "codrestaurante")
    private List<Comidas> comidasList;

    public Restaurante() {
    }

    public Restaurante(String codrestaurante) {
        this.codrestaurante = codrestaurante;
    }

    public String getCodrestaurante() {
        return codrestaurante;
    }

    public void setCodrestaurante(String codrestaurante) {
        this.codrestaurante = codrestaurante;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getHorariosAtencion() {
        return horariosAtencion;
    }

    public void setHorariosAtencion(String horariosAtencion) {
        this.horariosAtencion = horariosAtencion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
        hash += (codrestaurante != null ? codrestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurante)) {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if ((this.codrestaurante == null && other.codrestaurante != null) || (this.codrestaurante != null && !this.codrestaurante.equals(other.codrestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Restaurante[ codrestaurante=" + codrestaurante + " ]";
    }
    
}
