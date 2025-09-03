package models.dao;

import models.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO implements DAO<Jogador> {
    @Override
    public void inserir(Jogador jogador) throws SQLException {
        try (Connection conn = DatabaseConnection.getConn(); PreparedStatement ps = conn.prepareStatement("INSERT INTO jogadores (nome, numCamisa) VALUES (?, ?)")) {
            ps.setString(1, jogador.getNome());

            ps.setInt(2, jogador.getNumCamisa());

            ps.executeUpdate();
        }
    }

    @Override
    public void excluir(Jogador jogador) throws SQLException {
        try (Connection conn = DatabaseConnection.getConn(); PreparedStatement ps = conn.prepareStatement("DELETE FROM jogadores WHERE id = ?");) {
            ps.setInt(1, jogador.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void atualizar(Jogador jogador) throws SQLException {
        try (Connection conn = DatabaseConnection.getConn(); PreparedStatement ps = conn.prepareStatement("UPDATE jogadores SET nome = ?, numeroCamisa = ? WHERE id = ?")) {
            ps.setString(1, jogador.getNome());
            ps.setInt(2, jogador.getNumCamisa());
            ps.setInt(3, jogador.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public List<Jogador> obterTudo() throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConn(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM jogadores")) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    jogadores.add(new Jogador(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
        }
        return jogadores;
    }

    @Override
    public Jogador obter(Jogador jogador) throws SQLException {
        return null;
    }
}
