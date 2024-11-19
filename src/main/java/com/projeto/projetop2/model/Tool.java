package com.projeto.projetop2.model;

import java.math.BigDecimal;
import java.util.Map;

public class Tool {

    public static Jogos converterJogos(Map<String, Object> registro) {
        return new Jogos((Integer) registro.get("id"), 
                          (String) registro.get("nome"), 
                          (String) registro.get("estado"), 
                          (BigDecimal) registro.get("preco"), 
                          (String) registro.get("console"));
                          //(String) registro.get("imagem"));
    }
}
