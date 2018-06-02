package com.wxbus.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by g1154 on 2018/5/5.
 */
public class QRcodeUtil {

    /**
     *
     * @param url
     * @param response
     * @param imgSize
     */
    public void getTwoDimension(String url, HttpServletResponse response, int imgSize)throws IOException{
        if(url!=null&&!"".equals(url)){
            HashMap hints=new HashMap();//
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);

            ServletOutputStream stream=null;
            try {
                stream=response.getOutputStream();
                QRCodeWriter writer=new QRCodeWriter();
                BitMatrix m=writer.encode(url, BarcodeFormat.QR_CODE,imgSize,imgSize);
                MatrixToImageWriter.writeToStream(m,"png",stream);
            }catch (WriterException e){
                e.printStackTrace();
            }finally {
                if(stream!=null){
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

}
