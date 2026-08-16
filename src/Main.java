import java.util.*;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Scanner sc = new Scanner(System.in);


            System.out.println(
                "1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar"
            );
            int opcao = sc.nextInt();
            sc.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    Produto p = new Produto(nome, preco);
                    dao.inserir(p);
                    break;

                case 2:
                    for (Produto prod : dao.listar()) {
                        System.out.println(
                            prod.getId() + " - " + 
                            prod.getNome() + " - R$ " + 
                            prod.getPreco()
                        );
                    }
                    break;

                case 3:
                    System.out.print("ID do produto: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    nome = sc.nextLine();
                    System.out.print("Novo preço: ");
                    preco = sc.nextDouble();
                    p = new Produto(nome, preco);
                    p.setId(id);
                    dao.atualizar(p);
                    break;

                case 4:
                    System.out.print("ID do produto a deletar: ");
                    id = sc.nextInt();
                    dao.deletar(id);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
            sc.close();
    }
}
