package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author rafael
 */


public class Main {

    
    public static void main(String[] args) throws Exception {
        
                Date dataAtual = new Date();
                
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String date = simpleDateFormat.format(dataAtual);
        
        Arquivo arquivo = new Arquivo();
        
        arquivo.lerArquivo();
        
//        percorrerCaminho();
    }
    
}
