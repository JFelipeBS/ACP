import java.util.ArrayList;

public class Estado {

    private String nome;
    private boolean eFinal;
    private boolean eInicial;

    public Estado(String nome) {
        this.nome = nome;
        this.eFinal = false;
        this.eInicial = false;
    }

    public static Estado getSelecionaEstados(ArrayList<Estado> estados, String nome){
        for(Estado e: estados){
            if(e.getNome().equals(nome)){
                return e;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean iseFinal() {
        return eFinal;
    }

    public void seteFinal(boolean eFinal) {
        this.eFinal = eFinal;
    }

    public boolean isEstInicial() {
        return eInicial;
    }

    public void setEstInicial(boolean eInicial) {
        this.eInicial = eInicial;
    }

    @Override
    public String toString() {
        return nome;
    }

}
