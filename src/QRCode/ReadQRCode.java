package QRCode;

import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadQRCode {

    public static void readQRCode(String path) throws IOException {
        File file = new File(path);

        BufferedImage image = ImageIO.read(file);

        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        byte[] decode = codeDecoder.decode(new QRCodeImageImpl(image));//需要传入一个QRCodeImage的实现类
        String result = new String(decode, StandardCharsets.UTF_8);//字节数组转换成String

        System.out.println("二维码内容为：" + result);
    }

    public static void main(String[] args) {
        try {
            ReadQRCode.readQRCode("./QRCode.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
