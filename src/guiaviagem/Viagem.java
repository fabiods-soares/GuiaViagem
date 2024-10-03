package guiaviagem;

import java.util.HashMap;
import java.util.Map;


public class Viagem {
    
    private String partida;
    private String destino;
    private int kml;
    private double rsl;

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getKml() {
        return kml;
    }

    public void setKml(int kml) {
        this.kml = kml;
    }

    public double getRsl() {
        return rsl;
    }

    public void setRsl(double rsl) {
        this.rsl = rsl;
    }

       public Viagem() {
        this.partida = partida;
        this.destino = destino;
        this.kml = kml;
        this.rsl = rsl;
    }
    
    // Mapa das cidades e suas cordenadas, sugestão do chatGPTeta
    private static final Map<String, double[]> cidades = new HashMap<>();
    
    static {
        cidades.put("São Paulo", new double[]{-23.5505, -46.6333});
        cidades.put("Rio de Janeiro", new double[]{-22.9068, -43.1729});
        cidades.put("Belo Horizonte", new double[]{-19.9191, -43.9378});
        cidades.put("Brasilia", new double[]{-15.7992, -47.8879});
        cidades.put("Fortaleza", new double[]{-3.7312, -38.5361});
        cidades.put("Belém", new double[]{-1.4563, -48.4945});
        cidades.put("Manaus", new double[]{-3.1197, -60.0175});
        cidades.put("Cuiabá", new double[]{-15.5923, -56.1049});
        cidades.put("Aracaju", new double[]{-10.9261, -37.0670});
        cidades.put("Palmas", new double[]{-10.2492, -48.3204});
       
    }
    
    public double DistanciaAB() {
        double[] coordsPartida = cidades.get(this.getPartida());
        double[] coordsDestino = cidades.get(this.getDestino());

        if (coordsPartida == null || coordsDestino == null) {
            throw new IllegalArgumentException("Cidade não encontrada.");
        }

        return calcularDistancia(coordsPartida[0], coordsPartida[1], coordsDestino[0], coordsDestino[1]);
    }
    //Metodo que calcula distancia
    private double calcularDistancia(double latitudePartida, double longitudePartida, double latitudeDestino, double longitudeDestino) {
        final int R = 6371; // Raio da Terra em quilômetros
        double latDistance = Math.toRadians(latitudeDestino - latitudePartida);
        double lonDistance = Math.toRadians(longitudeDestino - longitudePartida);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(latitudePartida)) * Math.cos(Math.toRadians(latitudeDestino)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
       
    //transforma e retorna a distancia em Quilometros
        return R * c; 
    }
    
    //Calculo de litros gastos
    public double LitrosGastos() {
        double distancia = DistanciaAB();
        return distancia / this.getKml(); 
    }
    
    //Calculo de custo da viagem
     public double CalcularGasto() {
        double litrosGastos = LitrosGastos();
        return litrosGastos * this.getRsl(); 
    }
     
     public static Map<String, double[]> getCidades() {
    return cidades;
}
    
}
