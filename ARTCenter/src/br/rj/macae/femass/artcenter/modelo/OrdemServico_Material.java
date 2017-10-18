/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.modelo;

import br.rj.macae.femass.artcenter.dao.MaterialDAO;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.swing.JList;

/**
 *
 * @author YMoraes
 */
public class OrdemServico_Material {
    
    private OrdemServico os;
    private Material material;
    private float quantidade;
    private String unidade;

    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico_Material other = (OrdemServico_Material) obj;
        if (!Objects.equals(this.os, other.os)) {
            return false;
        }
        if (!Objects.equals(this.material, other.material)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return material+" | "+quantidade+" "+unidade.toUpperCase() ;
    }
    
    
     
}
