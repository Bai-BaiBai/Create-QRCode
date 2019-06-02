package QRCode;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * QRCode是使用java GUI的画图工具来实现的
 */
public class CreateQRCode {

    public static void createQRCode(String content) throws IOException {

        content = "https://github.com/Bai-BaiBai/Create-QRCode";

        Qrcode x = new Qrcode();
        /**
         * 纠错等级分为
         * level L : 最大 7% 的错误能够被纠正；
         * level M : 最大 15% 的错误能够被纠正；
         * level Q : 最大 25% 的错误能够被纠正；
         * level H : 最大 30% 的错误能够被纠正；
         */
        x.setQrcodeErrorCorrect('L');
        x.setQrcodeEncodeMode('B');// N代表数字，A代表a-Z, B代表其它
        int version = 7;
        x.setQrcodeVersion(version);//版本号 1-40

        //画出的二维码长宽是和 version 有关系的，固定公式
        int width = 67 + 12 * (version-1);
        int height = 67 + 12 * (version-1);

        //创建一个缓冲区图片 , 参数为宽、高、类型
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //绘图
        Graphics2D gs = image.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.orange);
        gs.clearRect(0,0, width, height);

        //偏移量
        int pixoff = 2;

        byte[] data = content.getBytes(StandardCharsets.UTF_8);//如果没有汉字可以不写该参数

        //字符串长度小于120，可以根据内容长度调节该值
        if (data.length >0 && data.length <120){
            //将content的二进制数组转成二维矩阵，使用boolean型表示
            boolean[][] s = x.calQrcode(data);

            //注意i,j的顺序
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    //如果该位置有值(true) 使用gs填充，不加偏移量可能会解析出错
                    if (s[j][i]){
                        gs.fillRect(j*3 + pixoff, i*3 + pixoff, 3,3);
                    }
                }
            }
        }

        gs.dispose();
        image.flush();

        ImageIO.write(image, "png", new File("./QRCode.png"));
    }

    public static void main(String[] args) {
        try {
            CreateQRCode.createQRCode("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
