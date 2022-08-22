package internetbanking.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;


@Entity
public class Cliente {

    public Cliente(){}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name")

    private String name;

    @Column(name = "plano_exclusivo")
    private boolean plano_exclusive;

    @Column(name = "balance")
    private BigDecimal balance;


    @Column(name = "account_number")
    private String account_number;

    @Column(name = "birth_date")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
    private LocalDate birth_date;



    public Cliente(Long id , String name, boolean plano_exclusive, BigDecimal balance, String account_number, LocalDate birth_date) {
        this.id = id;
        this.name = name;
        this.plano_exclusive = plano_exclusive;
        this.balance = balance;
        this.account_number = account_number;
        this.birth_date =  birth_date;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlano_exclusive() {
        return plano_exclusive;
    }

    public void setPlano_exclusive(boolean plano_exclusive) {
        this.plano_exclusive = plano_exclusive;
    }

    public boolean getPlano_exclusive(){
        return plano_exclusive;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public BigDecimal setBalance(BigDecimal balance) {
        this.balance = balance;
        return balance;



    }

    public String getCount_number() {
        return account_number;
    }

    public void setCount_number(String count_number) {
        this.account_number = count_number;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public Long getId() {
        return id;
    }



}

