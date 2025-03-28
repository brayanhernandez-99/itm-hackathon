package com.co.hackathon.itm_hackathon_web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Eventos")
@Getter
@Setter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "organizador_id", nullable = false)
    private Miembro organizador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoEvento tipo;
    
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsistenciaAcompanante> asistenciasAcompanantes;
}
