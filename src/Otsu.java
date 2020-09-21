import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Otsu{
    public static void main(String args[]){ new Otsu("ZJXXt.png"); }

    public Otsu(String filename){
        BufferedImage Imagee=null;
        try{
            File imgFile = new File(filename);Imagee = javax.imageio.ImageIO.read(imgFile);
        } catch (IOException ioE){ }
        int width = Imagee.getWidth();int height = Imagee.getHeight();

        Raster ras = Imagee.getData();
        DataBuffer buf = ras.getDataBuffer();
        DataBufferByte byteBuffer = (DataBufferByte) buf;
        byte[] srcData = byteBuffer.getData(0);
        byte[] dstData = new byte[srcData.length];
        OtsuThresholder thresholder = new OtsuThresholder();thresholder.doThreshold(srcData, dstData);
        Grey srcFrame = new Grey(width, height, srcData);
        Grey dstFrame = new Grey(width, height, dstData);
        JPanel panel = new JPanel(new BorderLayout(25, 25));
        panel.setBorder(new javax.swing.border.EmptyBorder(70, 39, 70, 39));
        panel.add(srcFrame, BorderLayout.WEST);panel.add(dstFrame, BorderLayout.EAST);

        JFrame frame = new JFrame("Statistic Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);frame.pack();frame.setVisible(true); }}









