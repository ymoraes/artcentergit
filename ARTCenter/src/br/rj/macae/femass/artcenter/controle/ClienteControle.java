/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.controle;


import br.rj.macae.femass.artcenter.dao.ClienteDAO;
import br.rj.macae.femass.artcenter.modelo.Cliente;
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
public class ClienteControle{
    public void gravar(Cliente c)throws SQLException{
        ClienteDAO dao = new ClienteDAO();
        try{
        if(c.getId()==null || c.getId()<=0)
            dao.adicionar(c);
        else
            dao.alterar(c);
        }catch(SQLException ex){
            throw new SQLException("Erro ao salvar as informações: \n"+ex.getMessage());
        }
    }
    public void excluir(Cliente c)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta cliente?")==JOptionPane.YES_OPTION){
            ClienteDAO dao = new ClienteDAO();    
            dao.excluir(c);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta cliente?")==JOptionPane.YES_OPTION){
            ClienteDAO dao = new ClienteDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        TableColumnModel modeloDaColuna = tabela.getColumnModel();
        modeloDaColuna.getColumn(0).setMaxWidth(25);

        //limpa as linhas da tabela.
        model.setNumRows(0);
        ClienteDAO dao = new ClienteDAO();
        List clientes = dao.listarTodos();

        //Adiciona as linhas
        for (Object o : clientes){
            Cliente c = (Cliente) o;
            
            model.addRow(new Object[]{c.getId(),c.getNome()});
            
        }
        
        
    }

    public Cliente getClientePorId(int id) throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        Cliente c = (Cliente)dao.listarPorId(id);
        return c;       
    }
}
