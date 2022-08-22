package internetbanking.demo.SaqueDTO;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransacoesDTO {


    private long id;

    private LocalDate data_hora;

    private String tipo_transacao;


    private BigDecimal valor;


    public TransacoesDTO(long id, LocalDate data_hora, String tipo_transacao, BigDecimal valor) {
        this.id = id;
        this.data_hora = data_hora;
        this.tipo_transacao = tipo_transacao;
        this.valor = valor;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDate data_hora) {
        this.data_hora = data_hora;
    }

    public String getTipo_transacao() {
        return tipo_transacao;
    }

    public void setTipo_transacao(String tipo_transacao) {
        this.tipo_transacao = tipo_transacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
