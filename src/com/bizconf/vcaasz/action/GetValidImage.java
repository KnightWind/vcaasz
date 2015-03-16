package com.bizconf.vcaasz.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.service.ValidCodeService;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

/**
 * @author Chris Gao
 * @version Create Time：2010-11-5 下午05:22:31
 * 
 */
@ReqPath("valid/image")
public class GetValidImage {

	@Autowired
	ValidCodeService validCodeService;
	
    private static char[] alphas = new char[36];

    static {
        for (int i = 0; i < 10; i++) {
            alphas[i] = (char) ('0' + i);
        }

        /*for (int i = 0; i < 26; i++) {
            alphas[i + 10] = (char) ('A' + i);
        }*/

        for (int i = 0; i < 26; i++) {
            alphas[i + 10] = (char) ('a' + i);
        }
    }

    public Object doRequest(HttpServletRequest request, HttpServletResponse response, 
    		@CParam("type") String type, @CParam("random") String randomString)
            throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        int width = 75, height = 25;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(/*getRandColor(200, 250)*/new Color(249, 252, 124));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(/*getRandColor(160, 200)*/new Color(134, 150, 242));
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(30);
            int yl = random.nextInt(30);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(alphas.length);
            sb.append(alphas[index]);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random
                    .nextInt(110)));
            g.drawString(String.valueOf(alphas[index]), 15 * i + 3, 18);
        }
        g.dispose();

        validCodeService.recordValidCode(randomString, type, sb.toString());
        OutputStream toClient = response.getOutputStream();
        ImageIO.write(image, "jpeg", toClient);
        toClient.close();

        return null;
    }

    @SuppressWarnings("unused")
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);

    }
}
