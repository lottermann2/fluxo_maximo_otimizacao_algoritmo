package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author rafael
 */

@Data
@Builder
public class CaminhoNodo {
    public Integer nodo;
    public Integer carga;
}
