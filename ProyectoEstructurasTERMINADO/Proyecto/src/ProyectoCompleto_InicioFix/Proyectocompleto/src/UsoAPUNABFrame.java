package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class UsoAPUNABFrame extends JFrame {

    public UsoAPUNABFrame() {
        setTitle("Uso de APUNAB");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de encabezado con logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(new Color(212, 31, 17));
        logoPanel.setPreferredSize(new Dimension(1000, 70));
        
        try {
            ImageIcon icon = new ImageIcon("resources/logo_unab.png");
            JLabel logo = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            logoPanel.add(logo);
        } catch (Exception e) {
            JLabel logoText = new JLabel("UNAB");
            logoText.setForeground(Color.WHITE);
            logoText.setFont(new Font("SansSerif", Font.BOLD, 24));
            logoPanel.add(logoText);
        }

        // Panel de título
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(212, 31, 17));
        titlePanel.setPreferredSize(new Dimension(1000, 50));
        
        JLabel title = new JLabel("Uso De APUNAB", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(title, BorderLayout.CENTER);

        // Combinar header panels
        JPanel headerContainer = new JPanel(new BorderLayout());
        headerContainer.add(logoPanel, BorderLayout.NORTH);
        headerContainer.add(titlePanel, BorderLayout.SOUTH);
        
        add(headerContainer, BorderLayout.NORTH);

        // Panel de gráficos
        JPanel graphsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        graphsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        graphsPanel.add(createBarChartPanel());
        graphsPanel.add(createPieChartPanel());

        add(graphsPanel, BorderLayout.CENTER);
        
        pack(); // Ajusta el tamaño automáticamente
        setVisible(true);
    }

    private JPanel createBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(12, "Apuestas", "Ganadas");
        dataset.addValue(5, "Apuestas", "Perdidas");
        dataset.addValue(8, "Apuestas", "Pendientes");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Resumen de apuestas",
                "Categoría",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        // Personalizar colores del gráfico de barras
        barChart.getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(212, 31, 17));

        return new ChartPanel(barChart);
    }

    private JPanel createPieChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Apuestas Ganadas", 48);
        dataset.setValue("Apuestas Perdidas", 20);
        dataset.setValue("Apuestas Pendientes", 32);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Distribución porcentual",
                dataset,
                true, true, false
        );

        // Personalizar colores del gráfico de pastel
        pieChart.getPlot().setBackgroundPaint(Color.WHITE);

        return new ChartPanel(pieChart);
    }

    // Método main para probar la clase
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new UsoAPUNABFrame();
        });
    }
}