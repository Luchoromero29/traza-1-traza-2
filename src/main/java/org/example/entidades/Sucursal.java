package org.example.entidades;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "empresa")  // Excluir empresa para evitar recursión infinita
@SuperBuilder
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;


    private Domicilio domicilio;


    private Empresa empresa;

    private List<Articulo> articulos = new ArrayList<>();

    public void agregarArticulo(Articulo articulo) {
        Objects.requireNonNull(articulo, "articulo no puede ser null");
        if (articulos == null) articulos = new ArrayList<>(); // guard null-proof

        boolean yaExiste = articulos.stream()
                .anyMatch(a -> Objects.equals(a.getId(), articulo.getId()));
        if (!yaExiste) {
            articulos.add(articulo);
        }
    }

    public boolean quitarArticuloPorId(Long articuloId) {
        return articulos.removeIf(a -> Objects.equals(a.getId(), articuloId));
    }

    public Optional<Articulo> buscarArticulo(Long articuloId) {
        return articulos.stream()
                .filter(a -> Objects.equals(a.getId(), articuloId))
                .findFirst();
    }

    public void mostrarArticulos() {
        if (articulos.isEmpty()) {
            System.out.println("Sucursal '" + nombre + "' no tiene artículos.");
        } else {
            System.out.println("Artículos de sucursal '" + nombre + "':");
            articulos.forEach(a -> System.out.println(" - " + a));
        }
    }

/*
    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horarioApertura=" + horarioApertura +
                ", horarioCierre=" + horarioCierre +
                ", esCasaMatriz=" + esCasaMatriz +
                ", domicilio=" + domicilio +  // Aquí se imprime el domicilio, que ya tiene su propia lógica de toString
                '}';
    }
   */
}
