package internetbanking.demo.controller;


import internetbanking.demo.model.TransacaoRepository;
import internetbanking.demo.model.Transacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Component
@RequestMapping("/transacoes")
public class TransacaoController {
    @Autowired
    private  TransacaoRepository transacaoRepository;

    public TransacaoController(){

    }

    @PostMapping
    public Transacoes salvarTransacao(@RequestBody Transacoes transacoes) {
        Transacoes transacao = transacaoRepository.save(transacoes);
        return transacao;
    }


    @GetMapping()
    public List<Transacoes> listarData(@RequestParam("id_cliente") long id_cliente,
                                       @RequestParam("data_inicio")@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") Date data_inicio,
                                       @RequestParam("data_fim") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") Date data_fim) {
         return  transacaoRepository.findBydata_inicioBetween(data_inicio, data_fim, id_cliente);

    }


}