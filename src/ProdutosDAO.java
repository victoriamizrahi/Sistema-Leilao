/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

   public boolean venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        int linhas = prep.executeUpdate();
        return linhas > 0;
    } catch (Exception e) {
        System.out.println("Erro ao vender: " + e.getMessage());
        return false;
    }
}

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        while (resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            vendidos.add(p);
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar vendidos: " + e.getMessage());
    }
    return vendidos;
}
}
