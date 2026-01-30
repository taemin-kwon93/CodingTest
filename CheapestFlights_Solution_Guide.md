# Cheapest Flights Within K Stops - Complete Solution Guide

## Problem Statement

**LeetCode 787: Cheapest Flights Within K Stops**

Given:
- `n` cities (numbered 0 to n-1)
- `flights[i] = [from, to, price]` (directed edges)
- `src` (starting city)
- `dst` (destination city)
- `k` (maximum number of stops allowed)

**Goal:** Find the cheapest price from `src` to `dst` with at most `k` stops. Return `-1` if no such route exists.

**Constraints:**
- 1 ≤ n ≤ 100
- 0 ≤ flights.length ≤ (n * (n - 1) / 2)
- 0 ≤ src, dst, from, to < n
- 1 ≤ price ≤ 10,000
- 0 ≤ k < n

---

## Key Insights

### 1. Why Not Standard Dijkstra?
- **Dijkstra assumption:** Once we reach a node with minimum cost, we never revisit it
- **This problem:** We might need to revisit a node via different paths with different stop counts
- **Constraint:** We must track both cost AND stop count

### 2. Stop Count vs Edge Count
- **"At most K stops"** means K intermediate cities
- Example: `0 → 1 → 2 → 3` has 2 stops (cities 1 and 2) but 3 edges
- So K stops = K+1 edges maximum

### 3. Optimal Substructure
- If we can reach city X with cost C in S stops, we can reach its neighbors in S+1 stops
- BUT: A more expensive path to X might lead to a cheaper overall path to destination

---

## Solution 1: BFS with Level-Order Traversal

### Core Idea
- Process nodes **level-by-level** where each level represents one stop
- Track minimum cost to reach each city
- Only add to queue if we find a cheaper route

### Algorithm Steps

```
1. Build adjacency list from flights
2. Initialize minCost[n] = INFINITY for all cities
3. Start BFS from src with cost=0
4. For each level (stop):
   a. Process all nodes at current level
   b. For each neighbor:
      - Calculate new cost
      - If new cost < minCost[neighbor]:
        * Update minCost[neighbor]
        * Add to queue for next level
5. Return minCost[dst] or -1 if unreachable
```

### Time & Space Complexity
- **Time:** O(E × K) where E = number of flights
  - At each stop level, we might process all edges
  - We do this for K+1 levels
- **Space:** O(N + E)
  - O(N) for minCost array
  - O(E) for adjacency list
  - O(N) for queue (at most N cities in queue)

### When to Use BFS
✅ Graph is sparse (few flights)  
✅ Want to stop early if destination is reached  
✅ Intuitive level-by-level processing

---

## Solution 2: Bellman-Ford Variation

### Core Idea
- Relax all edges exactly K+1 times
- Use temporary array to ensure we only use paths with exactly `i` edges in iteration `i`
- Simpler and more predictable than BFS

### Algorithm Steps

```
1. Initialize prices[n] = INFINITY
2. Set prices[src] = 0
3. For i = 0 to K:
   a. Create temp copy of prices
   b. For each flight [from, to, price]:
      - If prices[from] is reachable:
        * temp[to] = min(temp[to], prices[from] + price)
   c. Replace prices with temp
4. Return prices[dst] or -1 if unreachable
```

### Why We Need `temp` Array
Without `temp`, we might use updated values from the same iteration, which means using more than `i` edges in iteration `i`.

**Example:**
```
Iteration 1:
- Without temp: prices[1] = 100, then immediately use this to update prices[2]
  → This uses 2 edges in one iteration! ❌
- With temp: We only use prices from previous iteration
  → Correctly ensures exactly 1 edge per iteration ✅
```

### Time & Space Complexity
- **Time:** O(E × K)
  - K+1 iterations
  - Each iteration processes all E flights
- **Space:** O(N)
  - Two arrays of size N (prices and temp)

### When to Use Bellman-Ford
✅ Want simple, readable code  
✅ Easy to explain in interviews  
✅ Graph is dense (many flights)  
✅ More predictable behavior

---

## Comparison: BFS vs Bellman-Ford

| Aspect | BFS | Bellman-Ford |
|--------|-----|--------------|
| **Code Complexity** | More complex (queue, level tracking) | Simpler (nested loops) |
| **Interview Friendliness** | Medium | High |
| **Debugging** | Harder (queue state) | Easier (array snapshots) |
| **Early Termination** | Possible (if dst reached) | Not easily done |
| **Memory** | O(N + E) | O(N) |
| **Best For** | Sparse graphs | Dense graphs or interviews |

**Recommendation for interviews:** Use Bellman-Ford unless explicitly asked for BFS.

---

## Common Pitfalls & Edge Cases

### 1. Off-by-One Error
❌ **Wrong:** Loop `i < k` (allows only k-1 stops)  
✅ **Correct:** Loop `i <= k` (allows k stops = k+1 edges)

