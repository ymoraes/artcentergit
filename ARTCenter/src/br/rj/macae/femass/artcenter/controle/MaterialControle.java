/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.controle;


import br.rj.macae.femass.artcenter.dao.MaterialDAO;
import br.rj.macae.femass.artcenter.modelo.Material;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author anamm
 */
public class MaterialControle{
    public void gravar(Material c)throws SQLException{
        MaterialDAO dao = new MaterialDAO();
        try{
        if(c.getId()==null || c.getId()<=0)
            dao.adicionar(c);
        else
            dao.alterar(c);
        }catch(SQLException ex){
            throw new SQLException("Erro ao salvar as informações: \n"+ex.getMessage());
        }
    }
    public void excluir(Material c)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta material?")==JOptionPane.YES_OPTION){
            MaterialDAO dao = new MaterialDAO();    
            dao.excluir(c);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta material?")==JOptionPane.YES_OPTION){
            MaterialDAO dao = new MaterialDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        TableColumnModel modeloDaColuna = tabela.getColumnModel();
        modeloDaColuna.getColumn(0).setMaxWidth(25);

        //limpa as linhas da tabela.
        model.setNumRows(0);
        
        MaterialDAO dao = new MaterialDAO();
        List materials = dao.listarTodos();

        //Adiciona as linhas
        for (Object o : materials){
            Material c = (Material) o;
            
            model.addRow(new Object[]{c.getId(),c.getNome(),c.getMaterial(),c.getModelo()});
            
        }
        
        
    }

    public Material getMaterialPorId(int id) throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        Material c = (Material)dao.listarPorId(id);
        return c;       
    }
}
