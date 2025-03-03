/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.peluqueria.dao;

import java.util.List;
import main.java.com.peluqueria.controller.CRUD;
import main.java.com.peluqueria.model.Cliente;

/**
 *
 * @author fabia
 */
public interface ClienteDAO extends CRUD<Cliente> {
    List<Cliente> buscar(String criterio);
}

