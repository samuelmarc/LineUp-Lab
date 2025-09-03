package controllers;

import models.Jogador;
import models.dao.JogadorDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainGUIController extends JFrame {
    private JPanel mainPanel;
    private JPanel panelForm;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblNome;
    private JTextField txtNome;
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
    private static final DefaultTableModel tblModel = new DefaultTableModel(new String[]{"ID", "Nome", "N° Da Camisa"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private static final JogadorDAO JOGADOR_DAO = new JogadorDAO();

    public MainGUIController() throws SQLException {
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LineUp Lab - Gerenciamento de Jogadores");
        setContentPane(mainPanel);
        setVisible(true);

        tblJogadores.setModel(tblModel);
        tblJogadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblJogadores.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblJogadores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        tblJogadores.setRowSelectionAllowed(true);
        tblJogadores.setCellSelectionEnabled(false);

        adicionarEventos();
        atualizarDadosTabela();
    }

    private void atualizarDadosTabela() throws SQLException {
        tblModel.setRowCount(0);
        List<Jogador> jogadores = JOGADOR_DAO.obterTudo();

        for (Jogador jogador : jogadores) {
            tblModel.addRow(new Object[]{
                    jogador.getId(),
                    jogador.getNome(),
                    jogador.getNumCamisa()
            });
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtNumCamisa.setText("");
    }

    private void adicionarEventos() {
        btnAtualizarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizarDadosTabela();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        tblJogadores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tblJogadores.getSelectedRow();
                if (linhaSelecionada != -1) {
                    txtId.setText(tblJogadores.getValueAt(linhaSelecionada, 0).toString());
                    txtNome.setText(tblJogadores.getValueAt(linhaSelecionada, 1).toString());
                    txtNumCamisa.setText(tblJogadores.getValueAt(linhaSelecionada, 2).toString());
                }
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void adicionar() throws SQLException {
        if (!txtNome.getText().isEmpty() && !txtNumCamisa.getText().isEmpty() ) {
            Jogador jogador = new Jogador(txtNome.getText(), Integer.parseInt(txtNumCamisa.getText()));
            JOGADOR_DAO.inserir(jogador);
            atualizarDadosTabela();
        } else {
            JOptionPane.showMessageDialog(null,"Campos invalidos porenchaos");
        }
    }

    private void excluir() {
        
    }
}
