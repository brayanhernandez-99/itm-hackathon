package com.co.hackathon.itm_hackathon_web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Asistencia_Acompanantes")
@Getter
@Setter
@NoArgsConstructor
public class AsistenciaAcompanante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "acompanante_id", nullable = false)
    private Acompanante acompanante;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private Boolean asistencia_moto;

    @Column(nullable = false)
    private Integer kilometraje;
}
