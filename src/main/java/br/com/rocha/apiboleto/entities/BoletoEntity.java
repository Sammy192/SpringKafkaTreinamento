package br.com.rocha.apiboleto.entities;

import br.com.rocha.apiboleto.entities.enums.SituacaoBoletoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoletoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String codigoBarras;
    @Column
    private SituacaoBoletoEnum situacaoBoleto;
    @Column
    private LocalDateTime dataCriacao;
    @Column
    private LocalDateTime dataAtualizacao;
}