package org.study.algorithm;

/**
 * 문자열로 변환
 * 정수 n이 주어질 때, n을 문자열로 변환하여 return하도록 solution 함수를 완성해주세요.
 */
public class ConverToString {
    public static void main(String[] args) {
        ConverToString solve = new ConverToString();
        String answer = solve.solution(1234);
        System.out.println(answer);
    }

    public String solution(int n) {
        return n + "";
    }
}