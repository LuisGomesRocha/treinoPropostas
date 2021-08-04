package br.com.gomes.luis.propostas.domain;

import br.com.gomes.luis.propostas.dto.request.AnalisePropostaRequest;
import br.com.gomes.luis.propostas.dto.request.SolicitaCartaoRequest;
import br.com.gomes.luis.propostas.dto.response.PropostaResponse;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@Entity
@Table(name = "t_proposta")
public class Proposta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank(message = "Documento em branco!")
    @Column(unique = true)
    private String documento;
    @NotBlank(message = "Email em branco!")
    @Email(message = "email inválido")
    private String email;
    @NotBlank(message = "Nome em branco!")
    private String nome;
    @NotBlank(message = "Endereço em branco!")
    private String endereco;
    @NotNull(message = "Salário em branco!")
    @Positive(message = "Salário deve possuir um valor positivo!")
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;
    private String numeroCartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario, StatusProposta statusProposta, String numeroCartao) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = statusProposta;
        this.numeroCartao = numeroCartao;
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public AnalisePropostaRequest toAnalise() {
        return new AnalisePropostaRequest(this.documento, this.nome, this.id);
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public SolicitaCartaoRequest toSolicitaCartao() {
        return new SolicitaCartaoRequest(this.id);
    }

}

