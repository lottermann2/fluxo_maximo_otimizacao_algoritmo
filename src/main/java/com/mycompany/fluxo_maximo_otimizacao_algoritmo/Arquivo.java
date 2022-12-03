package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author rafael
 */

@Stateless
public class Arquivo {
    
    private final String CAMINHO_ARQUIVO = "/home/rafael/Downloads/custos totais.rtf";
    private List<Nodo> listaNodos = new ArrayList();

    public List<Nodo> lerArquivo() throws Exception{
        
        carregarArquivo();
        
        Collections.sort(listaNodos);

        return listaNodos;
    }

    private void carregarArquivo() throws Exception {
        try {
            
            BufferedReader br = lerDadosArquivos();
            String line = br.readLine();
            
            while (line != null) {
                try{
                    
                    line = br.readLine();
                    
                    StringTokenizer tok = new StringTokenizer(line, " ");

                    Integer nodoOrigem = Integer.parseInt(tok.nextToken());
                    Integer nodoDestino = Integer.parseInt(tok.nextToken());
                    Integer valorCarga = Integer.parseInt(tok.nextToken());
                    
                    criarNodo(nodoOrigem, nodoDestino, valorCarga);
                    criarNodo(nodoDestino, nodoOrigem, 0);

                }catch (Exception ex){
                    System.out.println("ERRO! Linha: " + line);
                }
            }
        } catch (Exception ex) {
            throw ex;   
        }
    }

    private BufferedReader lerDadosArquivos() throws Exception {
        FileInputStream stream = new FileInputStream(CAMINHO_ARQUIVO);
        RTFEditorKit kit = new RTFEditorKit();
        Document doc = kit.createDefaultDocument();
        kit.read(stream, doc, 0);

        String plainText = doc.getText(0, doc.getLength());
        BufferedReader br = new BufferedReader(new StringReader(plainText));
        return br;
    }

    private void criarNodo(Integer nodoOrigem, Integer nodoDestino, Integer valorCarga) {
    
        Nodo nodo = procurarNodoExistente(nodoOrigem);
        
        CaminhoNodo caminhoNodo = criarCaminhoNodo(nodoDestino, valorCarga, nodo);
        
        if(caminhoNodo != null) nodo.getListaCaminhoNodo().add(caminhoNodo);

        atualizarListaNodos(nodo);

    }

    private CaminhoNodo criarCaminhoNodo(Integer nodoDestino, Integer valorCarga, Nodo nodoOrigem) {
        
        for(CaminhoNodo caminhoNodo : nodoOrigem.getListaCaminhoNodo()){
            if(caminhoNodo.getNodo() == nodoDestino) return null;
        }
        
        return CaminhoNodo.builder()
                .nodo(nodoDestino)
                .carga(valorCarga)
                .build();
    }

    private Nodo procurarNodoExistente(Integer nodoOrigem) {
        for(Nodo nodo : listaNodos){
            if(nodo.getNodo() == nodoOrigem) return nodo;
        }
        return Nodo.builder()
                .nodo(nodoOrigem)
                .listaCaminhoNodo(new ArrayList())
                .build();
    }

    private void atualizarListaNodos(Nodo nodo) {
        Boolean adicionouLista = false;
        
        for(Nodo nodoLista : listaNodos){
            if(nodoLista.getNodo() == nodo.getNodo()){
                
                nodoLista.setListaCaminhoNodo(nodo.getListaCaminhoNodo());
                adicionouLista = true;
            }
        }
        
        if(adicionouLista != true){
            listaNodos.add(nodo);
        }
        
    }
}
