package internetbanking.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;

import java.sql.Date;

@Entity
@Table(name = "Transacao")
public class Transacoes {

    public Transacoes(){}

    public Transacoes(Long o, Long id, java.time.OffsetDateTime data_hora, String saque, BigDecimal balance){
        if (o != null) {
            this.id_transacao = o;
        }
        this.id_cliente = id;
        this.data_hora = data_hora;
        this.tipo_transacao = saque;
        this.valor = balance;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_transacao;




    @Column
    private long id_cliente;


    @Column(name = "data_hora")
    //@JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)"YYYY-MM-DDThh:mm:ss.sTZD"
    //@JsonFormat(pattern = "YYYY-MM-DD'T'hh:mm:ssXXX ", shape = STRING)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssXXX")
    private java.time.OffsetDateTime data_hora;



    @Column
    private String tipo_transacao;

    @Column
    private BigDecimal valor;

    public Transacoes(long id_transacao, long id_cliente, java.time.OffsetDateTime data_hora, String tipo_transacao, BigDecimal valor) {
        this.id_transacao = id_transacao;
        this.id_cliente = id_cliente;
        this.data_hora = data_hora;

        this.tipo_transacao = tipo_transacao;
        this.valor = valor;
    }

    public long getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(long id_transacao) {
        this.id_transacao = id_transacao;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public java.time.OffsetDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(java.time.OffsetDateTime data_hora) {
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