### 2. Integer Overflow
❌ **Wrong:**
```java
if (prices[from] < Integer.MAX_VALUE) {
    temp[to] = prices[from] + price; // Can overflow!
}
```
✅ **Correct:**
```java
if (prices[from] != Integer.MAX_VALUE) {
    temp[to] = Math.min(temp[to], prices[from] + price);
}
```

### 3. Revisiting Nodes
❌ **Wrong:** Mark nodes as visited (like standard BFS)  
✅ **Correct:** Allow revisiting if we find cheaper path

### 4. Edge Case: src == dst
❌ **Wrong:** Return minCost[dst] (will be INFINITY)  
✅ **Correct:** Check at start and return 0

### 5. Using Updated Values in Same Iteration
❌ **Wrong:** Update prices array directly  
✅ **Correct:** Use temp array copy (Bellman-Ford)

---

## Test Cases Coverage

### Basic Functionality
```java
// Example 1: Cheaper path with more stops
flights = [[0,1,100], [1,2,100], [0,2,500]]
src = 0, dst = 2, k = 1
Expected: 200 (path: 0→1→2)

// Example 2: Direct flight is only option
src = 0, dst = 2, k = 0
Expected: 500 (direct: 0→2)
```

### Edge Cases
```java
// No path exists
flights = [[0,1,100], [1,2,100]]
src = 0, dst = 3, k = 5
Expected: -1

// Source equals destination
src = 0, dst = 0, k = 0
Expected: 0

// Zero stops (direct flight only)
flights = [[0,1,100], [1,2,100], [0,2,500]]
src = 0, dst = 2, k = 0
Expected: 500
```

### Tricky Cases
```java
// Cycle in graph
flights = [[0,1,100], [1,2,100], [2,0,100], [1,3,600]]
src = 0, dst = 3, k = 1
Expected: 700 (should not loop infinitely)

// Multiple paths same cost
flights = [[0,1,100], [1,3,100], [0,2,100], [2,3,100]]
src = 0, dst = 3, k = 1
Expected: 200 (either path works)

// Large k (more than needed)
flights = [[0,1,100], [1,2,100]]
src = 0, dst = 2, k = 10
Expected: 200 (optimal path only uses 1 stop)
```

---

## Optimization Opportunities

### 1. Early Termination (BFS Only)
```java
// Stop if we've reached destination and explored all paths at this level
if (minCost[dst] != Integer.MAX_VALUE && stops > k) {
    break;
}
```

### 2. Pruning Expensive Paths
```java
// Don't add to queue if cost already exceeds best known result
int result = Integer.MAX_VALUE;
if (newCost < minCost[nextCity] && newCost < result) {
    minCost[nextCity] = newCost;
    queue.offer(new int[]{nextCity, newCost});
}
```

### 3. Dijkstra + Modified Priority Queue
For advanced optimization, use Dijkstra with state = `[city, stops, cost]` and priority by cost.

---

## Interview Tips

### How to Present Your Solution

1. **Clarify the problem**
   - "So k stops means k intermediate cities, not edges, right?"
   - "Should I optimize for time or space?"
   - "Can prices be negative?" (No, according to constraints)

2. **State your approach**
   - "I'll use Bellman-Ford variation because it's simpler to implement correctly"
   - "Time complexity will be O(E × K), space O(N)"

3. **Code cleanly**
   - Use descriptive variable names
   - Add comments for non-obvious logic
   - Handle edge cases explicitly

4. **Test thoroughly**
   - Walk through example with interviewer
   - Mention edge cases you'd test

5. **Discuss tradeoffs**
   - "BFS could terminate early but is more complex"
   - "Bellman-Ford is cleaner but always runs full K iterations"

### Common Follow-Up Questions

**Q: "Can you optimize this further?"**
- A: "Yes, we could use Dijkstra with modified state (city, stops) or use priority queue in BFS"

**Q: "What if we want the actual path, not just the cost?"**
- A: "We'd track parent pointers: `parent[city] = previous_city` during updates"

**Q: "What if k is very large (k ≥ n)?"**
- A: "Then it becomes standard shortest path. We could use regular Dijkstra"

**Q: "How would you handle negative prices?"**
- A: "Bellman-Ford handles negative edges well. Just need to check for negative cycles"

---

## Summary

### Choose Bellman-Ford When:
- In an interview (simpler to code correctly)
- Graph is dense
- You value code clarity over micro-optimizations

### Choose BFS When:
- Graph is sparse
- Want possibility of early termination
- Already very comfortable with BFS patterns

### Key Takeaway
This problem teaches the important lesson that **not all shortest path problems use Dijkstra**. When there are constraints beyond just finding minimum cost, we need algorithms that can handle additional state dimensions.

---

## Related Problems

- **LeetCode 743:** Network Delay Time (standard Dijkstra)
- **LeetCode 1334:** Find the City With the Smallest Number of Neighbors (Floyd-Warshall)
- **LeetCode 1976:** Number of Ways to Arrive at Destination (path counting with Dijkstra)
- **LeetCode 882:** Reachable Nodes In Subdivided Graph (advanced Dijkstra)

