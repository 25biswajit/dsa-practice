package com.arrays.easy;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://memorynotfound.com/wp-content/uploads/java-duke.png");
        URLConnection openConnection = url.openConnection();
        boolean check = true;

        try {
            openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            openConnection.connect();
        } catch (Exception e) {
            System.out.println("Couldn't create a connection to the link, please recheck the link.");
            check = false;
            e.printStackTrace();
        }
        if (check) {
            BufferedImage img = null;
            try {
                InputStream in = new BufferedInputStream(openConnection.getInputStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();
                img = ImageIO.read(new ByteArrayInputStream(response));
            } catch (Exception e) {
                System.out.println(" couldn't read an image from this link.");
                e.printStackTrace();
            }
            System.out.println("Found :" + img);
        }
    }
}
