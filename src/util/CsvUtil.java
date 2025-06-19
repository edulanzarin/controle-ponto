package util;

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
        // Lê linhas brutas

        return List.of();
    }

    /*
     * faz a escrita de uma linha no arquivo CSV. essa linha será adicionada sempre
     * após a última linha, nunca substituindo uma já existente
     */
    public void escreverLinha(String caminhoCsv, String linhaCsv) {
        // Adiciona uma linha CSV ao arquivo
    }
}
