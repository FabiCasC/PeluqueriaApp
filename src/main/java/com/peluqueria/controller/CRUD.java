/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.com.peluqueria.controller;
import java.util.List;

/**
 *
 * @author LAB-USR-LCENTRO
 */


public interface CRUD<T> {
    void crear(T item);
    T ver(String criterio);
    void actualizar(T item);
    void eliminar(String criterio);
    List<T> listar();
}

