package cn.guoke.utils;

import java.util.Scanner;

public class CheckStrUtils {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String password = sc.nextLine();
            if (verifyPassword(password)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
        sc.close();
    }

    /**
     * 验证密码合法性
     * 
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password) {
        // 长度超过6位
        if (password == null || password.length() <= 6) {
            return false;
        }

        if (checkCharTypes(password) && checkRepeatSubstring(password)) {
            return true;
        }
        return false;
    }

    /**
     * 包括大小写字母、数字、其它符号,以上四种至少三种
     * 
     * @param password
     * @return
     */
    public static boolean checkCharTypes(String password) {
        int upperCase = 0, lowerCase = 0, digit = 0, other = 0;

        for (Character ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCase = 1;
            } else if (Character.isLowerCase(ch)) {
                lowerCase = 1;
            } else if (Character.isDigit(ch)) {
                digit = 1;
            } else {
                other = 1;
            }
        }


        if (upperCase + lowerCase + digit + other >= 3) {
            return true;
        }
        return false;
    }

    /**
     * 不能有相同长度超2的子串重复
     * 
     * @param password
     * @return
     */
    public static boolean checkRepeatSubstring(String password) {
        for (int i = 0; i < password.length() - 3; i++) {
            String s = password.substring(i, i + 3);
            String tempStr = password.substring(i + 3, password.length());
            if (tempStr.contains(s)) {
                return false;
            }
        }
        return true;
    }

}
