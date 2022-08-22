package internetbanking.demo.SaqueDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import internetbanking.demo.model.Cliente;


public class SaqueResponseDTO {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String error;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Cliente cliente;
}
