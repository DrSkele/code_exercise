#include <iostream>
#include <string>
#include <vector>
using namespace std;

string src;
string search;

int src_len;
int search_len;

int fail[1000001];

int cnt = 0;
vector<int> location_list;


void preprocessing() {
	int j = 1;
	int k = 0;

	fail[1] = 0;

	while (j <= search_len) {
		if (k == 0 || search[j] == search[k]) {
			j++;
			k++;
			fail[j] = k;
		}
		else {
			k = fail[k];
		}
	}
}

void kmp() {
	preprocessing();

	int i = 1;
	int j = 1;

	while (i <= src_len) {
		if (j == 0 || src[i] == search[j]) {
			i++;
			j++;
		}
		else {
			j = fail[j];
		}

		if (j == search_len + 1) {
			cnt++;
			location_list.push_back(i - search_len);
			j = fail[j];
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	getline(cin, src);
	getline(cin, search);

	src_len = src.length();
	search_len = search.length();

	src = "1" + src;
	search = "2" + search;

//	preprocessing();

//	for (int i = 1; i <= search_len; i++)
//		cout << fail[i] << " ";
//	cout << "\n";


	kmp();

	cout << cnt << "\n";

	for (int i = 0; i < location_list.size(); i++)
		cout << location_list[i] << " ";

	return 0;
}