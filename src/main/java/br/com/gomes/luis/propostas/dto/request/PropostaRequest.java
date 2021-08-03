package br.com.gomes.luis.propostas.dto.request;

import br.com.gomes.luis.propostas.domain.Proposta;
import br.com.gomes.luis.propostas.validacao.CnpjOuCpf;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {
    @CnpjOuCpf
    @NotBlank(message = "Documento em branco!")
    @Column(unique = true)
    private String documento;
    @NotBlank (message = "Email em branco!")
    @Email(message = "email inválido")
    private String email;
    @NotBlank (message = "Nome em branco!")
    private String nome;
    @NotBlank (message = "Endereço em branco!")
    private String endereco;
    @NotNull(message = "Salário em branco!")
    @Positive(message = "Salário deve possuir um valor positivo!")
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento = documento,
                            this.email = email,
                            this.nome = nome,
                            this.endereco = endereco,
                            this.salario = salario);
    }


    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
