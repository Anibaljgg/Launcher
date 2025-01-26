import javax.swing.*;
import java.awt.*;

public class LauncherMain extends JFrame {
    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;

    public LauncherMain() {
        initComponents();
        setFullScreenMode();
    }

    private void initComponents() {
        // Configuración básica de la ventana
        setTitle("Universae Launcher");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evita el cierre
        setLayout(new BorderLayout());

        // Inicializar paneles principales
        createHeaderPanel();
        createSidebarPanel();
        createContentPanel();

        // Añadir paneles al frame
        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setFullScreenMode() {
        // Configurar pantalla completa
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true); // Elimina la barra de título
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(51, 51, 51));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Añadir los 14 escudos
        for (int i = 1; i <= 14; i++) {
            JLabel escudo = createEscudoLabel("path/to/escudo" + i + ".png");
            headerPanel.add(escudo);
        }
    }

    private JLabel createEscudoLabel(String imagePath) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(80, 80));

        // Usar la clase Utilidades que creamos antes para la imagen
        Utilidades.setImageLabel(label, imagePath, true);

        // Añadir efectos hover
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setSize(new Dimension(90, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setSize(new Dimension(80, 80));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showHomeView();
            }
        });

        return label;
    }

    private void createSidebarPanel() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(40, 40, 40));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));

        // Añadir logo Universae
        JLabel logo = new JLabel();
        Utilidades.setImageLabel(logo, "path/to/logo.png", true);

        // Añadir información de redes sociales
        JLabel socialInfo = new JLabel("<html>Síguenos en:<br>Twitter<br>Facebook<br>Instagram</html>");
        socialInfo.setForeground(Color.WHITE);

        sidebarPanel.add(logo);
        sidebarPanel.add(socialInfo);
    }

    private void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(30, 30, 30));
        showHomeView(); // Mostrar vista inicial
    }

    private void showHomeView() {
        contentPanel.removeAll();
        contentPanel.setLayout(new GridLayout(2, 3, 20, 20));

        // Añadir 6 previews de simuladores
        for (int i = 1; i <= 6; i++) {
            JPanel previewPanel = createPreviewPanel("Simulador " + i);
            contentPanel.add(previewPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showGameView(String simulatorTitle) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());

        // Crear carrusel de imágenes
        JPanel carouselPanel = createCarouselPanel();

        // Crear panel de información
        JPanel infoPanel = createInfoPanel(simulatorTitle);

        assert carouselPanel != null;
        contentPanel.add(carouselPanel, BorderLayout.CENTER);
        assert infoPanel != null;
        contentPanel.add(infoPanel, BorderLayout.SOUTH);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createInfoPanel(String simulatorTitle) {
        return null;
    }

    private JPanel createCarouselPanel() {
        return null;
    }

    private JPanel createPreviewPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        JLabel titleLabel = new JLabel(title);

        // Configurar preview
        Utilidades.setImageLabel(imageLabel, "path/to/preview.png", true);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(titleLabel, BorderLayout.SOUTH);

        // Añadir efectos hover y click
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel.setBorder(null);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showGameView(title);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LauncherMain().setVisible(true);
        });
    }
}
