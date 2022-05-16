import java.util.ArrayList;

public class Transicao {
    private Estado estadoAtual;
    private String simboloEntrada;
    private String topoDaPilha;
    private Estado novoEstado;
    private String simboloEmpilha;

    public Transicao(String estadoAtual, String simboloEntrada, String topoDaPilha, String proximoEstado, String simboloEmpilha) {
        this.estadoAtual = new Estado(estadoAtual);
        this.simboloEntrada = simboloEntrada;
        this.topoDaPilha = topoDaPilha;
        this.novoEstado = new Estado(proximoEstado);
        this.simboloEmpilha = simboloEmpilha;
    }

    public static ArrayList<Transicao> getTransicao(ArrayList<Transicao> transicoes, Estado estado) {
        ArrayList<Transicao> retorno = new ArrayList<>();
        for (Transicao t : transicoes) {
            if (t.estadoAtual.getNome().equals(estado.getNome())) {
                retorno.add(t);
            }
        }
        return retorno;
    }

    public String getSimboloEntrada() {
        return simboloEntrada;
    }

    public void setSimboloEntrada(String simboloEntrada) {
        this.simboloEntrada = simboloEntrada;
    }

    public String getTopoDaPilha() {
        return topoDaPilha;
    }

    public void setTopoDaPilha(String topoDaPilha) {
        this.topoDaPilha = topoDaPilha;
    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Estado estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Estado getNovoEstado() {
        return novoEstado;
    }

    public void setNovoEstado(Estado novoEstado) {
        this.novoEstado = novoEstado;
    }

    public String getSimboloEmpilha() {
        return simboloEmpilha;
    }

    public void setSimboloEmpilha(String simboloEmpilha) {
        this.simboloEmpilha = simboloEmpilha;
    }
    

}