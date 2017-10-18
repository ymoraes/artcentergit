/*
 * To change this license header, chomateriaise License Headers in Project Properties.
 * To change this template file, chomateriaise Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author YMoraes
 */
public class OrdemServico {
    
    private Integer id;
    private String data;
    private String projeto;
    private String tecnico;
    private String comentario;
    private Cliente cliente;
    private Equipamento equipamento;
    private List<OrdemServico_Material> materiais;
    

    public OrdemServico(Integer id) {
        this.id = id;
        materiais = new ArrayList<OrdemServico_Material>();
    }
    
    public OrdemServico() {
        materiais = new ArrayList<OrdemServico_Material>();
    }
    
        
    public List<OrdemServico_Material> getMaterial(){
        return Collections.unmodifiableList(materiais);
    }
    public void setMaterial(OrdemServico_Material material){
        this.materiais.add(material);
    }
    public void removeMaterial(OrdemServico_Material material){
        if(this.materiais.contains(material))
            this.materiais.remove(material);
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
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
        final OrdemServico other = (OrdemServico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+id ;
    }
    
    
    
    
}
