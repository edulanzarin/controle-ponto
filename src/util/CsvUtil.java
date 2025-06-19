package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 * classe responsável por agrupar as funções que fazem as tratativas de arquivos CSV, como leitura e escrita
 */
public class CsvUtil {

    /*
     * faz a leitura linha por linha do arquivo CSV e retorna uma List<String[]> de
     * todas as linhas
     */
    public List<String[]> lerLinhas(String caminhoCsv) {
        /* TODO implementar leitura do arquivo CSV */

        return List.of();
    }

    /*
     * faz a escrita de uma linha no arquivo CSV. essa linha será adicionada sempre
     * após a última linha, nunca substituindo uma já existente
     */
    public void escreverLinha(String caminhoCsv, String linhaCsv) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoCsv, true))) {
            bw.write(linhaCsv);
            bw.newLine(); /* garante que a próxima linha fique em uma linha nova */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
