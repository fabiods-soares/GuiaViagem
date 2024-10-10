/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package guiaviagem;

import com.sun.deploy.uitoolkit.ToolkitStore;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Aluno Matutino
 */
public class FXMLController implements Initializable {
    
    
    @FXML
    private Label lblCusto1;
    @FXML
    private TextField txtRSL1;
    @FXML
    private TextField txtKML1;
    @FXML
    private Label lblLitros1;
    @FXML
    private Label lblDistancia1;
    @FXML
    private Label lblResult1;
    @FXML
    private ComboBox<String> comboPartida1;
    @FXML
    private ComboBox<String> comboDestino1;
    @FXML
    private ComboBox<String> comboDestino2;

    /**
     * Initializes the controller class.
     */
    
    //Formata os label para que tragam apenas duas casas após o ponto
    private DecimalFormat df = new DecimalFormat("#.00");
    
    @FXML
    void Calcular1(ActionEvent event) {
        
        String partida = comboPartida1.getValue();
        String destino = comboDestino1.getValue();
        String destino2 = comboDestino2.getValue();

        if (partida != null && destino != null && destino2 != null 
                && !partida.equals(destino) && !partida.equals(destino2) 
                && !destino.equals(destino2)) {
            
            Viagem viagem = new Viagem();
            viagem.setPartida(partida);
            viagem.setDestino(destino);
            viagem.setDestino2(destino2); 

            try {
                int kml = Integer.parseInt(txtKML1.getText());
                double rsl = Double.parseDouble(txtRSL1.getText());

                viagem.setKml(kml);
                viagem.setRsl(rsl);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira valores válidos para KM/L e para R$/L, ex: 5.10");
                return;
            }

            double distancia = viagem.DistanciaAB1();
            double litros = viagem.LitrosGastos();
            double custo = viagem.CalcularGasto();

            lblDistancia1.setText(df.format(distancia) + " km");
            lblLitros1.setText(df.format(litros) + " L");
            lblCusto1.setText("R$ " + df.format(custo));
            
            lblResult1.setText("Saindo de " + partida + " parada em " + destino + " até " + destino2);
                    
            comboPartida1.getSelectionModel().clearSelection();
            comboDestino1.getSelectionModel().clearSelection();
            comboDestino2.getSelectionModel().clearSelection();
            txtKML1.clear();
            txtRSL1.clear();     
            
        } else {
            JOptionPane.showMessageDialog(null, "Insira uma cidade de Partida e duas cidades de Destino (não podem ser iguais)");
        }

    }
    
    @FXML
    void fechaTela2(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Fecha a janela
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        for (String cidade : Viagem.getCidades().keySet()) {
            comboPartida1.getItems().add(cidade);
            comboDestino1.getItems().add(cidade);
            comboDestino2.getItems().add(cidade);
        }

    }    
    
}
