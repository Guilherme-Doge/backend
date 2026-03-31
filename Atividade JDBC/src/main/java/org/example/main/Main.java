package org.example.main;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("""
                        ===================================
                        || 1 - Cadastrar cliente         ||
                        || 2 - Buscar cliente por ID     ||
                        || 3 - Listar clientes           ||
                        || 4 - Atualizar cliente         ||
                        || 5 - Deletar cliente           ||
                        || 6 - Cadastrar produto         ||
                        || 7 - Buscar produto por ID     ||
                        || 8 - Listar produtos           ||
                        || 9 - Atualizar produto         ||
                        || 10 - Deletar produto          ||
                        || 11 - Criar pedido             ||
                        || 12 - Listar pedidos           ||
                        ||                               ||
                        || 0 - Sair                      ||
                        ===================================""");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}