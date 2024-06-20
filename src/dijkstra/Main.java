package dijkstra;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pi
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new Panel());
        window.setTitle("Pilar Mora");
        
        int option = JOptionPane.showConfirmDialog(window, "Deseas iniciar el juego?", "Algoritmia", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
        System.out.println(option);
        window.pack();
        window.setLocationRelativeTo(null);
        if(option == 0) {
            window.setVisible(true);
            JOptionPane.showConfirmDialog(window, "Presione 1 para iniciar el juego \no presionar enter para autobusqueda", "Atencion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        else
            System.exit(option);
    }
    
}
