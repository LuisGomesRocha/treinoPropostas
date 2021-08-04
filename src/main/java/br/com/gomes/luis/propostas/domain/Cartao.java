package br.com.gomes.luis.propostas.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "t_cartao")
public class Cartao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @NotNull
    private BigDecimal limite;

    private boolean renegociacao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vencimento")
    private Vencimento vencimento;

    @NotBlank
    private String idProposta;


    @Deprecated
    public Cartao() {

    }

    public Cartao(LocalDateTime emitidoEm, String titular, BigDecimal limite, String idProposta) {
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public Cartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular, List<Bloqueio> bloqueios, List<AvisoViagem> avisos, List<Carteira> carteiras, List<Parcela> parcelas, @NotNull BigDecimal limite, @NotBlank boolean renegociacao, @NotBlank Vencimento vencimento, @NotBlank String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
//        this.bloqueios = bloqueios;
//        this.avisos = avisos;
//        this.carteiras = carteiras;
//        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public boolean isRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(boolean renegociacao) {
        this.renegociacao = renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}