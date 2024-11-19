package com.projeto.projetop2.controller;

/*import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths; */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
/*import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; */

import com.projeto.projetop2.model.Jogos;
import com.projeto.projetop2.model.JogosService;
import com.projeto.projetop2.model.Tool;

@Controller
public class CadastroJogosController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastroJogos")
    public String cadastroJogos(Model model) {
        Jogos jogo = new Jogos();
        model.addAttribute("jogos", jogo);
        return "cadastroJogos";
    }  
    
    @PostMapping("/cadastroJogos")
    public String cadastrarJogo(@ModelAttribute Jogos jogo) {
        JogosService js = context.getBean(JogosService.class);
        js.inserirJogo(jogo);
        return "redirect:/listaJogos";
    }  

    /*@PostMapping("/cadastroJogos")
    public String sucesso(@ModelAttribute Jogos jogo, 
                          @RequestParam("imagem") MultipartFile imagem) throws IOException {
        
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();
            Path caminhoArquivo = Paths.get("src/main/resources/static/uploadimages/", nomeArquivo);

            // Salvar o arquivo no diretório especificado
            Files.createDirectories(caminhoArquivo.getParent()); // Garante que a pasta exista
            imagem.transferTo(caminhoArquivo.toFile());

            jogo.setImagem("/uploadimages/" + nomeArquivo);
        }
        
        JogosService js = context.getBean(JogosService.class);
        js.inserirJogo(jogo);
        return "sucesso";
    } */

    @GetMapping("/listaJogos")
    public String listaJogos(Model model) {
        JogosService js = context.getBean(JogosService.class);
        List<Map<String,Object>> lista = js.listarJogos();
        List<Jogos> listaJogo = new ArrayList<Jogos>();
        for(Map<String,Object> registro : lista) {
            listaJogo.add(Tool.converterJogos(registro));
        }
        model.addAttribute("jogos", listaJogo);
        return "listaJogos";
    }

    @GetMapping("/editarJogo/{id}")
    public String editarJogo(Model model, @PathVariable int id) {
        JogosService js = context.getBean(JogosService.class);
        Jogos jogo = js.obterJogo(id);
        model.addAttribute("id", id);
        model.addAttribute("jogos", jogo);
        return "editarJogo";
    }  

    @PostMapping("/editarJogo/{id}")
    public String editarJogo(@PathVariable int id, @ModelAttribute Jogos jogo){
        JogosService js = context.getBean(JogosService.class);
        js.editarJogo(id, jogo);
        return "redirect:/listaJogos";
    }

    @PostMapping("/deletarJogo/{id}")
    public String deletarJogo(@PathVariable int id) {
        JogosService js = context.getBean(JogosService.class);
        js.deletarJogo(id);
        return "redirect:/listaJogos";
    }

}
