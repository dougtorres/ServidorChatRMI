/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Douglas Torres
 */
public class Usuario extends UnicastRemoteObject implements UsuarioIF {

    private String nome;

    public Usuario(String nome) throws RemoteException {
        this.nome = nome;
    }

    @Override
    public void mensagem(String mensagem) throws RemoteException {
        System.out.println(mensagem);
    }

    /**
     * @return the nome
     */
    @Override
    public String getNome()throws RemoteException{
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    @Override
    public void setNome(String nome) throws RemoteException{
        this.nome = nome;
    }

}
