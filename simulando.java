import java.util.ArrayList;
import java.util.Scanner;

public class simulando {

    static Scanner in = new Scanner(System.in);

    // atributos do automato com pilha
    static ArrayList<String> alfabeto = new ArrayList<String>();
    static ArrayList<String> alfabetoPilha = new ArrayList<String>();
    static String simboloInicialPilha;
    static String epsilon;
    static ArrayList<Estado> conjuntoEstados = new ArrayList<Estado>();
    static ArrayList<Transicao> transicoes = new ArrayList<Transicao>();

    public static void escreverEstados() {

        System.out.println("ESCREVER OS ESTADOS DO AUTÔMATO");

        int i = 1;
        int condicao = 1;

        while (condicao == 1) {
            System.out.println();
            System.out.print("Digite o " + i + "º estado: ");
            String nome = in.next();
            in.nextLine();
            Estado estado = new Estado(nome);

            conjuntoEstados.add(estado);

            System.out.println("--DIGITE 1 SE QUISER INSERIR OUTRO ESTADO--");
            condicao = in.nextInt();

            i += 1;
        }

        System.out.println("============================================================");

    }

    public static void definirEstados() {

        System.out.println("QUAL É O ESTADO INICIAL ?");
        String eInicial = in.next();
        in.nextLine();
        int contIni = 0;

        for (Estado i : conjuntoEstados) {
            if (eInicial.equals(i.getNome())) {
                conjuntoEstados.get(contIni).setEstInicial(true);
            }
            contIni++;
        }

        // ------------------------------------------------------------

        int condicao2 = 1;
        while (condicao2 == 1) {

            int contFin = 0;

            System.out.println("QUAL É O ESTADO FINAL? (Se não tiver estado final digite 0)");
            String eFinal = in.next();
            in.nextLine();
            for (Estado i : conjuntoEstados) {
                if (eFinal.equals(i.getNome())) {
                    conjuntoEstados.get(contFin).seteFinal(true);
                }
                contFin++;
            }

            System.out.println("--DIGITE 1 SE QUISER INSERIR OUTRO FINAL--");
            condicao2 = in.nextInt();
        }

    }

    public static void escreverTransicao() {

        System.out.println();
        System.out.println("ESCREVER AS TRANSIÇÕES DO AUTÔMATO");

        int i = 1;
        int condicao = 1;

        while (condicao == 1) {

            System.out.println("Digite o " + i + "º transição no formato (q0 a Z q1 aZ): ");
            String trasicao = in.nextLine();

            String[] trnasit = trasicao.split(" ");
            Transicao trans = new Transicao(trnasit[0], trnasit[1], trnasit[2], trnasit[3], trnasit[4]);
            transicoes.add(trans);

            System.out.println("--DIGITE 1 SE QUISER INSERIR OUTRA TRANSIÇÃO--");
            condicao = in.nextInt();
            in.nextLine();
            i += 1;
        }

        System.out.print("============================================================");

    }

    public static void escreverAlfAutomato() {

        System.out.println();
        System.out.println();

        System.out.println("ESCREVER O ALFABETO DO AUTÔMATO");

        System.out.print("Digite o 1º símbolo: ");
        String simbolo1 = in.next();
        in.nextLine();

        alfabeto.add(simbolo1);

        System.out.print("Digite o 2º símbolo: ");
        String simbolo2 = in.next();
        in.nextLine();
        alfabeto.add(simbolo2);

        System.out.println("============================================================");
    }

    // metodo para analizar alfabeto aceito e cadeia digitada
    public static boolean pertenceAlfabeto(String letra, ArrayList<String> alfabeto) {

        for (String simbolo : alfabeto) {
            if (simbolo.equals(letra)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("                  Simulador de ACP\n");
        System.out.println("============================================================");

        escreverEstados();
        definirEstados();
        escreverAlfAutomato();
        escreverTransicao();

        // ------------------------------------------------------------------

        System.out.print("\nSimbolo inicial da pilha: ");
        simboloInicialPilha = in.next();
        in.nextLine();

        // adicionando o simbolo inicial ao alfabeto da pilha
        alfabetoPilha.add(alfabeto.get(0));
        alfabetoPilha.add(alfabeto.get(1));
        alfabetoPilha.add(simboloInicialPilha);

        // ------------------------------------------------------------------

        System.out.println();

        // ------------------------------------------------------------------
        System.out.println("============================================================");
        System.out.print("\nSimbolo para epsilon: ");
        epsilon = in.next();
        in.nextLine();

        // loop para testar várias cadeias
        // ------------------------------------------------------------------
        System.out.println("============================================================");
        int condicao = 1;
        while (condicao == 1) {
            System.out.print("Digite a cadeia: ");
            String cadeia = in.next();
            in.nextLine();

            boolean verifica = false;
            for (int i = 0; i < cadeia.length(); i++) {
                verifica = pertenceAlfabeto(String.valueOf(cadeia.charAt(i)), alfabeto);
            }

            // verificando se a cadeia digitada pertence ao alfabeto
            if (verifica) {
                System.out.println("Cadeia não e aceita pelo alfabeto");
            } else {

                System.out.println("Transição da cadeia: " + cadeia);
                System.out.println();

                // ------------------------------------------------------------------
                Automato automato = new Automato(alfabeto, alfabetoPilha, simboloInicialPilha, conjuntoEstados,
                        transicoes, epsilon);
                automato.verificarCadeia(cadeia, automato);
            }

            System.out.println();
            System.out.println();
            System.out.println("============================================================");
            System.out.println("Deseja testar outra cadeia se sim digite 1 se não digite 0");
            condicao = in.nextInt();

            System.out.println();
            System.out.println();
            System.out.println();

        }

    }

}
