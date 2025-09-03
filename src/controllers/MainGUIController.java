package controllers;

import models.Jogador;
import dao.JogadorDAO;

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
    private static final DefaultTableModel TABLE_MODEL = new DefaultTableModel(new String[]{"ID", "Nome", "N° Da Camisa"}, 0) {
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

        tblJogadores.setModel(TABLE_MODEL);
        tblJogadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblJogadores.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblJogadores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        tblJogadores.setRowSelectionAllowed(true);
        tblJogadores.setCellSelectionEnabled(false);

        adicionarEventos();
        atualizarDadosTabela();
    }

    private void atualizarDadosTabela() throws SQLException {
        TABLE_MODEL.setRowCount(0);
        List<Jogador> jogadores = JOGADOR_DAO.obterTudo();

        for (Jogador jogador : jogadores) {
            TABLE_MODEL.addRow(new Object[]{
                    jogador.getId(),
                    jogador.getNome(),
                    jogador.getNumCamisa()
            });
        }
    }

    private void liberarUD() {
        if (!txtId.getText().trim().isEmpty()) {
            btnAtualizar.setEnabled(true);
            btnExcluir.setEnabled(true);
        } else {
            btnAtualizar.setEnabled(false);
            btnExcluir.setEnabled(false);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtNumCamisa.setText("");
        liberarUD();
    }

    private void adicionarEventos() {
        tblJogadores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tblJogadores.getSelectedRow();
                if (linhaSelecionada != -1) {
                    txtId.setText(tblJogadores.getValueAt(linhaSelecionada, 0).toString());
                    txtNome.setText(tblJogadores.getValueAt(linhaSelecionada, 1).toString());
                    txtNumCamisa.setText(tblJogadores.getValueAt(linhaSelecionada, 2).toString());
                }

                liberarUD();
            }
        });

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

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    excluir();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private boolean camposInvalidos() {
        String nome = txtNome.getText().trim();
        String numCamisa = txtNumCamisa.getText().trim();

        if (nome.isEmpty() && numCamisa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos nome e nº da camisa vazios.");
            return true;
        }

        try {
            Integer.parseInt(numCamisa);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "O número da camisa deve ser um número válido.");
            return true;
        }

        return false;
    }

    private void adicionar() throws SQLException {
        if (camposInvalidos()) {
            return;
        }

        String nome = txtNome.getText().trim();
        int numCamisa = Integer.parseInt(txtNumCamisa.getText().trim());
        Jogador jogador = new Jogador(nome, numCamisa);

        JOGADOR_DAO.inserir(jogador);
        atualizarDadosTabela();
        limparCampos();

        JOptionPane.showMessageDialog(null, "Jogador adicionado com sucesso!");
    }

    private void atualizar() throws SQLException {
        if (camposInvalidos()) {
            return;
        }

        int id = Integer.parseInt(txtId.getText().trim());
        String nome = txtNome.getText().trim();
        int numCamisa = Integer.parseInt(txtNumCamisa.getText().trim());
        Jogador jogador = new Jogador(id, nome, numCamisa);

        JOGADOR_DAO.atualizar(jogador);
        atualizarDadosTabela();
        limparCampos();

        JOptionPane.showMessageDialog(null, "Jogador atualizado com sucesso!");
    }

    private void excluir() throws SQLException {
        int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText().trim());
            Jogador jogador = new Jogador(id);

            JOGADOR_DAO.excluir(jogador);
            atualizarDadosTabela();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Jogador excluído com sucesso!");
        }
    }
}
