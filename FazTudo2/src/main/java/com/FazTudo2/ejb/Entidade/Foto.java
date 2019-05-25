/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 *
 * @author ALUNO
 */
@Embeddable
@Access(AccessType.FIELD)

public class Foto implements Serializable {

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public void fileToBytes(File file){
        int tamanho = (int) file.length();
        byte[] file_bytes = new byte[tamanho];
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(file);
            inFile.read(file_bytes, 0, tamanho);
        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
        }
        this.foto = file_bytes;
        //System.err.println(file_bytes);
    }
    

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    
}
