/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.server;

import br.edu.ifpb.model.Usuario;
import br.edu.ifpb.model.UsuarioIF;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas Torres
 */
public class Servidor extends UnicastRemoteObject implements ServidorIF {

    private ArrayList<UsuarioIF> usuarios;
    private Calendar data;

    public Servidor() throws RemoteException {
        this.usuarios = new ArrayList<UsuarioIF>();
    }

    @Override
    public UsuarioIF addUsuario(UsuarioIF user) throws RemoteException {
  
        for (UsuarioIF u : this.usuarios) {
            if(u.getNome().equals(user.getNome())){
                user.mensagem("Ja existe um usuario com esse nome da sala");
                return null;
          
            }
        }
        this.usuarios.add(user);
        this.data = Calendar.getInstance();
        for (UsuarioIF u : this.usuarios) {
            u.mensagem("Usuario " + user.getNome() + " entrou na sala! <" + data.getTime() + ">");
        }

        user.mensagem("Digite HELP para ver os comandos disponiveis");
        return user;

    }

    @Override
    public void sair(UsuarioIF user) throws RemoteException {
        user.mensagem("Voce saiu da sala");
        this.data = Calendar.getInstance();
        for (UsuarioIF u : this.usuarios) {
            u.mensagem("O usuario " + user.getNome() + " saiu da sala! <" + data.getTime() + ">");
        }
        this.usuarios.remove(user);
    }

    @Override
    public void sendAll(UsuarioIF user, String mensagem) throws RemoteException {
        this.data = Calendar.getInstance();
        for (UsuarioIF u : this.usuarios) {
            u.mensagem(user.getNome() + ": "+mensagem+" <"+data.getTime() + ">");
        }
    }

    @Override
    public void sendUser(UsuarioIF user, String nomeDoUsuario, String mensagem) throws RemoteException {
        UsuarioIF u = getUsuario(nomeDoUsuario);
        if(u == null){
            user.mensagem("O usuario digitado nao existe!");
        }else{
            this.data = Calendar.getInstance();
            u.mensagem(user.getNome() + " (Mensagem Privada): "+mensagem+" <"+data.getTime() + ">");
            user.mensagem("Para "+u.getNome()+" (Mensagem Privada): "+mensagem+" <"+data.getTime() + ">");
        }
        
    }

    @Override
    public void list(UsuarioIF user) throws RemoteException {
        user.mensagem("Usuarios na sala: ");
        for (UsuarioIF u : this.usuarios) {
            user.mensagem(u.getNome());
        }
    }

    @Override
    public void rename(UsuarioIF user, String novoNome) throws RemoteException {
        for (UsuarioIF u : this.usuarios) {
            if(u.getNome().equals(novoNome)){
                user.mensagem("Ja existe um usuario com esse nome da sala");
                return;
            }
        }
        user.setNome(novoNome);
        user.mensagem("Nome alterado com sucesso!");
    }

    @Override
    public UsuarioIF getUsuario(String nome) throws RemoteException {
        for (UsuarioIF u : this.usuarios) {
            if (u.getNome().equals(nome)) {
                return u;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.bind("ServidorRemoto", new Servidor());
            System.out.println("Servidor iniciado com sucesso!");
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
