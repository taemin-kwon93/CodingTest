package org.study.algorithm;

import java.util.Scanner;

/**
 * 문자열 str이 주어집니다.
 * 문자열을 시계방향으로 90도 돌려서 아래 입출력 예와 같이 출력하는 코드를 작성해 보세요
 */

public class RotateString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        for (char c : s.toCharArray()) System.out.println(c);
    }
}
