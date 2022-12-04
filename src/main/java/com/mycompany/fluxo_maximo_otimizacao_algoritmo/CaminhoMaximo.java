package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class CaminhoMaximo {
    
    private Boolean chegouFinal = false;
    private List<Nodo> nodoSemCaminho = new ArrayList();
    private List<Nodo> listaNodo = new ArrayList();
    private List<CaminhoNodo> caminhoPercorrido = new ArrayList();
    
    public void realizarMetodo(List<Nodo> listaNodoA){
        
        listaNodo = listaNodoA;
        
        Nodo nodoAtual = listaNodo.get(0);
        
        CaminhoNodo proximoNodo;
        
        while(!chegouFinal){
            
            proximoNodo = procurarCaminhoMaiorCarga(nodoAtual);
            
            if(proximoNodo != null) caminhoPercorrido.add(proximoNodo);
            
            nodoAtual = navegarProximoNodo(proximoNodo, nodoAtual);
            
            atualizarValoresLista(nodoAtual);
            
        }
        
        somarUltimoNodo();
        
    }

    private CaminhoNodo procurarCaminhoMaiorCarga(Nodo nodoAtual) {
        
        CaminhoNodo nodoValorMaximo = nodoAtual.getListaCaminhoNodo().get(0);
        
        for(Integer contador = 1; contador < nodoAtual.getListaCaminhoNodo().size(); contador++){
            
            if(nodoAtual.getListaCaminhoNodo().get(contador).getCarga() > nodoValorMaximo.getCarga()){
                
                nodoValorMaximo = verificarCaminhosPercorrido(nodoAtual.getListaCaminhoNodo().get(contador), nodoValorMaximo);
                
            }
        }
        
        if(verificarEncontrouCaminhoPercorridoOuZerado(nodoValorMaximo)){
            return null;
        }
        
        return nodoValorMaximo;
    }

    private Nodo navegarProximoNodo(CaminhoNodo proximoNodo, Nodo nodoAtual) {
        
        if(nodoAtual.getNodo() == listaNodo.get(listaNodo.size()-1).getNodo()){
            return listaNodo.get(0);
        }
        
        if(proximoNodo == null){
            
            if(caminhoPercorrido.size() <= 0){
                chegouFinal = true;
                return null;
            }
            
            nodoSemCaminho.add(nodoAtual);
            Nodo nodoAnterior;
            try{
                nodoAnterior = buscarNodo(caminhoPercorrido.get(caminhoPercorrido.size()-2).getNodo());
            } catch(Exception ex){
                nodoAnterior = listaNodo.get(0);
            }
            caminhoPercorrido.remove(caminhoPercorrido.size()-1);
            return nodoAnterior;
        }
        
        return buscarNodo(proximoNodo.nodo);
        
    }

    private CaminhoNodo verificarCaminhosPercorrido(CaminhoNodo proximoNodo, CaminhoNodo nodoValorMaximo) {
        Boolean caminhoExistente = false;
        
        for(Integer contador = 0; contador < caminhoPercorrido.size(); contador++){
            if(caminhoPercorrido.get(contador).getNodo() == proximoNodo.getNodo() || proximoNodo.getNodo() == 1){
                caminhoExistente = true;
            }
        }
        
        for(Integer contador = 0; contador < nodoSemCaminho.size(); contador++){
            if(nodoSemCaminho.get(contador).getNodo() == proximoNodo.getNodo()){
                caminhoExistente = true;
            }
        }
        
        if(caminhoExistente) return nodoValorMaximo;
        
        return proximoNodo;
    }

    private Boolean verificarEncontrouCaminhoPercorridoOuZerado(CaminhoNodo nodoValorMaximo) {
        Boolean caminhoExistente = false;
        
        for(Integer contador = 0; contador < caminhoPercorrido.size(); contador++){
            if(caminhoPercorrido.get(contador).getNodo() == nodoValorMaximo.getNodo() || nodoValorMaximo.getNodo() == 1){
                caminhoExistente = true;
            }
        }
        
        for(Integer contador = 0; contador < nodoSemCaminho.size(); contador++){
            if(nodoSemCaminho.get(contador).getNodo() == nodoValorMaximo.getNodo()){
                caminhoExistente = true;
            }
        }
        
        if(nodoValorMaximo.getCarga() == 0) return true;
        
        if(caminhoExistente) return true;
        
        return false;
    }

    private Nodo buscarNodo(Integer nodo) {
        try{
            for(Integer contador = 0; contador < listaNodo.size(); contador++){
                if(listaNodo.get(contador).getNodo() == nodo){
                    return listaNodo.get(contador);
                }
            }
            return null;
        }catch(Exception ex){
            return null;
        }
    }

    private void atualizarValoresLista(Nodo nodoAtual) {
        
        if(nodoAtual != null && nodoAtual.getNodo() == listaNodo.get(0).getNodo() && caminhoPercorrido.size() > 0){
            alterarValorLista();
            caminhoPercorrido.clear();
            nodoSemCaminho.clear();
        }
    }

    private void alterarValorLista() {
        Integer valorParaDiminuir = buscarMenorValorListaPercorrida();
        
        atribuirValores(valorParaDiminuir);
    }

    private Integer buscarMenorValorListaPercorrida() {
        Integer menorValor = caminhoPercorrido.get(0).getCarga();
        for(Integer contador = 1; contador < caminhoPercorrido.size()-1; contador++){
            if(caminhoPercorrido.get(contador).getCarga() < menorValor){
                menorValor = caminhoPercorrido.get(contador).getCarga();
            }
        }
        if(menorValor == 0) chegouFinal = true;
        
        return menorValor;
    }

    private void atribuirValores(Integer valorParaDiminuir) {
        Nodo nodoInicial = listaNodo.get(0);
        for(Integer contador = 0; contador < caminhoPercorrido.size(); contador++){
            trocarValorOrigem(nodoInicial, caminhoPercorrido.get(contador), valorParaDiminuir);
            trocarValorDestino(buscarNodo(caminhoPercorrido.get(contador).getNodo()), nodoInicial, valorParaDiminuir);
            
            try{
                nodoInicial = buscarNodo(caminhoPercorrido.get(contador).getNodo());
            }catch(Exception ex){
                nodoInicial = null;
            }
        }
    }

    private void trocarValorOrigem(Nodo nodoOrigem, CaminhoNodo nodoDestino, Integer valorParaDiminuir) {
        for(Integer contador = 0; contador < nodoOrigem.getListaCaminhoNodo().size(); contador++){
            if(nodoOrigem.getListaCaminhoNodo().get(contador).getNodo() == nodoDestino.getNodo()){
                nodoOrigem.getListaCaminhoNodo().get(contador).setCarga(nodoOrigem.getListaCaminhoNodo().get(contador).getCarga() - valorParaDiminuir);
            }
        }
    }

    private void trocarValorDestino(Nodo nodoDestino, Nodo nodoOrigem, Integer valorParaDiminuir) {
        
        for(Integer contador = 0; contador < nodoDestino.getListaCaminhoNodo().size(); contador++){
            if(nodoDestino.getListaCaminhoNodo().get(contador).getNodo() == nodoOrigem.getNodo()){
                nodoDestino.getListaCaminhoNodo().get(contador).setCarga(nodoDestino.getListaCaminhoNodo().get(contador).getCarga() + valorParaDiminuir);

            }

        }

    }

    private void somarUltimoNodo() {
        Integer total = 0;
        for(Integer contador = 0; contador < listaNodo.get(listaNodo.size()-1).listaCaminhoNodo.size(); contador++){
            total+= listaNodo.get(listaNodo.size()-1).listaCaminhoNodo.get(contador).getCarga();
        }
        System.out.println("Valor Final: " + total);
    }
    
}
