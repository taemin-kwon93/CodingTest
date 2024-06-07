package org.study.algorithm;

/**
 * 문자열 myString과 pat이 주어집니다. myString에서 pat이 등장하는 횟수를 return 하는 solution 함수를 완성해 주세요.
 */
public class CountStringOccurrences {
    public int solution(String myString, String pat) {
        int counter = 0;
        for (int i = 0; i <= myString.length() - pat.length(); i++) {
            if (myString.substring(i, i + (pat.length())).contains(pat)) counter++;
        }

        return counter;
    }
}
