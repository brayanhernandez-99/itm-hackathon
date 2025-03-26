package com.co.hackathon.itm_hackathon_web.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Acompanantes")
@Getter
@Setter
@NoArgsConstructor
public class Acompanante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "acompanante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsistenciaAcompanante> asistencias;
}
