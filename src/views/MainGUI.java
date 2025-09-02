package views;

import javax.swing.*;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel panelForm;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblDataNascimento;
    private JTextField txtDataNascimento;
    private JLabel lblNumCamisa;
    private JTextField txtNumCamisa;
    private JPanel panelActions;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnAtualizarLista;
    private JTable tblJogadores;
    private JPanel panelContent;
    private JScrollPane panelTable;

    public MainGUI() {
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LineUp Lab - Gerenciamento de Jogadores");
        setContentPane(mainPanel);
        setVisible(true);
    }
}
