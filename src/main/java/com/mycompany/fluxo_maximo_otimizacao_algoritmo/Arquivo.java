package com.mycompany.fluxo_maximo_otimizacao_algoritmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author rafael
 */

@Stateless
public class Arquivo {
    
    public void lerArquivo() throws Exception{
        
        String dadosArquivo = carregarArquivo();
        
        
    }

    private String carregarArquivo() throws Exception {
        try {
            FileInputStream stream = new FileInputStream("/home/rafael/Downloads/custos totais.rtf");
            RTFEditorKit kit = new RTFEditorKit();
            Document doc = kit.createDefaultDocument();
            kit.read(stream, doc, 0);

            String plainText = doc.getText(0, doc.getLength());
            BufferedReader br = new BufferedReader(new StringReader(plainText));
            String line = br.readLine();
            while (line != null) {
              StringTokenizer tok = new StringTokenizer(line, " ");
              while (tok.hasMoreTokens()) {
                System.out.print(tok.nextToken() + "|");
              }
              System.out.println();
              line = br.readLine();
            }
            return line;
        } catch (Exception ex) {
            return null;
        }
    }

}
