import controller.Controller;
import view.View;

import javax.swing.*;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        JFrame obj = new JFrame();
        View view = new View("background1.jpg");
        obj.getContentPane().add(view);
        obj.setBounds(100, 100, 440, 535);
        Controller controller = new Controller(view);
        obj.setTitle("Polynomial Calculator");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
