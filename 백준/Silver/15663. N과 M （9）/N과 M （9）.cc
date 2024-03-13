#include <iostream>
using namespace std;

// 같은 것이 있는 순열 문제


int num_cnt[10001] = {0,};
int n, m;
int result[10];

void backtrack(int cnt) {
	if (cnt == m) {
		for (int i = 0; i < m; i++)
			cout << result[i] << " ";

		cout << "\n";
		return;
	}

	for (int i = 1; i <= 10000; i++) {
		if (num_cnt[i] > 0) {
			num_cnt[i]--;
			result[cnt] = i;
			backtrack(cnt + 1);
			num_cnt[i]++;
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int temp;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> temp;
		num_cnt[temp]++;
	}

	backtrack(0);
	return 0;
}