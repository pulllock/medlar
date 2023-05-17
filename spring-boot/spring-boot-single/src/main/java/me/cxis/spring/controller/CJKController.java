package me.cxis.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cjk")
public class CJKController {

    /**
     * 𨫎府𡌶𥌓1234哈哈哈＝&&*%￥##○●★☆☉♀♂※¤╬の〆🔍🏃
     * @param cjk
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(@RequestParam String cjk) {
        System.out.println(cjk);
        System.out.println("cjk: " + cjk.length());
        cjk = filterUTF8mb4(cjk);
        System.out.println(cjk);
    }

    private static String filterUTF8mb4(String str) {
        // 基本多文种平面，第0平面，编码从U+0000至U+FFFF，mysql的utf8编码属于基本多文种平面，最多支持3个字节
        final int LAST_BMP = 0xFFFF;
        StringBuilder sb = new StringBuilder(str.length());
        for (int offset = 0; offset < str.length(); offset++) {
            int codePoint = str.codePointAt(offset);
            if (codePoint < LAST_BMP) {
                sb.appendCodePoint(codePoint);
            } else {
                String replaceStr = "*";
                int repCodePoint = replaceStr.codePointAt(0);
                sb.appendCodePoint(repCodePoint);
                offset++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "\uD862\uDECE府\uD844\uDF36\uD854\uDF131234哈哈哈＝*%￥##○●★☆☉♀♂※¤╬の〆\uD83D\uDD0D\uD83C\uDFC3";
        System.out.println(filterUTF8mb4(str));
    }
}
