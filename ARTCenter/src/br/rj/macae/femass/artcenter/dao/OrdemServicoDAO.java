/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.dao;


import br.rj.macae.femass.artcenter.modelo.Cliente;
import br.rj.macae.femass.artcenter.modelo.Equipamento;
import br.rj.macae.femass.artcenter.modelo.Material;
import br.rj.macae.femass.artcenter.modelo.OrdemServico;
import br.rj.macae.femass.artcenter.modelo.OrdemServico_Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ymoraes
 */
public class OrdemServicoDAO implements IDAO{

    @Override
    public void adicionar(Object o) throws SQLException {
        OrdemServico ordemservico = (OrdemServico) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "INSERT INTO public.ordemservico(\n" +
"	data, projeto, tecnico, comentario, cliente, equipamento)\n" +
"	VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, ordemservico.getData());
            stmt.setString(2, ordemservico.getProjeto());
            stmt.setString(3, ordemservico.getTecnico());
            stmt.setString(4, ordemservico.getComentario());
            stmt.setInt(5, ordemservico.getCliente().getId());
            stmt.setInt(6, ordemservico.getEquipamento().getId());
            

            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar a ordemservico. \n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        OrdemServico ordemservico = (OrdemServico) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "UPDATE public.ordemservico\n" +
"	SET data=?, projeto=?, tecnico=?,comentario=?, cliente=?, equipamento=? \n" +
"	WHERE id = ?";
        
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, ordemservico.getData());
            stmt.setString(2, ordemservico.getProjeto());
            stmt.setString(3, ordemservico.getTecnico());
            stmt.setString(4, ordemservico.getComentario());
            stmt.setInt(5, ordemservico.getCliente().getId());
            stmt.setInt(6, ordemservico.getEquipamento().getId());
            stmt.setInt(7, ordemservico.getId());
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar alterar a ordemservico. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        OrdemServico ordemservico = (OrdemServico) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.ordemservico\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setLong(1, ordemservico.getId());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar remover a ordemservico. \n" + e.getMessage());
        }
    }
    
    @Override
    public void excluir(int id) throws SQLException {
        
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.ordemservico\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar remover a ordemservico. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException{
        List lista = new ArrayList();
        Connection conn = null;
        ClienteDAO cdao = new ClienteDAO();
        EquipamentoDAO edao = new EquipamentoDAO();
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, data, projeto, tecnico, comentario, cliente, equipamento\n" +
"	FROM public.ordemservico ORDER BY id ASC;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdemServico c = new OrdemServico();
                c.setData(rs.getString("data"));  
                c.setProjeto(rs.getString("projeto"));
                c.setTecnico(rs.getString("tecnico"));
                c.setComentario(rs.getString("comentario"));
                c.setCliente((Cliente)cdao.listarPorId(rs.getInt("cliente")));
                c.setEquipamento((Equipamento)edao.listarPorId(rs.getInt("equipamento")));
                
                c.setId(rs.getInt("id"));
                recuperarMateriais(c);
                lista.add(c);
            }

            rs.close();
            stmt.close();
            
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a lista de ordemservicos. \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        Connection conn = null;
        ClienteDAO cdao = new ClienteDAO();
        EquipamentoDAO edao = new EquipamentoDAO();
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, data, projeto, tecnico, comentario, cliente, equipamento\n" +
"	FROM public.ordemservico WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            OrdemServico c = new OrdemServico();
            c.setData(rs.getString("data"));  
                c.setProjeto(rs.getString("projeto"));
                c.setTecnico(rs.getString("tecnico"));
                c.setComentario(rs.getString("comentario"));
                c.setCliente((Cliente)cdao.listarPorId(rs.getInt("cliente")));
                c.setEquipamento((Equipamento)edao.listarPorId(rs.getInt("equipamento")));
            c.setId(rs.getInt("id"));
            

            rs.close();
            stmt.close();
            
            return c;
        } catch (SQLException e) {
            throw new SQLException("Erro ao recuperar a ordemservico. \n" + e.getMessage());
        }
    }
    
    private void recuperarMateriais(OrdemServico r) throws SQLException{
        Connection conn = null;
        MaterialDAO dao = new MaterialDAO();
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT os, material, quantidade, unidade\n" +
"	FROM public.ordemservico_material WHERE os=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, r.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){            
                OrdemServico_Material material = new OrdemServico_Material();
                material.setOs(r);
                material.setQuantidade(rs.getFloat("quantidade"));
                material.setUnidade(rs.getString("unidade"));
                material.setMaterial((Material)dao.listarPorId(rs.getInt("material")));
                r.setMaterial(material);
            }

            rs.close();
            stmt.close();
            
            
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar material da receita. \n" + e.getMessage());
        }
    }
}
