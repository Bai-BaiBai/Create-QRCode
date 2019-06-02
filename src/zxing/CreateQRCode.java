package zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 使用zxing生成二维码
 */
public class CreateQRCode {

    //二维码的宽度和高度
    private int width = 300;
    private int height = 300;

    private String format = "png"; //二维码保存的格式
    private String content = "https://github.com/Bai-BaiBai/Create-QRCode";//二维码的内容

    public CreateQRCode(String content) {
        this.content = content;
    }
    public CreateQRCode(){}

    public void createQRCode(){

        //定义参数，通过map, key需要设置为EncodeHintType
        HashMap<EncodeHintType, Object> hints = new HashMap<>();

        //所需要的参数在 EncodeHintType枚举类中
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");//编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//定义纠错等级 L<M<Q<H 一般使用M
        hints.put(EncodeHintType.MARGIN, 3);//设置图片的空白边距，默认是5

        try {
            //                                                      内容，  二维码类型           宽度    长度   参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            //矩阵写入图片类,可以选择写入到路径 or 输出流中，这里为了直接看到，使用不推荐的写入文件方式
            File file = new File("./QRCode.png");
            MatrixToImageWriter.writeToFile(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
