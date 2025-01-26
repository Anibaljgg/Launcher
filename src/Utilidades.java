import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class Utilidades {
    // Función 1 - PanelInPanel
    public static void panelInPanel(JPanel contentPanel, JPanel instancePanel) {
        contentPanel.removeAll(); // Limpia el panel contenedor
        contentPanel.setLayout(new BorderLayout()); // Establece el layout
        contentPanel.add(instancePanel, BorderLayout.CENTER); // Añade el panel contenido
        contentPanel.revalidate(); // Revalida el panel
        contentPanel.repaint(); // Repinta el panel
    }

    // Función 2 y 3 - SetImageLabel
    public static void setImageLabel(JLabel label, String root, boolean keepProportions) {
        try {
            ImageIcon image = new ImageIcon(root);
            Image img = image.getImage();

            if (keepProportions) {
                Image newImg = img.getScaledInstance(-1, label.getHeight(),
                        Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImg));
            } else {
                Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(),
                        Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImg));
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    public static void setImageLabel(JLabel label, String root, Dimension dimension,
                                     boolean keepProportions) {
        try {
            ImageIcon image = new ImageIcon(root);
            Image img = image.getImage();

            if (keepProportions) {
                double ratio = (double) img.getWidth(null) / img.getHeight(null);
                int newWidth = (int) (dimension.height * ratio);
                Image newImg = img.getScaledInstance(newWidth, dimension.height,
                        Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImg));
            } else {
                Image newImg = img.getScaledInstance(dimension.width, dimension.height,
                        Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImg));
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    // Función 4 - CreateStringList
    public static ArrayList<String> createStringList(String root, String name,
                                                     String filetype, int size) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add(root + name + i + "." + filetype);
        }
        return list;
    }

    // Función 5 - ReadTextFile
    public static String readTextFile(String root) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(root))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return content.toString();
    }

    // Función 6 - Async
    public static void async(long waitTime, Runnable asyncFunction) {
        new Thread(() -> {
            try {
                Thread.sleep(waitTime);
                asyncFunction.run();
            } catch (InterruptedException e) {
                System.out.println("Error en la ejecución asíncrona: " + e.getMessage());
            }
        }).start();
    }
}
