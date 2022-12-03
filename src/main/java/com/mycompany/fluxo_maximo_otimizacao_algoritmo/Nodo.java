package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author rafael
 */
@Data
@Builder
public class Nodo implements Comparable<Nodo>{
    public Integer nodo;
    public List<CaminhoNodo> listaCaminhoNodo;
    
    @Override
    public int compareTo(Nodo outroNodo) {
        if (this.nodo < outroNodo.getNodo()) { 
          return -1; 
        } 
        if (this.nodo > outroNodo.getNodo()) { 
          return 1; 
        } 
        return 0; 
    }
}
