/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rj.macae.femass.artcenter.dao;


import br.rj.macae.femass.artcenter.modelo.Equipamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anamm
 */
public class EquipamentoDAO implements IDAO{

    @Override
    public void adicionar(Object o) throws SQLException {
        Equipamento equipamento = (Equipamento) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "INSERT INTO public.equipamento(\n" +
"	part, descricao, tipo, serie)\n" +
"	VALUES (?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, equipamento.getPart());
            stmt.setString(2, equipamento.getDescricao());
            stmt.setString(3, equipamento.getTipo());
            stmt.setString(4, equipamento.getSerie());

            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar a equipamento. \n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Equipamento equipamento = (Equipamento) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "UPDATE public.equipamento\n" +
"	SET part=?, descricao=?, tipo=?, serie=?\n" +
"	WHERE id = ?";
           /* UPDATE public.equipamento
	SET id=?, nome=?, descricao
	WHERE <condition>;
        */
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, equipamento.getPart());
            stmt.setString(2, equipamento.getDescricao());
            stmt.setString(3, equipamento.getTipo());
            stmt.setString(4, equipamento.getSerie());
            stmt.setInt(5, equipamento.getId());
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar alterar a equipamento. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Equipamento equipamento = (Equipamento) o;
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.equipamento\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, equipamento.getId());
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar remover a equipamento. \n" + e.getMessage());
        }
    }
    
    @Override
    public void excluir(int id) throws SQLException {
        
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();

            String sql = "DELETE FROM public.equipamento\n" +
"	WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar remover a equipamento. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException{
        List lista = new ArrayList();
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, part, descricao, tipo, serie\n" +
"	FROM public.equipamento ORDER BY id ASC;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento c = new Equipamento("");
                c.setPart(rs.getString("part"));  
                c.setDescricao(rs.getString("descricao"));
                c.setTipo(rs.getString("tipo"));
                c.setSerie(rs.getString("serie"));
                c.setId(rs.getInt("id"));
                
                lista.add(c);
            }

            rs.close();
            stmt.close();
            
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a lista de equipamentos. \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = FabricaConexao.getConexao();
            String sql = "SELECT id, part, descricao, tipo, serie\n" +
"	FROM public.equipamento WHERE id=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            Equipamento c = new Equipamento("");
             c.setPart(rs.getString("part"));  
                c.setDescricao(rs.getString("descricao"));
                c.setTipo(rs.getString("tipo"));
                c.setSerie(rs.getString("serie"));
            c.setId(rs.getInt("id"));
            

            rs.close();
            stmt.close();
            
            return c;
        } catch (SQLException e) {
            throw new SQLException("Erro ao recuperar a equipamento. \n" + e.getMessage());
        }
    }
    
}
