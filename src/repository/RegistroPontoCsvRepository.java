package repository;

import java.io.File;
import java.io.IOException;

import model.RegistroPonto;
import util.CsvUtil;
import util.FormatUtil;

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

    /*
     * adiciona um registroPonto no arquivo CSV
     */
    public void adicionarRegistroPonto(RegistroPonto registroPonto) {
        /*
         * verifica se o caminhoCsv não foi declarado ou se está vazio, caso seja "null"
         * chama obterCaminhoCsv() para definir o caminho do arquivo CSV
         */
        if (caminhoCsv == null || caminhoCsv.isEmpty()) {
            this.caminhoCsv = obterCaminhoCsv();
        }

        /*
         * usa o método registroPontoParCsv() da classe utilitária FormatUtil para
         * converter o objeto registroPonto em uma String formatada para CSV
         */
        String linhaCsv = FormatUtil.registroPontoParaCsv(registroPonto);

        /*
         * cria uma instância da classe utilitária CsvUtil e chama o método
         * escreverLinha() e adiciona o registroPonto
         */
        CsvUtil csvUtil = new CsvUtil();
        csvUtil.escreverLinha(caminhoCsv, linhaCsv);
    }

    /*
     * método responsável por fazer a exclusão de um registro de ponto específico
     * através do id
     */
    public void removerRegistroPonto(String idRegistroPonto) {
        /*
         * verifica se o caminhoCsv não foi declarado ou se está vazio, caso seja "null"
         * chama obterCaminhoCsv() para definir o caminho do arquivo CSV
         */
        if (caminhoCsv == null || caminhoCsv.isEmpty()) {
            this.caminhoCsv = obterCaminhoCsv();
        }

    }

    /*
     * método responsável por fazer a edição de um registro de ponto escpecífico
     * através do id
     */
    public void editarRegistroPonto(String idRegistroPonto) {

    }

    public RegistroPonto buscarRegistroPontoPorId(int id) {
        // Implementar a lógica de busca de registro ponto por ID no CSV
        // Isso pode envolver ler o arquivo e retornar o registro correspondente
        return null; // Retornar null ou um objeto RegistroPonto encontrado
    }

    public RegistroPonto[] listarRegistrosPonto() {
        // Implementar a lógica de listagem de todos os registros ponto no CSV
        // Isso pode envolver ler o arquivo e retornar um array de RegistroPonto
        return new RegistroPonto[0]; // Retornar um array vazio ou os registros encontrados
    }
}
