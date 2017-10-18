/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.controle;



import br.rj.macae.femass.artcenter.dao.EquipamentoDAO;
import br.rj.macae.femass.artcenter.modelo.Equipamento;



import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ymoraes
 */
public class EquipamentoControle{
    
 
    public void gravar(Equipamento c)throws SQLException{
        EquipamentoDAO dao = new EquipamentoDAO();
        try{
        if (c.getId()==null || c.getId()<=0)
            dao.adicionar(c);
        else
            dao.alterar(c);
        }catch(SQLException ex){
            throw new SQLException("Erro ao salvar as informações: \n"+ex.getMessage());
        }
    }
    
    
    public void excluir(Equipamento c)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta equipamento?")==JOptionPane.YES_OPTION){
            EquipamentoDAO dao = new EquipamentoDAO();    
            dao.excluir(c);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta equipamento?")==JOptionPane.YES_OPTION){
            EquipamentoDAO dao = new EquipamentoDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        TableColumnModel modeloDaColuna = tabela.getColumnModel();
        modeloDaColuna.getColumn(0).setMaxWidth(25);

        //limpa as linhas da tabela.
        model.setNumRows(0);
        
        EquipamentoDAO dao = new EquipamentoDAO();
        List equipamentos = dao.listarTodos();

        //Adiciona as linhas
        for (Object o : equipamentos){
            Equipamento c = (Equipamento) o;
            
            model.addRow(new Object[]{c.getId(),c.getPart(),c.getDescricao(),c.getTipo(),c.getSerie()});
            
        }
        
        
    }

    public Equipamento getEquipamentoPorId(int id) throws SQLException {
        EquipamentoDAO dao = new EquipamentoDAO();
        Equipamento c = (Equipamento)dao.listarPorId(id);
        return c;       
    }
}
