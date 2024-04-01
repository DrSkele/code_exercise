#include <iostream>
#include <vector>
#include <queue>
 
#define pii pair<int, int>
 
using namespace std;
 
int n, m;
 
vector<vector<pii>> vec;
vector<int> dist;
 
// 최소거리 구하기 -> 다익스트라
void dijkstra(int s)
{
    // 우선순위 큐
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    // 자기 자신으로 가는 것은 거리가 0임
    dist[s] = 0;
    pq.push(make_pair(0, s));
 
    // 가장 가까운 거리 순으로 우선순위 큐가 돌아간다.
    while (!pq.empty())
    {
        int d = pq.top().first;
        int cur = pq.top().second;
        pq.pop();
 
        // 현재 저장되어 있는 값보다 d가 크다면 continue
        if (dist[cur] < d) continue;
 
        for (int i = 0; i < vec[cur].size(); i++)
        {
            int nxt = vec[cur][i].first;
            int nd = d + vec[cur][i].second;
 
            // 다음 거리가 더 가깝다면 갱신한다.
            if (dist[nxt] > nd)
            {
                dist[nxt] = nd;
                pq.push(make_pair(nd, nxt));
            }
        }
    }
}
 
 
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
 
    cin >> n >> m;
 
    vec.resize(n + 1, vector<pii>());
    dist.resize(n + 1, 1000000000);
 
    int a, b, c;
    for (int i = 0; i < m; i++)
    {
        cin >> a >> b >> c;
        vec[a].push_back(make_pair(b, c));
    }
 
    cin >> a >> b;
 
    dijkstra(a);
 
    // a를 기준으로 다익스트라를 실행했기 때문에
    // dist[b]에는 a -> b의 최소 거리가 저장된다.
    cout << dist[b];
 
 
    return 0;
}