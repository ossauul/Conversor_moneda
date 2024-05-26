package com.conversor.principal;

import com.conversor.conexiones.ConexionHTTP;
import com.conversor.modelos.Lista_Moneda;
import com.conversor.modelos.Moneda;
import com.conversor.modelos.MonedaAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("¿Ingresa el numero de la moneda que deseas consultar ?");
                System.out.println("1.Boliviano\n" +
                        "2.Dólar\n" +
                        "3.Euro\n" +
                        "4.Libra Esterlina\n" +
                        "5.Peso Argentino\n" +
                        "6.Peso Chileno\n" +
                        "7.Peso Colombiano\n" +
                        "8.Peso Mexicano\n" +
                        "9.Real Brasileño\n" +
                        "10.Mostrar historial\n" +
                        "11.Salir"
                );
                var busqueda = lectura.nextInt();
                Moneda moneda = new Moneda();
                if (busqueda == 11) {
                    break;
                }
                if (busqueda == 10) {
                    moneda.leer_historial();
                    lectura.nextLine();
                }
                if(busqueda>=1 && busqueda <=9 )
                {
                    String[][] monedas = moneda.getMonedas();
                    String codigo = monedas[busqueda - 1][0].toString();
                    menu_monedas(codigo);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingresa solo numeros (main)");
        } catch (Exception e) {
            System.out.println("Surgio un error, intenta de nuevo (main)" + e);
        }
    }

    public static void menu_monedas(String s) {
        try {
            Scanner lectura = new Scanner(System.in);
            switch (s) {
                case "ARS", "BOB", "BRL", "CLP", "COP", "EUR", "MXN", "USD", "GBP":
                    Moneda moneda = new Moneda();
                    String titulo = moneda.nombre_moneda(s);
                    System.out.println("Moneda " + titulo);
                    System.out.println("1.Conversion general \n2.Conversion moneda especifica");
                    int aux = lectura.nextInt();
                    if (aux == 1) {
                        conversion_unoAmuchos(s);
                    }
                    if (aux == 2) {
                        conversion_unoAuno(s);
                    }
                    break;
                default:
                    System.out.println("Opcion no valida, intenta de nuevo (switch menu)");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ingresa un numero (menu)");
        } catch (Exception e) {
            System.out.println("Ingresa una opcion valida (menu)");
        }
    }


    public static void conversion_unoAuno(String codigo1) {
        System.out.println("¿Ingresa el numero de la moneda que deseas convertir ?");
        System.out.println("1.Boliviano\n" +
                "2.Dólar\n" +
                "3.Euro\n" +
                "4.Libra Esterlina\n" +
                "5.Peso Argentino\n" +
                "6.Peso Chileno\n" +
                "7.Peso Colombiano\n" +
                "8.Peso Mexicano\n" +
                "9.Real Brasileño"
        );
        try {
            Scanner lectura = new Scanner(System.in);
            int aux = lectura.nextInt();
            lectura.nextLine();
            System.out.println("Ingresa la cantidad a convertir a [" + codigo1 + "]");
            double aux2 = lectura.nextDouble();
            Moneda moneda = new Moneda();
            String[][] monedas = moneda.getMonedas();
            String s = monedas[aux - 1][0].toString();
            String resultado = moneda.unoAuno(codigo1,s,aux2);
            System.out.println(resultado);
            moneda.guardar(resultado);
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un numero (1a1)");
        } catch (Exception e) {
            System.out.println("Valor no valido (1a1)");
        }

    }
    /*public static void conversion_unoAmuchos(String codigo4) {
        try {
            Scanner lectura = new Scanner(System.in);
            System.out.println("Ingresa la cantidad a convertir [" + codigo4 + "]");
            double aux3 = lectura.nextDouble();
            Moneda moneda = new Moneda();
            List<Moneda> conversiones = moneda.unoAmuchos(codigo4, aux3);
            List<String> salidas;
            for (Moneda moneda1 : conversiones) {
                String salida = aux3 + " [" +codigo4  + "] equivale a " + moneda1.getValor_convertido() + " [" + moneda1.getCodigo() + "]";
                System.out.println(salida);
                moneda.guardar(salida);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un numero (1am)");
        } catch (Exception e) {
            System.out.println("Valor no valido (1am)");
        }

    }*/

    public static void conversion_unoAmuchos(String codigo4) {
        try {
            Scanner lectura = new Scanner(System.in);
            System.out.println("Ingresa la cantidad a convertir [" + codigo4 + "]");
            double aux3 = lectura.nextDouble();
            Moneda moneda = new Moneda();
            List<Moneda> conversiones = moneda.unoAmuchos(codigo4, aux3);
            ArrayList<String> salidas = new ArrayList<String>();
            for (Moneda moneda1 : conversiones) {
                String salida = aux3 + " [" +codigo4  + "] equivale a " + moneda1.getValor_convertido() + " [" + moneda1.getCodigo() + "]";
                salidas.add(salida);
                System.out.println(salida);
            }
            moneda.guardar(salidas);

        } catch (NumberFormatException e) {
            System.out.println("Ingresa un numero (1am)");
        } catch (Exception e) {
            System.out.println("Valor no valido (1am)");
        }

    }


}



