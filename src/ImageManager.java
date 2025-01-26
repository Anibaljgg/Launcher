import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class ImageManager {
    private JPanel carouselPanel;
    private ArrayList<String> imageList;
    private int currentImageIndex;
    private Timer timer;
    private JLabel imageLabel;
    private JButton prevButton;
    private JButton nextButton;

    public ImageManager() {
        imageList = new ArrayList<>();
        currentImageIndex = 0;
        initComponents();
    }
    private JPanel createCarouselPanel() {
        ImageManager imageManager = new ImageManager();

        // Crear lista de imágenes para el carrusel
        ArrayList<String> images = new ArrayList<>();
        images.addAll(Collections.singleton("images/carrousel"));


        // O usar el método de Utilidades
        // ArrayList<String> images = Utilidades.createStringList(
        //     "images/carousel/", "slide", "jpg", 5);

        imageManager.setImages(images);
        imageManager.setCarouselSize(new Dimension(800, 400));
        imageManager.startAutoPlay(5); // Cambiar imagen cada 5 segundos

        return imageManager.getCarouselPanel();
    }

    private void initComponents() {
        carouselPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Crear botones de navegación
        prevButton = createNavigationButton("←");
        nextButton = createNavigationButton("→");

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(prevButton, BorderLayout.WEST);
        buttonPanel.add(nextButton, BorderLayout.EAST);

        // Añadir componentes al panel principal
        carouselPanel.add(imageLabel, BorderLayout.CENTER);
        carouselPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar eventos de los botones
        prevButton.addActionListener(e -> showPreviousImage());
        nextButton.addActionListener(e -> showNextImage());
    }


    private JButton createNavigationButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0, 100));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        // Efectos hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 100));
            }
        });

        return button;
    }

    public void setImages(ArrayList<String> images) {
        this.imageList = images;
        if (!images.isEmpty()) {
            showImage(0);
        }
    }

    public void startAutoPlay(int intervalSeconds) {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> showNextImage());
            }
        }, intervalSeconds * 1000, intervalSeconds * 1000);
    }

    public void stopAutoPlay() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void showNextImage() {
        if (imageList.isEmpty()) return;
        currentImageIndex = (currentImageIndex + 1) % imageList.size();
        showImage(currentImageIndex);
    }

    private void showPreviousImage() {
        if (imageList.isEmpty()) return;
        currentImageIndex = (currentImageIndex - 1 + imageList.size()) % imageList.size();
        showImage(currentImageIndex);
    }

    private void showImage(int index) {
        if (index >= 0 && index < imageList.size()) {
            String imagePath = imageList.get(index);
            Utilidades.setImageLabel(imageLabel, imagePath,
                    new Dimension(carouselPanel.getWidth(), carouselPanel.getHeight()), true);
        }
    }

    public JPanel getCarouselPanel() {
        return carouselPanel;
    }

    // Método para cambiar el tamaño del carrusel
    public void setCarouselSize(Dimension size) {
        carouselPanel.setPreferredSize(size);
        carouselPanel.setSize(size);
        showImage(currentImageIndex); // Actualizar la imagen con el nuevo tamaño
    }
}
