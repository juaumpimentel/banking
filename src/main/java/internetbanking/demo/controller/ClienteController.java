package internetbanking.demo.controller;



import internetbanking.demo.SaqueDTO.ClienteSaqueDTO;
import internetbanking.demo.SaqueDTO.SaqueResponseDTO;
import internetbanking.demo.model.Cliente;
import internetbanking.demo.model.ClienteRepository;
import internetbanking.demo.model.Transacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;


@RestController
@Component
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private  TransacaoController transacaoController;


    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController(){

    }


    @PostMapping("/cadastrar")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @GetMapping
    public Page<Cliente> listar(@RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "1") int page) {
        return clienteRepository.findAll(PageRequest.of(page,size));
    }

    @PutMapping("/sacar")
    public ResponseEntity<SaqueResponseDTO> sacar(@RequestBody ClienteSaqueDTO clienteSaqueDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteSaqueDTO.getId());
        if (!cliente.isPresent()) {
            SaqueResponseDTO response = new SaqueResponseDTO();
            response.setError("Cliente não encontrado");
            return status(HttpStatus.NOT_FOUND).body(response);
        }
        Cliente clientenovo = cliente.get();
        var soma = clientenovo.getBalance().subtract(clienteSaqueDTO.getBalance());
        if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) <= 0) {
            SaqueResponseDTO request = new SaqueResponseDTO();
            request.setError("Digite valor correto para saque");
            return status(HttpStatus.BAD_REQUEST).body(request);
        } else if (clientenovo.getPlano_exclusive() == true) {
            clientenovo.setBalance(soma);
            Transacoes transacoes = new Transacoes(null,clientenovo.getId(),OffsetDateTime.now(), "saque", clienteSaqueDTO.getBalance());
            transacaoController.salvarTransacao(transacoes);
            SaqueResponseDTO cli = new SaqueResponseDTO();
            cli.setCliente(clientenovo);
            return status(HttpStatus.OK).body(cli);
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(100)) == 1 &&
                  ((clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == -1
                        || clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == 0))) {
            var tax = clienteSaqueDTO.getBalance().divide(java.math.BigDecimal.valueOf(100)).multiply(java.math.BigDecimal.valueOf(0.4));
            BigDecimal cl = clientenovo.getBalance().subtract(tax.add(clienteSaqueDTO.getBalance()));
            clientenovo.setBalance(cl);
            Transacoes transacoes = new Transacoes(null, clientenovo.getId(),OffsetDateTime.now(),"saque",cl);
            transacaoController.salvarTransacao(transacoes);
            SaqueResponseDTO cli = new SaqueResponseDTO();
            cli.setCliente(clientenovo);
            return status(HttpStatus.OK).body(cli);
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == 1) {
            var taxa = clienteSaqueDTO.getBalance().divide(java.math.BigDecimal.valueOf(100).multiply(java.math.BigDecimal.valueOf(1)));
            BigDecimal resul = clientenovo.getBalance().subtract(clienteSaqueDTO.getBalance()).subtract(taxa);
            clientenovo.setBalance(resul);
            Transacoes transacoes = new Transacoes(null, clientenovo.getId(),OffsetDateTime.now(), "saque",resul);
            transacaoController.salvarTransacao(transacoes);
            SaqueResponseDTO cli = new SaqueResponseDTO();
            cli.setCliente(clientenovo);
            return status(HttpStatus.OK).body(cli);
        } else {
            clientenovo.setBalance(soma);
            Transacoes transacoes = new Transacoes(null, clientenovo.getId(),OffsetDateTime.now(), "saque", soma);
            transacaoController.salvarTransacao(transacoes);
            SaqueResponseDTO cli = new SaqueResponseDTO();
            cli.setCliente(clientenovo);
            return status(HttpStatus.OK).body(cli);
        }
    }
    @PutMapping("/depositar")
    public ResponseEntity<Object> depositar(@RequestBody ClienteSaqueDTO clienteSaqueDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteSaqueDTO.getId());
        if (!cliente.isPresent()) {
            return status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) == -1 || clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) == 0) {
            return status(HttpStatus.BAD_REQUEST).body("Digite valor correto para deposito");
        }
        Cliente clientenovo = cliente.get();
        BigDecimal add = clientenovo.getBalance().add(clienteSaqueDTO.getBalance());
        clientenovo.setBalance(add);
        Transacoes transacoes = new Transacoes(null, clientenovo.getId(),OffsetDateTime.now(),"deposito", clienteSaqueDTO.getBalance());
        transacaoController.salvarTransacao(transacoes);
        return status(HttpStatus.OK).body(clienteRepository.save(clientenovo));

    }
}