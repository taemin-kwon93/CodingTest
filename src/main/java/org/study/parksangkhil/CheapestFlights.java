package org.study.parksangkhil;

import java.util.Arrays;

public class CheapestFlights {
    // 벨만-포드 알고리즘 (Bellman-Ford Algorithm) 변형 버전
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 거리 배열 초기화
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // K번 경유 = 최대 K+1 개의 간선을 거칠 수 있음
        for (int i = 0; i <= k; i++) {
            // 이번 라운드에서 갱신된 내용을 저장할 임시 배열 (deep copy)
            // 이걸 안 쓰면, 이번 턴에 갱신된 값이 즉시 다음 노드 계산에 반영되어 K 제한을 뚫어버림
            int[] tempDist = dist.clone();

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];

                // 시작점에서 u까지 도달 불가능하면 스킵
                if (dist[u] == Integer.MAX_VALUE) continue;

                // 완화(Relaxation): 더 싸게 갈 수 있으면 갱신
                if (dist[u] + price < tempDist[v]) {
                    tempDist[v] = dist[u] + price;
                }
            }
            // 이번 라운드 결과를 원본에 반영
            dist = tempDist;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
