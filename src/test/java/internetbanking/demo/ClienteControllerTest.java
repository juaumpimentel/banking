package internetbanking.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import internetbanking.demo.SaqueDTO.TransacoesDTO;
import internetbanking.demo.controller.ClienteController;
import internetbanking.demo.model.Cliente;
import internetbanking.demo.model.ClienteRepository;
import internetbanking.demo.model.Transacoes;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String  cliente = "{\"name\":\"Henrique\",\"plano_exclusive\":true,\"balance\":400.00,\"count_number\":\"342332\",\"birth_date\":\"09/08/2022\"}";
    private static String  transacoes = "{\"id\":\"1\",\"balance\":50\"}";
    @Test
    public void deveRetornarSucesso_QuandoSalvarCliente() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cliente))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(cliente));
    }
    //Modelo AAA Pattern
    @Test
    public void deveRetornarCliente_QuandoListarCliente() throws Exception {
        String expectedResult = "{\"content\":["+ cliente +"],\"pageable\":{\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"offset\":0,\"pageNumber\":0,\"pageSize\":5,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":1,\"size\":5,\"number\":0,\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\":false},\"first\":true,\"numberOfElements\":1,\"empty\":false}";

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cliente))
                .andExpect(status().isOk());

       //Assert - result
//        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(expectedResult));
    }

    @Test
    public void deveRetornarSucesso_AoFazerSaque() throws Exception{
        Transacoes transacoes1 = new Transacoes("1",)
        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/sacar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transacoes))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(transacoes));




    }
}