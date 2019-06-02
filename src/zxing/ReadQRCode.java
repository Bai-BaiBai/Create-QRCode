package zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 使用zxing读取二维码
 */
public class ReadQRCode {

    public static void readQRCode() throws IOException, NotFoundException {

        MultiFormatReader  reader = new MultiFormatReader();//需要使用reader的decode方法读取二维码信息
        File file = new File("./QRCode.png");//二维码文件位置

        BufferedImage image = ImageIO.read(file);//将二维码按图片读取
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));//转换成二进制map

        // 解析参数
        HashMap<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        // decode需要 BinaryBitmap作为必要参数，可选参数为对该二维码的Decode参数
        // 使用zxing下的result接收
        Result result = reader.decode(bitmap, hints);

        System.out.println("解析结果：" + result.toString());
        System.out.println("二维码格式类型：" + result.getBarcodeFormat());
        System.out.println("二维码文本内容：" + result.getText());
    }
}
