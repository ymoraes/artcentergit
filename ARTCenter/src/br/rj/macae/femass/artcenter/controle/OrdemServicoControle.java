/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.controle;


import br.rj.macae.femass.artcenter.dao.ClienteDAO;
import br.rj.macae.femass.artcenter.dao.EquipamentoDAO;
import br.rj.macae.femass.artcenter.dao.MaterialDAO;
import br.rj.macae.femass.artcenter.dao.OrdemServicoDAO;
import br.rj.macae.femass.artcenter.modelo.OrdemServico;
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
public class OrdemServicoControle{
    public void gravar(OrdemServico c)throws SQLException{
        OrdemServicoDAO dao = new OrdemServicoDAO();
        try{
        if(c.getId()==null || c.getId()<=0)
            dao.adicionar(c);
        else
            dao.alterar(c);
        }catch(SQLException ex){
            throw new SQLException("Erro ao salvar as informações: \n"+ex.getMessage());
        }
    }
    public void excluir(OrdemServico c)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta material?")==JOptionPane.YES_OPTION){
            OrdemServicoDAO dao = new OrdemServicoDAO();    
            dao.excluir(c);
        }
        
    }
    public void excluir(Long id)throws SQLException{
        if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta material?")==JOptionPane.YES_OPTION){
            OrdemServicoDAO dao = new OrdemServicoDAO();    
            dao.excluir(id);
        }
    }
    public void atualizarLista(JTable tabela) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        TableColumnModel modeloDaColuna = tabela.getColumnModel();
        modeloDaColuna.getColumn(0).setMaxWidth(25);
        modeloDaColuna.getColumn(4).setMaxWidth(150);

        //limpa as linhas da tabela.
        model.setNumRows(0);
        
        OrdemServicoDAO dao = new OrdemServicoDAO();
        List materials = dao.listarTodos();

        //Adiciona as linhas
        for (Object o : materials){
            OrdemServico c = (OrdemServico) o;
            
            model.addRow(new Object[]{c.getId(),c.getData(),c.getProjeto(),c.getTecnico(), c.getComentario(), c.getCliente(),c.getEquipamento()});
            
        }
        
        
    }

    public OrdemServico getOrdemServicoPorId(int id) throws SQLException {
        OrdemServicoDAO dao = new OrdemServicoDAO();
        OrdemServico c = (OrdemServico)dao.listarPorId(id);
        return c;       
    }
    
    public List listarClientes() throws SQLException {
        ClienteDAO dao = new ClienteDAO();
        return dao.listarTodos();
    }

    public List listarEquipamentos() throws SQLException {
        EquipamentoDAO dao = new EquipamentoDAO();
        return dao.listarTodos();
    }
    
    public List listarMateriais() throws SQLException {
        MaterialDAO dao = new MaterialDAO();
        return dao.listarTodos();
    }
    
}
