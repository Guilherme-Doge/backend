package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        while (true) {
            try {
                System.out.print("""
                        1 - Menu de Contatos
                        2 - Menu de Grupos
                        
                        Digite a opção desejada:\s """);
                int opcao = SC.nextInt();
                switch (opcao) {
                    case 1 : {
                        menuContatos();
                        break;
                    }
                    case 2 : {
                        //1menuGrupos();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void menuContatos() {
        try {
            System.out.print("""
                    1 - Cadastrar Contato
                    2 - Ver Contato
                    3 - Atualizar Contato
                    4 - Deletar Contato
                    
                    Digite a opção desejada:\s """);
            int opcao = SC.nextInt();
            SC.nextLine();
            switch (opcao) {
                case 1: {
                    cadastrarContato();
                    break;
                }
                case 2: {
                    menuVerContato();
                    break;
                }
                case 3: {
                    menuAtualizarContato();
                    break;
                }
                case 4 : {
                    excluirContato();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cadastrarContato() throws Exception {
        System.out.print("Digite o nome do contato: ");
        String nome = SC.nextLine();
        System.out.print("Digite o número do contato: ");
        String numero = SC.nextLine();

        ContatoDAO dao = new ContatoDAO();
        try {
            dao.salvar(new Contato(nome, numero));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menuVerContato() throws Exception {
        System.out.println("""
                1 - Ver todos os contatos
                2 - Ver contatos pelo nome
                3 - Ver vários contatos
                \n
                Escolha uma opção:\s """);
        int opcao = SC.nextInt();
        SC.nextLine();
        switch (opcao) {
            case 1 : {
                verContato();
                break;
            }
            case 2 : {
                verContatoNome();
                break;
            }
            case 3 : {
                verVariosContatos();
                break;
            }
        }
    }

    public static void verContato() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        try {
            List<Contato> contatos = dao.verContato();
            System.out.println("ID | NOME | NUMERO");
            for (int i = 0; i < contatos.size(); i++) {
                int id = contatos.get(i).getId();
                String nome = contatos.get(i).getNome();
                String numero = contatos.get(i).getNumero();

                System.out.println(id + " | " + nome + " | " + numero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verContatoNome() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        System.out.print("Insira o nome do contato:\s");
        String pedacoNome = SC.next();
        dao.verContato(pedacoNome);
        List<Contato> contatos = dao.verContato();
        System.out.println("ID | NOME | NUMERO");
        for (int i = 0; i < contatos.size(); i++) {
            int id = contatos.get(i).getId();
            String nome = contatos.get(i).getNome();
            String numero = contatos.get(i).getNumero();
            System.out.println(id + " | " + nome + " | " + numero);
        }
    }

    public static void verVariosContatos() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        ArrayList<Integer> ids = new ArrayList<>();
        int resposta = 0;
        do {
            System.out.print("Insira um id:\s");
            int id = SC.nextInt();
            ids.add(id);
            System.out.println("""
                    Deseja adicionar mais um id?
                    0 - Sim
                    1 - Não""");
            resposta = SC.nextInt();
        } while (resposta != 1);
        List<Contato> contatos = dao.verVariosContatos(ids);
        System.out.println("ID | NOME | NUMERO");
        for (int i = 0; i < contatos.size(); i++) {
            int id = contatos.get(i).getId();
            String nome = contatos.get(i).getNome();
            String numero = contatos.get(i).getNumero();
            System.out.println(id + " | " + nome + " | " + numero);
        }
    }

    public static void menuAtualizarContato() throws Exception {
        Contato contato = selecionarContato();

        if (contato.getId() == 0) {
            System.out.println("Contato não encontrado");
            return;
        }

        System.out.println("""
                1 - Atualizar Nome
                2 - Atualizar Numero
                3 - Atualizar Nome e Numero
                
                Escolha uma opção:\s """);
        int opcao = SC.nextInt();
        SC.nextLine();
        switch (opcao) {
            case 1 : {
                atualizarNomeContato(contato);
                break;
            }
            case 2 : {
                atualizarNumeroContato(contato);
                break;
            }
            case 3 : {
                atualizarContatoCompleto(contato);
                break;
            }
        }
    }

    public static void atualizarNomeContato(Contato contato) throws Exception {
        System.out.print("Insira o novo nome do contato: ");
        String nome = SC.nextLine();

        ContatoDAO dao = new ContatoDAO();
        try {
            dao.atualizarNomeContato(nome, contato);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarNumeroContato(Contato contato) throws Exception {
        System.out.print("Insira o novo numero do contato: ");
        String numero = SC.nextLine();

        ContatoDAO dao = new ContatoDAO();
        try {
            dao.atualizarNumeroContato(numero, contato);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarContatoCompleto(Contato contato) throws Exception {
        System.out.print("Insira o novo nome do contato: ");
        String nome = SC.nextLine();
        System.out.print("Insira o novo numero do contato: ");
        String numero = SC.nextLine();

        ContatoDAO dao = new ContatoDAO();
        try {
            dao.atualizarContatoCompleto(nome, numero, contato);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Contato selecionarContato() throws Exception {
        ContatoDAO dao = new ContatoDAO();
        try {
            verContato();

            System.out.print("Digite o ID do contato: ");
            int id = SC.nextInt();
            Contato contato = dao.selecionarContato(id);
            return contato;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Contato(0, "Null", "Null");
        }
    }

    public static void excluirContato() throws SQLException {
        ContatoDAO dao = new ContatoDAO();
        try {
            verContato();
            System.out.println("Digite o ID do contato:\s ");
            int id = SC.nextInt();
            Contato contato = dao.selecionarContato(id);
            dao.excluirContato(contato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verGruposContato() throws SQLException {
        ContatoDAO dao = new ContatoDAO();
        try {
            Contato contato = selecionarContato();
            List<Grupo> grupos = dao.verGrupos(contato);
            System.out.println("ID | NOME");
            for (int i = 0; i < grupos.size(); i++) {
                int id = grupos.get(i).getId();
                String nome = grupos.get(i).getNome();
                System.out.println(id + " | " + nome);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}