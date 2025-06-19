package repository;

import java.io.File;
import java.io.IOException;

/*
 * classe para agrupar as funções do repositório CSV
 */
public class RegistroPontoCsvRepository {

    protected String caminhoCsv;

    /*
     * obtém o sistema operacional do usuário e criar um caminho para o arquivo CSV
     * baseado nisso
     */
    private String obterCaminhoCsv() {
        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        boolean ehLinux = sistemaOperacional.contains("linux");

        String caminhoDiretorio = ehLinux
                ? System.getProperty("user.home") + "/applications/controle-ponto"
                : "C:\\applications\\controle-ponto";

        return caminhoDiretorio + (ehLinux ? "/registros.csv" : "\\registros.csv");
    }

    /*
     * verifica se o arquivo CSV já existe no computador e se não existir cria ele a
     * partir do caminho obtido em obterCaminhoCsv()
     */
    public void criarCsv() throws IOException {
        /*
         * chama o método obterCaminhoCsv() para obter o caminho completo do arquivo CSV
         * no linux: /home/usuario/applications/controle-ponto/registros.csv
         * no windows: C:\applications\controle-ponto\registros.csv
         */
        this.caminhoCsv = obterCaminhoCsv();

        /*
         * cria o o objeto File arquivo representando o arquivo CSV que será
         * verificado/criado
         */
        File arquivo = new File(caminhoCsv);

        /*
         * obtém o diretório pai do arquivo verificado com a função getParentFile()
         * é necessário para garantir que o diretório exista antes de criar o arquivo
         * se caminhoCsv = /home/usuario/applications/controle-ponto/registros.csv
         * então diretorio = /home/usuario/applications/controle-ponto
         */
        File diretorio = arquivo.getParentFile();

        /*
         * verifica se o diretório do arquivo CSV já existe
         * se não existir, tenta criar com mkdirs()
         * retorna uma IOException caso occora algum erro.
         */
        if (!diretorio.exists()) {
            boolean criado = diretorio.mkdirs();
            if (!criado) {
                throw new IOException("Erro ao criar diretório: " + diretorio.getAbsolutePath());
            }
        }

        /*
         * se o diretório do arquivo CSV for criado com sucesso, então tenta criar o
         * arquivo com createNewFile()
         * retorna uma IOException caso occora algum erro.
         */
        if (!arquivo.exists()) {
            boolean criado = arquivo.createNewFile();
            if (!criado) {
                throw new IOException("Não foi possível criar o arquivo CSV.");
            }
        }

    }
}
