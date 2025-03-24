package com.co.hackathon.itm_hackathon_web.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Miembros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoMiembro tipo;  // Usando el Enum TipoMiembro

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos_organizados;

}
