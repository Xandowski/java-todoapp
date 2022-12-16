package todoapp.view;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
  public static void main(String[] args) {
    JLabel headerLabel = new JLabel("App Tarefas");
    headerLabel.setHorizontalTextPosition(JLabel.LEFT);
    headerLabel.setVerticalTextPosition(JLabel.TOP);
    headerLabel.setForeground(new Color(0x0CB0FF));
    headerLabel.setFont(new Font("Inter", Font.BOLD, 36));

    JPanel headerPanel = new JPanel();
    headerPanel.setBackground(new java.awt.Color(0x0D202B));
    headerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    headerPanel.setSize(1440, 200);
    headerPanel.setVisible(true);
    headerPanel.add(headerLabel);

    JFrame frame = new JFrame("Todo App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1440, 1024);
    frame.setVisible(true);

    frame.add(headerPanel);
  }
}
