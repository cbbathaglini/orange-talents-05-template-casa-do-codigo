package br.com.casadocodigo.casadocodigo.dtoresponse;

import br.com.casadocodigo.casadocodigo.model.Cliente;

public class ClienteDTOResponse {

    private Long id;
    public ClienteDTOResponse() {
    }
    public ClienteDTOResponse(Cliente cliente) {
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClienteDTOResponse{" +
                "id=" + id +
                '}';
    }
}
