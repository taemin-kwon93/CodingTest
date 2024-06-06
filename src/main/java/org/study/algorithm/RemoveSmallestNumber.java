package org.study.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 정수를 저장한 배열, arr 에서 가장 작은 수를 제거한 배열을 리턴하는 함수, solution을 완성해주세요. 단, 리턴하려는 배열이 빈 배열인 경우엔 배열에 -1을 채워 리턴하세요. 예를들어 arr이 [4,3,2,1]인 경우는 [4,3,2]를 리턴 하고, [10]면 [-1]을 리턴 합니다.
 */
public class RemoveSmallestNumber {
    public int[] solution(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int[] answer = new int[arr.length - 1];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != min) list.add(arr[j]);
        }

        for (int k = 0; k < answer.length; k++) {
            answer[k] = list.get(k);
        }


        return answer.length > 0 ? answer : new int[] {-1};
    }
}
