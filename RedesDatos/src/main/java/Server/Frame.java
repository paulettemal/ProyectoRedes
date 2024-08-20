/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Layers.InternetLayer;
import Layers.TransportLayer;
import Layers.NetworkAccessLayer;
import Layers.AppLayer;

/**
 *
 * @author icrio
 */
public class Frame {
    private AppLayer capaApp;
    private TransportLayer capaTransporte;
    private InternetLayer capaInternet;
    private NetworkAccessLayer capaAccesoRed;
    private String data;

    public Frame(AppLayer capaApp, TransportLayer capaTransporte, InternetLayer capaInternet, NetworkAccessLayer capaAccesoRed, String data) {
        this.capaApp = capaApp;
        this.capaTransporte = capaTransporte;
        this.capaInternet = capaInternet;
        this.capaAccesoRed = capaAccesoRed;
        this.data = data;
    }
    
    
    
    
    
}
