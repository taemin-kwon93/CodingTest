package org.study.parksangkhil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheapestFlightsTest {
    private CheapestFlights cheapestFlights;

    @BeforeEach
    void setUp() {
        cheapestFlights = new CheapestFlights();
    }

    @Test
    void findCheapestPrice_Example1() {
        int n = 4;
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src = 0;
        int dst = 3;
        int k = 1;
        
        int expected = 700;
        int result = cheapestFlights.findCheapestPrice(n, flights, src, dst, k);
        
        assertEquals(expected, result);
    }

    @Test
    void findCheapestPrice_Example2() {
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        
        int expected = 200;
        int result = cheapestFlights.findCheapestPrice(n, flights, src, dst, k);
        
        assertEquals(expected, result);
    }
    
    @Test
    void findCheapestPrice_Example3_DirectPathOnly() {
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 0;
        
        int expected = 500;
        int result = cheapestFlights.findCheapestPrice(n, flights, src, dst, k);
        
        assertEquals(expected, result);
    }

    @Test
    void findCheapestPrice_NoPath() {
        int n = 2;
        int[][] flights = {{0,1,100}};
        int src = 1;
        int dst = 0;
        int k = 0;
        
        int expected = -1;
        int result = cheapestFlights.findCheapestPrice(n, flights, src, dst, k);
        
        assertEquals(expected, result);
    }

    @Test
    void findCheapestPrice_NoPathDueToK() {
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100}};
        int src = 0;
        int dst = 2;
        int k = 0;

        int expected = -1;
        int result = cheapestFlights.findCheapestPrice(n, flights, src, dst, k);

        assertEquals(expected, result);
    }
}
