package com.projeto.projetop2.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogosService {

    @Autowired
    JogosDAO jdao;

    public void inserirJogo(Jogos jogo) {
        jdao.inserirJogo(jogo);
    }

    public List<Map<String,Object>> listarJogos() {
        return jdao.listarJogos();
    }

    public void editarJogo(int id, Jogos jogo) {
        jdao.editarJogo(id, jogo);
    }

    public Jogos obterJogo(int id) {
        return jdao.obterJogo(id);
    }

    public void deletarJogo(int id) {
        jdao.deletarJogo(id);
    }
}
