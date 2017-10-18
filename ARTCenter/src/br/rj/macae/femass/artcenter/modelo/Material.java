/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.modelo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author YMoraes
 */
public class Material {
    
    private Integer id;
    private String nome;
    private String material;
    private String modelo;
    private List<OrdemServico_Material> os;
    

    public List<OrdemServico_Material> getOS(){
        return Collections.unmodifiableList(os);
    }
    public void setOS(OrdemServico_Material ordem){
        this.os.add(ordem);
    }
    public void removeOS(OrdemServico_Material ordem){
        if(this.os.contains(ordem))
            this.os.remove(ordem);
    }
    
   

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
