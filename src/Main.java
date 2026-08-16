import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;       
import javax.swing.JTextField;   
import javax.swing.JButton;      
import javax.swing.JTextArea;     
import javax.swing.JScrollPane;   


//EXPLICAÇÃO DA MUDANÇA: 
// O código antigo se baseava em um switch case para tomar suas ações, porém para usar os botões como bse e o programa não encerrar assim que uma ação é realizada;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();

        JFrame janela = new JFrame("Meu CRUD Visual");
        JPanel painel = new JPanel();
        painel.setLayout(null); 
        janela.setContentPane(painel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 450); 
        janela.setLocationRelativeTo(null); 

        JLabel idLabel = new JLabel("ID do Produto:");
        idLabel.setBounds(20, 20, 120, 25); 
        painel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(150, 20, 200, 25);
        painel.add(idField);

        JLabel nomeLabel = new JLabel("Nome do Produto:");
        nomeLabel.setBounds(20, 60, 120, 25); 
        painel.add(nomeLabel);

        JTextField nomeField = new JTextField();
        nomeField.setBounds(150, 60, 200, 25);
        painel.add(nomeField);

        JLabel precoLabel = new JLabel("Preço do Produto:");
        precoLabel.setBounds(20, 100, 120, 25); 
        painel.add(precoLabel);

        JTextField precoField = new JTextField();
        precoField.setBounds(150, 100, 200, 25);
        painel.add(precoField);

        JButton inserirBtn = new JButton("Inserir");
        inserirBtn.setBounds(20, 140, 100, 30);
        painel.add(inserirBtn);

        JButton listarBtn = new JButton("Listar");
        listarBtn.setBounds(130, 140, 100, 30);
        painel.add(listarBtn);

        JButton atualizarBtn = new JButton("Atualizar");
        atualizarBtn.setBounds(240, 140, 100, 30);
        painel.add(atualizarBtn);

        JButton deletarBtn = new JButton("Deletar");
        deletarBtn.setBounds(350, 140, 100, 30);
        painel.add(deletarBtn);

        JTextArea resultadoArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(resultadoArea);
        scroll.setBounds(20, 190, 430, 180);
        painel.add(scroll);
        
        inserirBtn.addActionListener((ActionEvent e) -> {  //ActionEvent para "guardar a resposta em uma variável e fazer oq está em {}";
            String nome = nomeField.getText();
            double preco = Double.parseDouble(precoField.getText());  //Usa o parse para converter o digitado pelo usuário de String p Double;
            Produto p = new Produto(nome, preco);
            dao.inserir(p);  //"chama" o objeto dao e usa um de seus métodos(inserir), passando os parâmetros;
            resultadoArea.setText("Produto inserido com sucesso!");
            nomeField.setText("");     //limpa campos após apertar botões;
            precoField.setText("");
        });

        listarBtn.addActionListener((ActionEvent e) -> {
            resultadoArea.setText("Lista de Produtos:\n\n");
            for (Produto prod : dao.listar()) {    //usa um for-each juntamente com o método listar para percorrer o BD e trazer tudo que têm cadastrado lá, co ajuda de getters;
                resultadoArea.append(prod.getId() + " - " + prod.getNome() + " - R$ " + prod.getPreco() + "\n");
            }
        });

        atualizarBtn.addActionListener((ActionEvent e) -> {  //esse addActionListener é como se fosse adcionado um ouvinte a esse botão em relação às ações dentro das chaves;
            int id = Integer.parseInt(idField.getText());
            String nome = nomeField.getText();
            double preco = Double.parseDouble(precoField.getText());
            Produto p = new Produto(nome, preco);
            p.setId(id);
            dao.atualizar(p);
            resultadoArea.setText("Produto atualizado com sucesso!");
            idField.setText("");
            nomeField.setText("");
            precoField.setText("");
        });

        deletarBtn.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(idField.getText());
            dao.deletar(id);
            resultadoArea.setText("Produto deletado com sucesso!");
            idField.setText("");
        });

        janela.setVisible(true);  //"confirma que a janela será mostrada"
    }
}
