package com.conversor.modelos;

import com.conversor.conexiones.ConexionHTTP;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Moneda {
    private double valor_convertido;
    private String codigo;
    private String nombre;
    String[][] monedas = {
            {"BOB", "Boliviano"},
            {"USD", "Dólar"},
            {"EUR", "Euro"},
            {"GBP", "Libra esterlina"},
            {"ARS", "Peso Argentino"},
            {"CLP", "Peso Chileno"},
            {"COP", "Peso Colombiano"},
            {"MXN", "Peso Mexicano"},
            {"BRL", "Real Brasileño"}
    };

    public Moneda() {
    }

    public Moneda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Moneda(MonedaAPI MonedaAPI) {
        this.valor_convertido = MonedaAPI.conversion_rate();
        this.codigo = MonedaAPI.target_code();

    }

    public Moneda(double valor_convertido, String codigo) {
        this.valor_convertido = valor_convertido;
        this.codigo = codigo;
    }

    public double getValor_convertido() {
        return valor_convertido;
    }

    public void setValor_convertido(int valor_convertido) {
        this.valor_convertido = valor_convertido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String abreviacion) {
        this.codigo = abreviacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[][] getMonedas() {
        return monedas;
    }

    public void setMonedas(String[][] monedas) {
        this.monedas = monedas;
    }

     public double conversion ( double  valor1, double valor2 )
     {
         valor_convertido = valor1 * valor2;
         return valor_convertido;
     }

    public String unoAuno(String codigo1, String codigo2, double monto) {
        String salida =" ";
        try {
            String direccion = "https://v6.exchangerate-api.com/v6/0f49ed402868b5aa01cff7de/pair/" + codigo1 + "/" + codigo2;
            ConexionHTTP conexion = new ConexionHTTP();
            String resultado = conexion.conexion(direccion);
            Gson gson = new Gson();
            MonedaAPI response = gson.fromJson(resultado, MonedaAPI.class);
            System.out.println("desde moneda" + response);
            Moneda moneda1 = new Moneda(response);
            double conversion = conversion(monto, moneda1.getValor_convertido());
            salida = monto + " [" + moneda1.getCodigo() + "] equivale a " + conversion + " [" + codigo1 + "]";
            return salida;
        }catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        return salida;
    }

    public List<Moneda> unoAmuchos(String codigo1, double monto) {
        List<Moneda> conversiones = new ArrayList<>();;
        try {
            //Moneda moneda = new Moneda();
            String direccion = "https://v6.exchangerate-api.com/v6/0f49ed402868b5aa01cff7de/latest/" + codigo1;
            ConexionHTTP conexion = new ConexionHTTP();
            String s = conexion.conexion(direccion);
            Gson gson = new Gson();
            Lista_Moneda response = gson.fromJson(s, Lista_Moneda.class);
            Map<String, Double> conversion_rates = response.conversion_rates;
            for (Map.Entry<String, Double> entry : conversion_rates.entrySet()) {
                String code = entry.getKey();
                double rate = entry.getValue() * monto;
                Moneda mon = new Moneda(rate, code);
                conversiones.add(mon);
            }
            return conversiones;
        }catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        return conversiones;
    }
     public String nombre_moneda(String s)
     {
         String titulo = s;
         for(int i = 0; i < monedas.length; i++) {
             for (int j = 0; j < monedas[i].length; j++) {
                 if (monedas[i][j].equals(s)) {
                     titulo = monedas[i][1].toString();
                 }
             }
         }
         return titulo;
     }
     public void leer_historial()
     {
         try (FileReader fr = new FileReader("C:/Users/ossau/IdeaProjects/Conversor_moneda/conversiones.txt")) {
             BufferedReader br = new BufferedReader(fr);
             String linea;
             while((linea=br.readLine())!=null)
                 System.out.println(linea);
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }
    public void guardar(List<Moneda> array) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        FileWriter escritura = new FileWriter("conversiones.txt", true );
        escritura.write("\r\n["+array.toString() + "\nFecha de conversion " + dtf.format(now) +"]");
        escritura.close();
        System.out.println("Conversion guardada correctamente");
    }
    public void guardar(ArrayList<String> array) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        FileWriter escritura = new FileWriter("conversiones.txt", true );
        escritura.write("\r\n["+array.toString() + "\nFecha de conversion " + dtf.format(now) +"]");
        escritura.close();
        System.out.println("Conversion guardada correctamente");
    }
    public void guardar(String s) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        FileWriter escritura = new FileWriter("conversiones.txt",true);
        escritura.write("\r\n["+s + "\nFecha de conversion " + dtf.format(now) + "]");
        escritura.close();
        System.out.println("Conversion guardada correctamente");
    }
    @Override
    public String toString() {
        return "Moneda: [" + codigo +
                "] equivale a " + valor_convertido;

    }

}
