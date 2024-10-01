/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package guiaviagem;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.Map;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno Matutino
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox<String> comboPartida;
    @FXML
    private ComboBox<String> comboDestino;
    @FXML
    private Label lblDistancia;
    @FXML
    private Label lblLitros;
    @FXML
    private Label lblCusto;
    @FXML
    private Label lblAB;    
    @FXML
    private TextField txtKML; 
    @FXML
    private TextField txtRSL; 

    
    
    private DecimalFormat df = new DecimalFormat("#.00");
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String partida = comboPartida.getValue();
        String destino = comboDestino.getValue();

        if (partida != null && destino != null) {
            Viagem viagem = new Viagem();
            viagem.setPartida(partida);
            viagem.setDestino(destino);

            
           try {
                int kml = Integer.parseInt(txtKML.getText());
                double rsl = Double.parseDouble(txtRSL.getText());

                viagem.setKml(kml);
                viagem.setRsl(rsl);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira valores validos para KM/L e para R$/L ex: 5.10");
                return;
            }

            double distancia = viagem.DistanciaAB();
            double litros = viagem.LitrosGastos();
            double custo = viagem.CalcularGasto();
            
            

            lblDistancia.setText(df.format(distancia) + " km");
            lblLitros.setText(df.format(litros) + " L");
            lblCusto.setText("R$ "+ df.format(custo));
            
            lblAB.setText("Saindo de " + partida + " até " + destino);
                    
            comboPartida.getSelectionModel().clearSelection();
            comboDestino.getSelectionModel().clearSelection();
            txtKML.clear();
            txtRSL.clear();
     
            
        } else {
            JOptionPane.showMessageDialog(null, "Insira uma cidade de Partida e o Destino");
        }
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (String cidade : Viagem.getCidades().keySet()) {
            comboPartida.getItems().add(cidade);
            comboDestino.getItems().add(cidade);
        }

    }    
    
}
