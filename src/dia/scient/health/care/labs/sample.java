package dia.scient.health.care.labs;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class sample extends JFrame {
    private ZoomPanel zoomPanel;
    private double scale = 1.0;

    public sample() {
        setTitle("Zoom In/Out Example");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        zoomPanel = new ZoomPanel();
        JScrollPane scrollPane = new JScrollPane(zoomPanel);

        JPanel buttonPanel = new JPanel();
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");

        zoomInButton.addActionListener(e -> {
            scale *= 1.1; // Increase scale by 10%
            zoomPanel.setScale(scale);
        });

        zoomOutButton.addActionListener(e -> {
            scale /= 1.1; // Decrease scale by 10%
            zoomPanel.setScale(scale);
        });

        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static class ZoomPanel extends JPanel {
        private double scale = 1.0;

        public void setScale(double scale) {
            this.scale = scale;
            repaint(); // Redraw the panel with new scale
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            g2d.setTransform(at);

            // Draw something (Example: Rectangle)
            g2d.setColor(Color.BLUE);
            g2d.fillRect(50, 50, 200, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension((int) (500 * scale), (int) (400 * scale));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(sample::new);
    }
}
