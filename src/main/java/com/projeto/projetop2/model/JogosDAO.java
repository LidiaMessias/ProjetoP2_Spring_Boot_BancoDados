package com.projeto.projetop2.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class JogosDAO {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirJogo(Jogos jogo) {
        String sql = "INSERT INTO jogos(nome, estado, preco, console) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, jogo.getNome(), jogo.getEstado(), jogo.getPreco(), jogo.getConsole());
    }

    public List<Map<String,Object>> listarJogos() {
        String sql = "SELECT * FROM jogos;";
        return jdbc.queryForList(sql);
    }

    public void editarJogo(int id, Jogos jogo) {
        String sql = "UPDATE jogos SET ";
        sql += "nome = ?, estado = ?, preco = ?, console = ? WHERE id = ?";
        jdbc.update(sql, jogo.getNome(), jogo.getEstado(), jogo.getPreco(), jogo.getConsole(), id);
    }

    public Jogos obterJogo(int id) {
        String sql = "SELECT * FROM jogos WHERE id = ?";
        return Tool.converterJogos(jdbc.queryForMap(sql, id));
    }

    public void deletarJogo(int id) {
        String sql = "DELETE FROM jogos WHERE id = ?";
        jdbc.update(sql, id);
    }
}
