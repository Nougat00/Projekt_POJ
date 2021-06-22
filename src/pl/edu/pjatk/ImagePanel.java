package pl.edu.pjatk;

import javax.swing.*;
import java.awt.*;

    class ImagePanel extends JPanel {

        private Image img;
        protected int w;
        protected int h;

        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            this.h=img.getHeight(null);
            this.w=img.getWidth(null);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }

