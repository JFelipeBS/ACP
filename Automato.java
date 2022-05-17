import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Automato {

    private ArrayList<String> alfabeto;
    private ArrayList<String> alfabetoPilha;
    private String epsilon;
    private String simboloInitPilha;
    private ArrayList<Estado> estadosACP;
    private Stack pilhaACP;
    private Estado estadoAtual;
    private ArrayList<Transicao> transicoes;

    public Automato(ArrayList<String> alfabeto, ArrayList<String> alfabetoPilha, String simboloInitPilha,
            ArrayList<Estado> estadosACP, ArrayList<Transicao> transicoes, String epsilon) {

        this.alfabeto = alfabeto;
        this.epsilon = epsilon;
        this.alfabetoPilha = alfabetoPilha;
        this.simboloInitPilha = simboloInitPilha;
        this.estadosACP = estadosACP;
        this.pilhaACP = new Stack<>();
        this.pilhaACP.push(simboloInitPilha);
        this.transicoes = transicoes;
        this.estadoAtual = buscar(estadosACP);

    }

    public void verificarCadeia(String cadeia, Automato automato) {
        
        // tranfere cadeia para um arraylist
        ArrayList<String> verificaCadeia = new ArrayList<>();
        verificaCadeia.addAll(Arrays.asList(cadeia.split("")));

        // Pegando transições possíveis para o estado atual
        ArrayList<Transicao> transicoesPossiveis = Transicao.getTransicao(automato.getTransicoes(),automato.getEstadoAtual());

        // variável de controle para novos trasições
        boolean verificar;

        // ate que nao exista mais transições
        while (!transicoesPossiveis.isEmpty()) {
            verificar = false;
            for (Transicao trasiPossivel : transicoesPossiveis) {

                // para um entrada vazia
                if (trasiPossivel.getSimboloEntrada().equals(automato.epsilon)) {

                    // comparando o topo da pilhaACP de trasição com o topo ACP
                    
                    if (automato.getPilha().empty() == false) {
                        if (trasiPossivel.getTopoDaPilha().equals(automato.getPilha().peek())) {
                            // desempilha
                            if (automato.getPilha().empty() == false) {
                                automato.getPilha().pop();  
                            }
    
                            // (negado) compara o simbo a recebido com o epson
                            if (!trasiPossivel.getSimboloEmpilha().equals(automato.epsilon)) {
                                //empilhando
                                Transicao transicaoEmpilha = trasiPossivel;
                                String[] s = transicaoEmpilha.getSimboloEmpilha().split("");
                                for (int i = s.length - 1; i >= 0; i--) {
                                    automato.getPilha().push(s[i]);
                        
                                }
                            }
    
                           // variavel para pegar estado velho e ecrever na tela
                            String estadoVelho = estadoAtual.getNome();
                            automato.setEstadoAtual(Estado.getSelecionaEstados(automato.getConjuntoEstados(),trasiPossivel.getNovoEstado().getNome()));
 
 
                            // escrevendo transição
                            String escrita = "vazio";
                            if (!automato.getPilha().empty()) {
                             escrita = (String) automato.getPilha().peek();
                            }
                            System.out.println("&*(" + estadoVelho + ", " + epsilon + ", "+ trasiPossivel.getTopoDaPilha() + ") = (" + automato.getEstadoAtual()+ ", " + escrita + ")");
                            verificar = true;
                        }

                         
                    }
                    


                } else if (!verificaCadeia.isEmpty() && !automato.getPilha().empty()) {
                    if (trasiPossivel.getSimboloEntrada().equals(verificaCadeia.get(0)) && trasiPossivel.getTopoDaPilha().equals(automato.getPilha().peek())) {

                        // setando novo estado
                        String estadoVelho = estadoAtual.getNome();
                        automato.setEstadoAtual(Estado.getSelecionaEstados(automato.getConjuntoEstados(),trasiPossivel.getNovoEstado().getNome()));

                        // o formato de entrar e do tipo "aZ"entao remove um e adiciona dois
                        automato.getPilha().pop();

                        if (!trasiPossivel.getSimboloEmpilha().equals(automato.epsilon)) {
                            
                            //Empilhando
                            Transicao transicaoEmpilha = trasiPossivel;
                            String[] s = transicaoEmpilha.getSimboloEmpilha().split("");
                            for (int i = s.length - 1; i >= 0; i--) {
                                automato.getPilha().push(s[i]);
                    
                            }

                        }
                        String removidoCadeia = verificaCadeia.remove(0);
                       
                        System.out.println("&*(" + estadoVelho + ", " + removidoCadeia + ", "+ trasiPossivel.getTopoDaPilha() + ") = ("  + automato.estadoAtual + ", " + automato.getPilha().peek() + " topo atual da pilha)");
                        
                        // estado, simbolo, topo = estado, topo da pilha
                        verificar = true;
                    }

                }

            }

            // pegar transiçoes para o novo estado
            if (verificar) {
                if (verificaCadeia.isEmpty() && automato.getPilha().isEmpty()) {
                    
                    ArrayList<Transicao> vazio = new ArrayList<Transicao>();
                    transicoesPossiveis = vazio;

                }else{
                    transicoesPossiveis = Transicao.getTransicao(automato.getTransicoes(), automato.getEstadoAtual());

                }

            } else {
                break;
            }

        }
        System.out.println("Automato finalizou no estado = " + estadoAtual);
        System.out.println();
        System.out.println("Numero de simbolos que restaram da cadeia: "+ verificaCadeia.size());
        System.out.println("Numero de simbolos na Pilha: "+ automato.getPilha().size());
        System.out.println();

        if (verificaCadeia.isEmpty()) {
            if (automato.getPilha().empty() && automato.getEstadoAtual().iseFinal()) {
                System.out.println("Cadeia aceita por pilha VAZIA e estado Final ");
            } else if (automato.getEstadoAtual().iseFinal()) {
                System.out.println("Cadeia aceita por estado Final ");
            }else if(automato.getPilha().empty()){
                System.out.println("Cadeia aceita por pilha VAZIA");
            }else if(!automato.getEstadoAtual().iseFinal()){
                System.out.println("Cadeia REJEITADA");  
            }
        }else if(automato.getEstadoAtual().iseFinal()){
            System.out.println("Cadeia aceita por estado Final ");

        }else{
            System.out.println("Cadeia REJEITADA");
        }
    }


    public Estado buscar(ArrayList<Estado> estado) {

        Estado retorno = null;

        for (Estado i : estado) {
            if (i.isEstInicial()) {
                retorno = i;
            }
        }

        return retorno;
    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Estado estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public ArrayList<String> getAlfabetoEntrada() {
        return alfabeto;
    }

    public void setAlfabetoEntrada(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<String> getAlfabetoPilha() {
        return alfabetoPilha;
    }

    public void setAlfabetoPilha(ArrayList<String> alfabetoPilha) {
        this.alfabetoPilha = alfabetoPilha;
    }

    public String getSimboloInicialPilha() {
        return simboloInitPilha;
    }

    public void setSimboloInicialPilha(String simboloInitPilha) {
        this.simboloInitPilha = simboloInitPilha;
    }

    public ArrayList<Estado> getConjuntoEstados() {
        return estadosACP;
    }

    public void setConjuntoEstados(ArrayList<Estado> estadosACP) {
        this.estadosACP = estadosACP;
    }

    public Stack getPilha() {
        return pilhaACP;
    }

    public void setPilha(Stack pilhaACP) {
        this.pilhaACP = pilhaACP;
    }

}
