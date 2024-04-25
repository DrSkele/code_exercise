#include <iostream>
using namespace std;

const int MAX_N = 16;

int n;
int board[MAX_N + 2][MAX_N + 2];

int dp_from_left[MAX_N + 2][MAX_N + 2];
int dp_from_top[MAX_N + 2][MAX_N + 2];
int dp_from_lefttop[MAX_N + 2][MAX_N + 2];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	cin >> n;

	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			cin >> board[i][j];

	dp_from_left[1][2] = 1;

	for (int x = 1; x <= n; x++) {
		for (int y = 1; y <= n; y++) {
			if (board[x][y + 1] == 0) {
				dp_from_left[x][y + 1] += dp_from_left[x][y];
				dp_from_left[x][y + 1] += dp_from_lefttop[x][y];
			}
			if (board[x + 1][y] == 0) {
				dp_from_top[x + 1][y] += dp_from_top[x][y];
				dp_from_top[x + 1][y] += dp_from_lefttop[x][y];
			}
			if (board[x + 1][y] == 0 && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0) {
				dp_from_lefttop[x + 1][y + 1] += dp_from_top[x][y];
				dp_from_lefttop[x + 1][y + 1] += dp_from_left[x][y];
				dp_from_lefttop[x + 1][y + 1] += dp_from_lefttop[x][y];
			}
		}
	}

	cout << dp_from_lefttop[n][n] + dp_from_left[n][n] + dp_from_top[n][n];

	return 0;
}
