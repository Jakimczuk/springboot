package com.alkemy.icon.icons.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;




@Entity
@Table(name = "pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String denominacion;

    @Column(name = "cantidad_habitantes")
    private Long cantidadHabitantes;

    private Long superficie; // m2


    // este es para buscar informacion se declara un obj de tipo contienteEntity donde va a estar la informacion que traiga
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // muchos a uno fetch : la inicializacion es de tipo temprana esto quiere decir que cada vez que traiga un pais va a venir con su continente , cascade es para que si eliminas un continente se eliminen los paises
    @JoinColumn(name = "continente_id",insertable = false,updatable = false) // es como joinear los datos , no se puede insertar ni modificar por eso el false
    private ContinenteEntity continente;


    //este es guardar y actualizar informacion , donde realmente esta el id
    @Column(name = "continente_id",nullable = false)
    private Long continenteId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    @JoinTable(
            name ="icon_pais",
            joinColumns = @JoinColumn(name ="pais_id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id"))
    private Set<IconEntity> icons = new HashSet<>();

    @Override
    public  boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        final PaisEntity other = (PaisEntity) obj;
        return other.id == this.id;
    }




}
