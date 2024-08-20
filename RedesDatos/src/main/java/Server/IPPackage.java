/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

/**
 *
 * @author icrio
 */
public class IPPackage {
     private String sourceIP;
        private String destinationIP;
        private String frameHeader;
        private byte[] data;

        public IPPackage(String sourceIP, String destinationIP, byte[] data) {
            this.sourceIP = sourceIP;
            this.destinationIP = destinationIP;
            this.data = data;
            this.frameHeader = "";
        }

        public String getSourceIP() {
            return sourceIP;
        }

        public String getDestinationIP() {
            return destinationIP;
        }

        public byte[] getData() {
            return data;
        }
        
        public String getFrameHeader() {
            return frameHeader;
        }

        public void setFrameHeader(String frameHeader) {
            this.frameHeader = frameHeader;
        }

        public void removeFrameHeader() {
            this.frameHeader = "";
        }
}

