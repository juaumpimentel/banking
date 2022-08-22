package internetbanking.demo.SaqueDTO;

import java.math.BigDecimal;


public class ClienteSaqueDTO  {

    private long id;
    private BigDecimal balance;

    public ClienteSaqueDTO(BigDecimal balance){}


    public ClienteSaqueDTO(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ClienteSaqueDTO transformaParaObjeto(){
        return new ClienteSaqueDTO(balance);
    }
}