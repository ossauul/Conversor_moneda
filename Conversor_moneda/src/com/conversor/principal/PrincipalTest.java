package com.conversor.principal;

import com.conversor.conexiones.ConexionHTTP;
import com.conversor.modelos.Lista_Moneda;
import com.conversor.modelos.Moneda;
import com.conversor.modelos.MonedaAPI;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrincipalTest {
    public static void main(String[] args) throws IOException {
        Moneda moneda = new Moneda();
        String s = moneda.unoAuno("USD", "MXN", 20);
        //moneda.guardar_json(s);
    }

}
