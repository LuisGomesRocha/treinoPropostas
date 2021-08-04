package br.com.gomes.luis.propostas.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_carteira")
public class Carteira {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @NotBlank
    private String idCartao;
    @NotBlank
    private String email;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCartao, String email) {
        this.idCartao = idCartao;
        this.email = email;
      }

    public String getId() {
        return id;
    }
}
