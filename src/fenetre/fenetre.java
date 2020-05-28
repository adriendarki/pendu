package fenetre;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class fenetre {
    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }
    public static class Fenetre extends JFrame {

        private char[] lettreTab = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't',
                'u', 'v', 'w', 'x', 'y', 'z'};

        public Fenetre(){
            this.setTitle("le jeu du pendu !");
            this.setSize(1200, 800);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(new Panneau());


            this.setVisible(true);
        }
    }
    public static class Panneau extends JPanel {
        public void paintComponent(Graphics g){
            Font font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.red);
            g.drawString("le jeu du pendu !", 10, 20);
        }
    }
}

