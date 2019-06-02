# Java生成和解析二维码

矩阵式二维码(字符压缩)：在矩阵相应元素位置上，用点(方点、圆点或其它形状)从出现表示二进制“1”，点的不出现表示二进制“0”

最普遍的矩阵二维码类型是 QR Code：专利公开，支持中文

**优点**：
- 高密度编码，信息容量大
- 编码范围广
- 容错能力强(损坏一部分仍然可读)
- 译码可靠性高
- 可引入加密措施
- 成文低，易制作，持久耐用

***

主要有三种方式生成二维码：
- java程序中：zxing、qrcode
- Web网站中：jquery.qrcode.js（https://github.com/jeromeetienne/jquery-qrcode）

*** 

**zxing方式**：
- 二维码的设置参数需要存放在一个Map<EncodeHintType, Object>中
- 使用MultiFormatWriter.encode(content,BarcodeFormat,w,h,map)方法压缩成为一个矩阵(bitMatrix)
- 使用MatrixToImageWriter.writeTo* 将矩阵写入文件或流中

**qrcode方式**：
- 将内容转化成二进制数组，再使用Qrcode.calQrcode转成boolean型的二维矩阵
- 使用java的GUI工具Graphics2D根据二维boolean数组填充图片。
- 需要注意的细节有：Qrcode中set设置二维码参数；
- 二维码长宽是和 Qrcode.version 有关系的，固定公式67 + 12 * (version-1);
- Graphics2D画图时要加偏移量pixoff(随便一个整数)：gs.fillRect(j*3 + pixoff, i*3 + pixoff, 3,3);
- 如果有汉字，在String转成二进制数组时使用UTF-8编码：content.getBytes(StandardCharsets.UTF_8)

**jquery方式**：引用一个js文件(jquery.qrcode.js),前提是jquery.js必须存在。[使用方法](https://github.com/jeromeetienne/jquery-qrcode)

***

解析二维码：
- [zxing方式](https://github.com/Bai-BaiBai/Create-QRCode/blob/master/src/zxing/ReadQRCode.java)
- [qrcode方式](https://github.com/Bai-BaiBai/Create-QRCode/blob/master/src/QRCode/ReadQRCode.java)