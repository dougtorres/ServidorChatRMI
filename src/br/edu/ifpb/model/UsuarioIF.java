/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Douglas Torres
 */
public interface UsuarioIF extends Remote{
    
    public void mensagem(String mensagem) throws RemoteException;
    public String getNome() throws RemoteException;
    public void setNome(String nome) throws RemoteException;
    
}
