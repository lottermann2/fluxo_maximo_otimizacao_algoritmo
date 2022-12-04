package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 * @author rafael
 */


public class Main {

    
    public static void main(String[] args) throws Exception {
        
        Arquivo arquivo = new Arquivo();
        CaminhoMaximo caminhoMaximo = new CaminhoMaximo();
        
        List<Nodo> listaNodo = arquivo.lerArquivo();
        
        caminhoMaximo.realizarMetodo(listaNodo);
    }

}
