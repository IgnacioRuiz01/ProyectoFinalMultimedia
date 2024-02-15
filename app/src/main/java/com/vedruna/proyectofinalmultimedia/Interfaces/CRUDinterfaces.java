package com.vedruna.proyectofinalmultimedia.Interfaces;

import com.vedruna.proyectofinalmultimedia.DTO.RapperDTO;
import com.vedruna.proyectofinalmultimedia.Model.Rapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


/**
 * Esta interfaz define los métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la API REST para los raperos.
 */
public interface CRUDinterfaces {


    /**
     * Método para obtener todos los raperos.
     * @return Una lista de objetos de tipo Rapper encapsulados en una llamada asíncrona.
     */
    @GET("rapper")
    Call<List<Rapper>> getAll();


    /**
     * Método para crear un nuevo rapero.
     * @param dto Objeto de transferencia de datos (DTO) que contiene la información del rapero a crear.
     * @return Un objeto de tipo Rapper encapsulado en una llamada asíncrona.
     */
    @POST("rapper")
    Call<Rapper>create(@Body RapperDTO dto);


    /**
     * Método para actualizar la información de un rapero existente.
     * @param id El ID del rapero a actualizar.
     * @param rapper El objeto Rapper con la información actualizada.
     * @return Un objeto de tipo Rapper encapsulado en una llamada asíncrona.
     */
    @PUT("rapper/{id}")
    Call<Rapper> actualizar(@Path("id") int id, @Body Rapper rapper);


    /**
     * Método para eliminar un rapero.
     * @param id El ID del rapero a eliminar.
     * @return Una llamada asíncrona que no devuelve ningún resultado.
     */
    @DELETE("rapper/{id}")
    Call<Void>delete(@Path("id")int id);
}
