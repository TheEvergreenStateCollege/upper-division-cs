// Chpt 10: Dynamic Programming
// pg: 347, 10-13

// Consider a country whose coins are minted with denominations of {1, 6, 10} units.
// We want to count how many distinct ways C(n) there are to make the change of n units.
// How many ways are there to make the change of 20 units from {1, 6, 10}?
// Give an efficient algorithm to compute C(n), and analyze its complexity

// Make change and then count the ways to equal twenty

public class ChangeMaker {

    public static int countWays(int[] coins, int n) {

    int[] ways = new int[n + 1];
    ways[0] = 1; // There is 1 way to make change for 0 units: with no coins

    for (int coin : coins) {
        for (int amount = coin; amount <= n; amount++) {
            ways[amount] += ways[amount - coin];
        }
    }
    return ways[n];
}

public static void main(String[] args) {
    int n = 20; // The amount for how much we want to make change
    int[] coins = {1, 6, 10}; // Dominations of the coins
    int numberOfWays = countWays(coins, n);
    System.out.println("The number of ways to make change of " + n + " units is: " + numberOfWays);
    }
}
