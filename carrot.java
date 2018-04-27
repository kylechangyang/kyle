class Solution {
	public int totalCarrots(int[][] garden) {
		int n = garden.length, m = garden[0].length;
		// check the boundary conditions
		if (garden == null || n == 0 || m == 0) return 0;
		
		// Step One, we need to find the starting cell
		int[] start = new int[2];
		// case 1, both n and m are odd then only one start cell
		if (n % 2 == 1 && m % 2 == 1) {
			start[0] = n/2;
			start[1] = m/2;
		} 
		// case 2, n is odd but m is even, then two candidate cells
		else if (n % 2 == 1) {
			start[0] = n/2;
			start[1] = garden[n/2][m/2] > garden[n/2][m/2-1] ? m/2 : m/2-1;
		}
		// case 3, m is odd but n is even, then two candidate cells
		else if (m % 2 == 1) {
			start[0] = garden[n/2][m/2] > garden[n/2-1][m/2] ? n/2 : n/2-1;
			start[1] = m/2;
		}
		// case 4, both n and m are even, then four candidate cells
		// Since no tie exists, we don't need to consider equal cases
		else {
			if (garden[n/2][m/2] > garden[n/2-1][m/2] && garden[n/2][m/2] > garden[n/2][m/2-1] && garden[n/2][m/2] > garden[n/2-1][m/2-1]) {
				start[0] = n/2; start[1] = m/2;
			} else if (garden[n/2-1][m/2] > garden[n/2][m/2-1] && garden[n/2-1][m/2] > garden[n/2-1][m/2-1]) {
				start[0] = n/2-1; start[1] = m/2;
			} else if (garden[n/2][m/2-1] > garden[n/2-1][m/2-1]) {
				start[0] = n/2; start[1] = m/2-1;
			} else {
				start[0] = n/2-1; start[1] = m/2-1;
			}
		} 

		// Step Two, check four directions, and find the cell with most carrot
		// Use a variable sum to calculate total carrots eaten
		int sum = garden[start[0]][start[1]];
		// Since the center carrots are eaten, set the cell to 0
		garden[start[0]][start[1]] = 0;
		// check the edge case where there are no center carrots to begin with
		if (sum == 0) return sum;
		// list four directions in a 2D array
		int[][] dirs = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};

		// Use an integer localMax to track the highest carrot number among adjacent cells 
		int localMax = -1;
		// If localMax hits zero, the condition of falling asleep is satisfied
		while (localMax != 0) {
			// reset localMax value to 0 every time we move to a new cell
			localMax = 0;
			int[] next = new int[2];
			for (int[] dir : dirs) {
				int x = start[0] + dir[0];
				int y = start[1] + dir[1];
				if (x >= 0 && x < n && y >=0 && y < m) {
					// Update the potential next cell whose has more carrots than current localMax
					if (garden[x][y] > localMax) {
						next[0] = x;
						next[1] = y;
					}
					// Update the localMax value
					localMax = Math.max(localMax, garden[x][y]);
				}
			}
			// After we find the localMax, we add the carrots to the total sum
			if (localMax > 0){
				sum += localMax;
			}
			// Update the carrot number of next cell to zero; bunny eats all!
			garden[next[0]][next[1]] = 0;
			// reset the start cell to the next cell and repeat the process until we hit a zero localMax
			start[0] = next[0]; 
			start[1] = next[1];
		}

		return sum;
	}
}
