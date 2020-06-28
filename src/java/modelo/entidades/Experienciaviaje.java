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
@Table(name = "experienciaviaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experienciaviaje.findAll", query = "SELECT e FROM Experienciaviaje e")
    , @NamedQuery(name = "Experienciaviaje.findByCodexperiencia", query = "SELECT e FROM Experienciaviaje e WHERE e.codexperiencia = :codexperiencia")
    , @NamedQuery(name = "Experienciaviaje.findByTiempoEntrega", query = "SELECT e FROM Experienciaviaje e WHERE e.tiempoEntrega = :tiempoEntrega")
    , @NamedQuery(name = "Experienciaviaje.findByExperiencia", query = "SELECT e FROM Experienciaviaje e WHERE e.experiencia = :experiencia")})
public class Experienciaviaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "codexperiencia")
    private String codexperiencia;
    @Size(max = 7)
    @Column(name = "tiempo_entrega")
    private String tiempoEntrega;
    @Size(max = 150)
    @Column(name = "experiencia")
    private String experiencia;
    @JoinColumn(name = "codviaje", referencedColumnName = "codviaje")
    @ManyToOne
    private Viaje codviaje;

    public Experienciaviaje() {
    }

    public Experienciaviaje(String codexperiencia) {
        this.codexperiencia = codexperiencia;
    }

    public String getCodexperiencia() {
        return codexperiencia;
    }

    public void setCodexperiencia(String codexperiencia) {
        this.codexperiencia = codexperiencia;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public Viaje getCodviaje() {
        return codviaje;
    }

    public void setCodviaje(Viaje codviaje) {
        this.codviaje = codviaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codexperiencia != null ? codexperiencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experienciaviaje)) {
            return false;
        }
        Experienciaviaje other = (Experienciaviaje) object;
        if ((this.codexperiencia == null && other.codexperiencia != null) || (this.codexperiencia != null && !this.codexperiencia.equals(other.codexperiencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Experienciaviaje[ codexperiencia=" + codexperiencia + " ]";
    }
    
}
