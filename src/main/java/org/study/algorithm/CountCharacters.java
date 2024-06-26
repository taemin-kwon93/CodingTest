package org.study.algorithm;

/**
 * 알파벳 대소문자로만 이루어진 문자열 my_string이 주어질 때, my_string에서 'A'의 개수,
 * my_string에서 'B'의 개수,..., my_string에서 'Z'의 개수, my_string에서 'a'의 개수,
 * my_string에서 'b'의 개수,..., my_string에서 'z'의 개수를 순서대로 담은 길이
 * 52의 정수 배열을 return 하는 solution 함수를 작성해 주세요.
 */
public class CountCharacters {
    public int[] solution(String s) {
        int[] answer = new int[52];
        for (char c : s.toCharArray()) {
            if ((int) c < 91) {
                answer[(int) c - 65] += 1;
            } else {
                answer[(int) c - 71] += 1;
            }

        }
        return answer;
    }
}
