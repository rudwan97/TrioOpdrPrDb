

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private String home = "Home";
    String overzicht1 = "Overzicht1";
    String overzicht2 = "Overzicht2";
    int extraWindowWidth = 100;

    public UserInterface() {
    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(300, 200));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {


        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel card1 = new JPanel() {

            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };

        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        BorderLayout layout2 = new BorderLayout();
        card1.setLayout(layout2);
        JLabel labelDown2 = new JLabel("Netflix Statistix");
        JLabel labelDown3 = new JLabel("Informatica <eerste leerjaar> <23IVT1A1> <Joey, Rudwan, Kevin> ");
        card1.add(labelDown2, BorderLayout.SOUTH);
        card1.add(labelDown3, BorderLayout.SOUTH);


        JPanel card2 = new JPanel();

        card2.add(new JLabel("Selecteer serie"));
        String[] programmaStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        JComboBox petList = new JComboBox(programmaStrings);
        petList.setSelectedIndex(4);



        TextArea text = new TextArea("Hier komen de gemiddeldes");
        text.setSize(50, 50);
        ImageIcon image = new ImageIcon("src/betflix.png");
        JLabel label = new JLabel(image);
        card2.add(label, BorderLayout.SOUTH);



        card2.add(petList);
        card2.add(text);


        JPanel card3 = new JPanel();

        BorderLayout layout = new BorderLayout();
        card3.setLayout(layout);

        JLabel labelTop = new JLabel("Dit moet boven aan staan");
        labelTop.setMinimumSize(new Dimension(100, 100));
        labelTop.setPreferredSize(new Dimension(300, 300));
        labelTop.setFont(new Font("Serif", Font.BOLD, 150));
        labelTop.setMaximumSize(new Dimension(500, 500));
        JLabel labelDown = new JLabel("Dit moet onderaanstaan");
        card3.add(labelTop, BorderLayout.NORTH);
        card3.add(labelDown, BorderLayout.SOUTH);


        tabbedPane.addTab(home, card1);
        tabbedPane.addTab(overzicht1, card2);
        tabbedPane.addTab(overzicht2, card3);

        container.add(tabbedPane);


    }


    public JFrame getFrame() {
        return frame;
    }
}
