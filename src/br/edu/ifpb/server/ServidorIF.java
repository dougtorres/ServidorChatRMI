/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.server;

import br.edu.ifpb.model.Usuario;
import br.edu.ifpb.model.UsuarioIF;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Douglas Torres
 */
public interface ServidorIF extends Remote{
    
    public UsuarioIF addUsuario(UsuarioIF user)throws RemoteException;
    public void sair(UsuarioIF user)throws RemoteException;
    public void sendAll(UsuarioIF user, String mensagem)throws RemoteException;
    public void sendUser(UsuarioIF user, String nomeDoUsuario, String mensagem)throws RemoteException;
    public void list(UsuarioIF user)throws RemoteException;
    public void rename(UsuarioIF user, String novoNome)throws RemoteException;
    public UsuarioIF getUsuario(String nome)throws RemoteException;
    
}
