package hospitalmanager.interfaces;

import hospitalmanager.dominio.PlanoDeSaude;
import hospitalmanager.interfaces.ModelosTabela.TabelaPlanos;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MenuPlanos extends JFrame {
    public MenuPlanos(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        JButton botaoAdicionar = getJButton(principal);
        setBackground(Color.GRAY);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Planos");
        titulo.setVisible(true);
        TabelaPlanos modeloPlanos = new TabelaPlanos(principal.getSistema().getPlanos());
        JTable tabela = new JTable(modeloPlanos);
        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);

        DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.getColumnModel().getColumn(2).setHeaderRenderer(renderizadorCabecalho);
        tabela.getColumnModel().getColumn(3).setHeaderRenderer(renderizadorCabecalho);
        Dimension dim = tabela.getTableHeader().getPreferredSize();
        FontMetrics metrics = tabela.getTableHeader().getFontMetrics(tabela.getTableHeader().getFont());
        int alturaDeUmaLinha = metrics.getHeight();
        dim.height = (alturaDeUmaLinha * 3) + 4;
        tabela.getTableHeader().setMinimumSize(dim);
    }
    private static JButton getJButton(MenuInicial principal) {
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);

        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        botaoAdicionar.addActionListener(_ -> {
            JTextField campoNome = new JTextField();
            JTextField campoCodigo = new JTextField();
            JTextField campoDescInter = new JTextField();
            JTextField campoDescCons = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

            panel.add(new JLabel("Codigo:"));
            panel.add(campoCodigo);
            panel.add(new JLabel("Nome:"));
            panel.add(campoNome);
            panel.add(new JLabel("Desconto Internações (%):"));
            panel.add(campoDescInter);
            panel.add(new JLabel("Desconto Consultas (%):"));
            panel.add(campoDescCons);

            int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Plano de Saúde",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                String codigo = campoCodigo.getText();
                String nome = campoNome.getText();
                Double descInter = Double.parseDouble(campoDescInter.getText());
                Double descCons = Double.parseDouble(campoDescCons.getText());
                PlanoDeSaude plano = new PlanoDeSaude(codigo,nome,descInter,descCons);
                principal.getSistema().addPlano(plano);
            } else {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
    TableCellRenderer renderizadorCabecalho = new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea areaTexto = new JTextArea(value.toString());
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            areaTexto.setOpaque(true);
            areaTexto.setFont(table.getTableHeader().getFont());
            areaTexto.setForeground(table.getTableHeader().getForeground());
            areaTexto.setBackground(table.getTableHeader().getBackground());
            areaTexto.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            areaTexto.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
            areaTexto.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
            return areaTexto;
        }
    };
}
